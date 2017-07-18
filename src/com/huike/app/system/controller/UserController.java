package com.huike.app.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartframework.common.utils.ExStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huike.app.system.service.impl.UserService;
import com.huike.base.tools.Page;
import com.sunshine.common.utils.ExControllerUtils;

/**
 * <p class="detail">
 * 功能：系统用户控制类
 * </p>
 * @ClassName: UserController 
 * @version V1.0  
 * @date 2017年5月25日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
@RestController  
@RequestMapping("/api")  
public class UserController {
	
	Logger log = LoggerFactory.getLogger(VillageController.class);
	
	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	/**
	 * <p class="detail">
	 * 功能：后台保存系统用户
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月26日 
	 * @param request
	 * @param id 用户ID（传入时为修改，不传时为添加 ）
	 * @param username 姓名（添加时必传 ）
	 * @param userPhone 手机号（添加时必传）
	 * @param password 密码（添加村时必传）
	 * @param xiangId 乡ID
	 * @param cunId 村ID
	 * @param departId 部门ID
	 * @return 
	 */
	@RequestMapping(value = "/back/user/save", produces = "application/json")
	public Map save(HttpServletRequest request) {
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "保存用户失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			if (params.get("id") == null || ExStringUtils.isBlank(params.get("id").toString())) {
				// 姓名
				if (params.get("username") == null || ExStringUtils.isBlank(params.get("username").toString())) {
					resultMap.put("resultMsg","请输入用户姓名");
					return resultMap;
				}
				// 手机号
				if (params.get("userPhone") == null || ExStringUtils.isBlank(params.get("userPhone").toString())) {
					resultMap.put("resultMsg","请输入用户手机号");
					return resultMap;
				}
				// 密码
				if (params.get("password") == null || ExStringUtils.isBlank(params.get("password").toString())) {
					resultMap.put("resultMsg","请输入用户密码");
					return resultMap;
				}
				// 级别
				/*if (params.get("cunId") != null && ExStringUtils.isNotBlank(params.get("cunId").toString())){
					params.put("level", "2"); // 村级
				}else if(params.get("xiangId") != null && ExStringUtils.isNotBlank(params.get("xiangId").toString())){
					params.put("level", "1"); // 乡级
				}else {
					params.put("level", "0"); // 县级
				}*/
				
				// 校验用户名是否被注册
				boolean existUsername = userService.existUser(params.get("username").toString());
				if (!existUsername) {
					resultMap.put("resultMsg", "用户名已存在！");
					return resultMap;
				}
				
				result = userService.save(params);
			} else {
				result = userService.modify(params);
			}
			
			if(result > 0){
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "OK");
				resultMap.put("data", result);
			}
		} catch (Exception e) {
			log.info("保存用户报错："+e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：后台删除用户
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月26日 
	 * @param request
	 * @param id 乡村ID（必传 ）
	 * @return 
	 */
	@RequestMapping(value = "/back/user/delete", produces = "application/json")
	public Map delete(HttpServletRequest request) {
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "删除系统用户失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Object idObj = params.get("id");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg","请输入记录ID");
				return resultMap;
			} 
			Long id = Long.valueOf(idObj.toString());
			result = userService.removeById(id);
			
			if(result > 0){
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
	 * <p class="detail">
	 * 功能：后台分页查询用户
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月24日 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/user/list", produces = "application/json")
	public Map getDepartmentList(HttpServletRequest request) throws IOException {
		Map<String, Object> userMap = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取系统用户列表失败");
		resultMap.put("data", userMap);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			int pageIndex = ExControllerUtils.getPageIndex(paramMap);
			int pageSize = ExControllerUtils.getPageSize(paramMap);
			paramMap.put("offset", new Page(pageSize, pageIndex).getOffset());
			paramMap.put("show", pageSize);
			List<Map<String, Object>> userList = userService.findForPageListSearch(paramMap);
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
	 * 功能：获取单条记录详情
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月26日 
	 * @param request
	 * @param id 记录ID（必传）
	 * @return
	 */
	@RequestMapping(value = "/back/user/detail", produces = "application/json")
	public Map queryDetail(HttpServletRequest request) {
		Map<String, Object> user = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取用户详情失败");
		resultMap.put("data", user);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Object idObj = params.get("id");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg","请输入记录ID");
				return resultMap;
			} 
			Long id = Long.valueOf(idObj.toString());
			user = userService.queryById(id);
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
	 * 功能：根据村ID查询用户列表
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月24日 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/user/listByCun", produces = "application/json")
	public Map getUserListByCun(HttpServletRequest request) throws IOException {
		List<Map<String, Object>> userMap = new ArrayList<Map<String, Object>>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取用户列表失败");
		resultMap.put("data", userMap);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			Object cunObj = paramMap.get("cunId");
			if (cunObj == null || ExStringUtils.isBlank(cunObj.toString())) {
				resultMap.put("resultMsg","请输入村ID");
				return resultMap;
			} 
			userMap = userService.findUserListByCun(paramMap);
			resultMap.put("resultCode", 0);
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
	 * 功能：添加短信人员
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月24日 
	 * @param request
	 * @param ids ,分隔
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/user/addReportUser", produces = "application/json")
	public Map addReportUser(HttpServletRequest request) throws IOException {
		Integer result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "添加上传报表人员失败");
		resultMap.put("data", result);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			Object idObj = paramMap.get("ids");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg","请输入用户ID");
				return resultMap;
			}
			String[] ids = StringUtils.split(idObj.toString(), ",");
			paramMap.put("ids", ids);
			paramMap.put("isReport", "0");
			result = userService.addReportUser(paramMap);
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", result);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：根据级别获取所有人员
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月24日 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/user/queryUsersByLevel", produces = "application/json")
	public Map queryUsersByLevel(HttpServletRequest request) throws IOException {
		List<Map<String, Object>> userMap = new ArrayList<Map<String, Object>>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取人员失败");
		resultMap.put("data", userMap);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			String level = paramMap.get("level")==null?"":paramMap.get("level").toString();
			if(ExStringUtils.isBlank(level)){
				resultMap.put("resultMsg", "请传入级别！");
				return resultMap;
			}
			if("1".equals(level)){
				paramMap.put("departId", "");
			}
			userMap = userService.queryUsersByLevel(paramMap);
			resultMap.put("resultCode", 0);
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
	 * 功能：不分页获取人员列表
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月24日 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/user/queryUsersList", produces = "application/json")
	public Map queryUsersList(HttpServletRequest request) throws IOException {
		List<Map<String, Object>> userMap = new ArrayList<Map<String, Object>>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取人员失败");
		resultMap.put("data", userMap);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			userMap = userService.queryUsersList(paramMap);
			resultMap.put("resultCode", 0);
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
	 * 功能：删除短信人员
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月24日 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/user/deleteReportUser", produces = "application/json")
	public Map deleteReportUser(HttpServletRequest request) throws IOException {
		Integer result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "删除上传报表人员失败");
		resultMap.put("data", result);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			Object idObj = paramMap.get("ids");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg","请输入用户ID");
				return resultMap;
			}
			String[] ids = StringUtils.split(idObj.toString(), ",");
			paramMap.put("ids", ids);
			paramMap.put("isReport", "1");
			result = userService.deleteReportUser(paramMap);
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", result);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
}
