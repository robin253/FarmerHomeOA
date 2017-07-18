package com.huike.app.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartframework.common.utils.ExStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huike.app.system.service.impl.UserService;
import com.huike.base.tools.CacheHelp;
import com.sunshine.common.utils.ExControllerUtils;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	protected static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Qualifier("userService")
	@Autowired(required = true)
	private UserService userService;


    @RequestMapping(value = "/login", produces = "application/json")
	public Map<String, Object> login(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> rowData = new HashMap<String, Object>();
		resultMap.put("resultCode", 1);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 非空判断
		Object usernameObj = params.get("username");
		if (usernameObj == null || ExStringUtils.isBlank(usernameObj.toString())) {
			resultMap.put("resultMsg", "用户名不能为空！");
			return resultMap;
		}

		// 非空判断
		Object passwordObj = params.get("password");
		if (passwordObj == null || ExStringUtils.isBlank(passwordObj.toString())) {
			resultMap.put("resultMsg", "密码不能为空！");
			return resultMap;
		}

		// 非空判断
		Object codeObj = params.get("code");
		if (codeObj == null || ExStringUtils.isBlank(codeObj.toString())) {
			resultMap.put("resultMsg", "验证码不能为空！");
			return resultMap;
		}
		Object sessionCode = CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
		String piccode = sessionCode == null ? "" : sessionCode.toString();
		if (codeObj.toString() == null || !(codeObj.toString().toLowerCase().equals(piccode.toLowerCase()))) {
			resultMap.put("resultMsg", "验证码错误！");
			return resultMap;
		}
		
		// 校验用户名密码.
		Map<String, Object> checkUserParams = new HashMap<String, Object>();
		checkUserParams.put("username", usernameObj.toString());
		checkUserParams.put("password", passwordObj.toString());
		checkUserParams.put("userType", 0);
		Map<String, Object> user = userService.checkUser(checkUserParams);
		if (user == null || user.get("id") == null) {
			resultMap.put("resultMsg", "用户名或密码错误！");
			return resultMap;
		}
		CacheHelp.SESSION_USER_MAP.put(request.getParameter("sessionId"),user);
		rowData.put("user",user);
		resultMap.put("resultCode", 0);
		resultMap.put("resultMsg", "OK!");
		resultMap.put("rowData", rowData);
		return resultMap;
	}
    
    @RequestMapping(value = "/back/logout", produces = "application/json")
    public Map<String, Object> logout(HttpServletRequest request){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	resultMap.put("resultCode", 1);
    	request.getSession().invalidate();
    	CacheHelp.SESSION_USER_MAP.remove(request.getParameter("sessionId"));
    	resultMap.put("resultCode", 0);
		resultMap.put("resultMsg", "OK!");
    	return resultMap;
    }
}