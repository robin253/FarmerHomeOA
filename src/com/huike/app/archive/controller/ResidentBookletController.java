package com.huike.app.archive.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartframework.common.utils.ExStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huike.app.archive.service.impl.ResidentBookletService;
import com.huike.app.archive.service.impl.ResidentService;
import com.huike.base.tools.CacheHelp;
import com.huike.base.tools.MapUtil;
import com.huike.base.tools.Page;
import com.sunshine.common.utils.ExControllerUtils;

/**
 * <p class="detail">
 * 功能：农户档案信息控制类
 * </p>
 * 
 * @ClassName: ResidentController
 * @version V1.0
 * @date 2017年7月24日
 * @author Zerlinda Copyright 2015 tsou.com, Inc. All rights reserved
 */
@RestController
@RequestMapping("/api")
public class ResidentBookletController {

	Logger log = LoggerFactory.getLogger(ResidentBookletController.class);

	@Autowired
	@Qualifier("residentBookletService")
	ResidentBookletService residentBookletService;
	
	@Autowired
	@Qualifier("residentService")
	ResidentService residentService;

	/**
	 * <p class="detail">
	 * 功能：后台保存农户档案信息
	 * </p>
	 * 
	 * @author Zerlinda
	 * @date 2017年7月24日
	 * @param request
	 * @param id
	 *            记录ID（传入时为修改，不传时为添加 ）
	 * @param name
	 *            姓名（必传 ）
	 * @return
	 */
	@RequestMapping(value = "/back/residentBooklet/save", produces = "application/json")
	public Map save(HttpServletRequest request) {
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "保存农户失败");
		resultMap.put("data", result);
		Map paramsTemp = new HashMap();
		try {
			paramsTemp = ExControllerUtils.buildParametersMap(request);
			
			Map params = MapUtil.string2Map(paramsTemp.get("residentBooklet").toString());
			
			if (params.get("id") == null || ExStringUtils.isBlank(params.get("id").toString())) {
				if (params.get("houseHolder") == null || ExStringUtils.isBlank(params.get("houseHolder").toString())) {
					resultMap.put("resultMsg", "请输入农户主姓名");
					return resultMap;
				}
				Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
				if(sessionUser==null){
					resultMap.put("resultMsg", "登录失效，请重新登录！");
					return resultMap;
				}
				params.put("xiangId", sessionUser.get("xiangId").toString());
				params.put("cunId", sessionUser.get("cunId").toString());
				result = residentBookletService.save(params);
			} else {
				result = residentBookletService.modify(params);
			}
			if (result > 0) {
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "OK");
				resultMap.put("data", result);
			}
		} catch (Exception e) {
			log.info("保存农户信息报错：" + e.getMessage());
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * <p class="detail">
	 * 功能：获取单条记录详情
	 * </p>
	 * 
	 * @author Zerlinda
	 * @date 2017年5月26日
	 * @param request
	 * @param id
	 *            记录ID（必传）
	 * @return
	 */
	@RequestMapping(value = "/back/residentBooklet/detail", produces = "application/json")
	public Map queryDetail(HttpServletRequest request) {
		Map<String, Object> user = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取农户详情失败");
		resultMap.put("data", user);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Object idObj = params.get("id");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg", "请输入记录ID");
				return resultMap;
			}
			Integer id = Integer.valueOf(idObj.toString());
			user = residentBookletService.queryById(id);
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", user);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * <p class="detail">
	 * 功能：后台分页查询农户列表 
	 * </p>
	 * 
	 * @author Zerlinda
	 * @date 2017年5月26日
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/residentBooklet/list", produces = "application/json")
	public Map getResidentList(HttpServletRequest request) throws IOException {
		Map<String, Object> userMap = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取农户列表失败");
		resultMap.put("data", userMap);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			// 乡只能查看自己乡下面的村，无法查看其它乡下面的村
			Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
			String level = sessionUser.get("level").toString();
			log.info("当前登录用户级别为："+level);
			if("1".equals(level)){
				paramMap.put("xiangId", sessionUser.get("xiangId"));
			}
			if("2".equals(level)){
				paramMap.put("cunId", sessionUser.get("cunId"));
			}
			int pageIndex = ExControllerUtils.getPageIndex(paramMap);
			int pageSize = ExControllerUtils.getPageSize(paramMap);
			paramMap.put("offset", new Page(pageSize, pageIndex).getOffset());
			paramMap.put("show", pageSize);
			List<Map<String, Object>> userList = residentBookletService.findForPageListSearch(paramMap);
			Map<String, Object> map = ExControllerUtils.buildTableData(userList, pageIndex, pageSize);
			userMap.put("rows", map.get("rows"));
			userMap.put("pagination", map.get("pagination"));
			resultMap.put("resultCode", map.get("resultCode"));
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", userMap);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * <p class="detail">
	 * 功能：后台删除农户
	 * </p>
	 * 
	 * @author Zerlinda
	 * @date 2017年5月26日
	 * @param request
	 * @param id
	 *            记录ID（必传 ）
	 * @return
	 */
	@RequestMapping(value = "/back/residentBooklet/delete", produces = "application/json")
	public Map delete(HttpServletRequest request) {
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "删除农户失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Object idObj = params.get("id");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg", "请输入记录ID");
				return resultMap;
			}
			Integer id = Integer.valueOf(idObj.toString());
			result = residentBookletService.removeById(id);
			// 删除关联成员
			residentService.removeByForId(id);
			if (result > 0) {
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "OK");
				resultMap.put("data", result);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * 查看详情 附带家庭成员信息
	 */
	@RequestMapping(value = "/back/residentBooklet/detailWithMembers", produces = "application/json")
	public Map queryDetailWithMembers(HttpServletRequest request) {
		Map<String, Object> residentBooklet = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取农户详情失败");
		resultMap.put("data", residentBooklet);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Object idObj = params.get("id");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg", "请输入记录ID");
				return resultMap;
			}
			Integer id = Integer.valueOf(idObj.toString());
			residentBooklet = residentBookletService.queryById(id);
			
			Map<String, Object> membersParams =  new HashMap<String, Object>();
			membersParams.put("residentBookletId", id);
			
			int pageIndex = ExControllerUtils.getPageIndex(membersParams);
			int pageSize = ExControllerUtils.getPageSize(membersParams);
			membersParams.put("offset", new Page(pageSize, pageIndex).getOffset());
			membersParams.put("show", pageSize);
			
			List<Map<String, Object>> members = residentService.findForPageListSearch(membersParams);
			residentBooklet.put("members",members);
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", residentBooklet);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
}
