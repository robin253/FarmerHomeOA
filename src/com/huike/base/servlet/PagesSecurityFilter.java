package com.huike.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.smartframework.common.GlobalConfig;
import org.smartframework.common.utils.ExRequestUtils;
import org.smartframework.common.utils.ExStringUtils;
import org.smartframework.service.token.TokenService;
import org.smartframework.service.token.TokenServiceFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.huike.base.tools.CacheHelp;
import com.huike.base.tools.MapUtil;
import com.sunshine.common.service.AppConfigService;
import com.sunshine.common.utils.ExControllerUtils;

@WebFilter(filterName = "myFilter")
public class PagesSecurityFilter implements Filter {

	private static Log log = LogFactory.getLog(PagesSecurityFilter.class);

	private static final String X_REQUESTED_WITH = "X-Requested-With";

	public PagesSecurityFilter() {

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String requestUrl = request.getRequestURI();
		// 设置字符集
		request.setCharacterEncoding("UTF-8");
		// 检查访问权限
		String resultMsg = this.checkSecurity(request);

		if (resultMsg != null) {
			log.debug("过滤结果:" + requestUrl + " = " + resultMsg);
		} else {
			log.debug("访问:" + requestUrl);
		}

		// 登出页面,清除COOKIE
		if (requestUrl.endsWith("/logout")) {
			CacheHelp.SESSION_USER_MAP.remove(request.getParameter("sessionId"));
			/*ExRequestUtils.removeCookie((HttpServletRequest) req, (HttpServletResponse) resp,
					GlobalConfig.KEY_LOGIN_ID);
			ExRequestUtils.removeCookie((HttpServletRequest) req, (HttpServletResponse) resp,
					GlobalConfig.KEY_LOGIN_TOKEN);*/
			request.getSession().invalidate();
		}

		if (resultMsg == null) {
			chain.doFilter(request, response);
		} else {
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");

			String contextPath = request.getContextPath();
			String redirectUrl = null;
			if ("未找到URL访问权限信息".equalsIgnoreCase(resultMsg)) {
				redirectUrl = "/error.html?msg=" + resultMsg;
				if (!"/".equalsIgnoreCase(contextPath)) {
					redirectUrl = contextPath + redirectUrl;
				}
			} else {
				redirectUrl = GlobalConfig.getConfigValue("filter.login.url");
				if (redirectUrl == null) {
					redirectUrl = "/login.html";
				}

				if (!"/".equalsIgnoreCase(contextPath)) {
					redirectUrl = contextPath + redirectUrl;
				}
				String backtoUrl = request.getRequestURL().toString();
				if (request.getQueryString() != null) {
					backtoUrl += "?" + request.getQueryString();
				}
				redirectUrl += "?backtoUrl=" + URLEncoder.encode(backtoUrl, "UTF-8");
			}

			// log.debug("转向3:" + redirectUrl);
			// response.sendRedirect(redirectUrl);

			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-type", "application/json;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			Map resultMap = new HashMap();
			resultMap.put("resultCode", "1");
			resultMap.put("resultMsg", resultMsg);
			resultMap.put("rows", "");
			writer.write(MapUtil.map2JsonString(resultMap));
		}

	}

	protected String getRequestUrl(HttpServletRequest request) {
		String requestUrl = request.getRequestURI();
		String contextPath = request.getContextPath();
		if (!"/".equalsIgnoreCase(contextPath)) {
			requestUrl = requestUrl.substring(contextPath.length());
		}
		return requestUrl;
	}

	protected boolean isPublicResource(HttpServletRequest request) {
		String requestUrl = getRequestUrl(request);

		String configValue = "/, /login.html, /logout.html , ^/assets/.*,/api/web/rest/login/.*,/api/web/rest/kaptcha/.*,/api/app/rest/login/first,/api/app/rest/login/logout,/api/app/rest/login/second,/api/app/rest/regcode/.*,/api/app/rest/userregist/.*";
		/*String loginUrl = GlobalConfig.getConfigValue("filter.login.url");
		if (ExStringUtils.isNotBlank(loginUrl)) {
			configValue += "," + loginUrl;
		}

		String publicUrls = GlobalConfig.getConfigValue("filter.public.urls");
		if (ExStringUtils.isNotBlank(publicUrls)) {
			configValue += "," + publicUrls;
		}*/

		String[] urlPatterns = ExStringUtils.split(configValue, ",");

		for (String regex : urlPatterns) {
			if (requestUrl.matches(regex)) {
				return true;
			}
		}

		return false;
	}

	protected String checkSecurity(HttpServletRequest request) {

		if (isPublicResource(request)) {
			return null;
		}

		Map<String, Object> params = new HashMap<String, Object> ();
		try {
			params = ExControllerUtils.buildParametersMap(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(params == null || params.size() == 0){
			return "未找到登录用户信息";
		}
		
		// 判断：后台页面接口请求需要用户已登录
		String requestUrl = getRequestUrl(request);
		if(requestUrl.startsWith("/api/back/")){
			if(CacheHelp.SESSION_USER_MAP.containsKey(request.getParameter("sessionId"))){
				return null;
			}else{
				return "未找到后台管理登录用户信息";
			}
		}
		return null;
	}
	
	protected String checkTokenExpiredTime(Map<String, Object> params, String userIP) {

		// userId.
		String name = CacheHelp.SYS_CONFIG_MAP.get("filter.cookie.loginId").toString();
		if (ExStringUtils.isBlank(name)) {
			return "请传入UID";
		}
		// String loginId = ExRequestUtils.getCookieValue(request, name);
		String loginId = params.get(name) == null ? "" : params.get(name).toString();
		if (ExStringUtils.isBlank(loginId)) {
			return "未找到登录用户信息";
		}
		return null;
	}

	public boolean removeTokenData(String token) {
		String appName = CacheHelp.SYS_CONFIG_MAP.get("token.appName").toString();
		TokenService tokenService = TokenServiceFactory.createTokenService(appName);
		return tokenService.removeToken(token);
	}

	protected boolean checkUserRight(HttpServletRequest request, String loginId) {

		String requestUrl = getRequestUrl(request);

		ApplicationContext appContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getServletContext());
		AppConfigService appConfigService = (AppConfigService) appContext.getBean(AppConfigService.class);
		appConfigService.setLoginUserId(loginId);

		if (requestUrl.startsWith("api")) {
			return appConfigService.hasServiceInterfaceRight(requestUrl);
		} else {
			return appConfigService.hasMenuRight(requestUrl);
		}

	}

}
