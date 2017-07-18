package com.huike.app.system.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.huike.app.system.service.impl.DepartmentService;
import com.huike.base.tools.Page;
import com.sunshine.common.utils.ExControllerUtils;

/**
 * <p class="detail">
 * 功能：部门控制器
 * </p>
 * @ClassName: DepartmentController 
 * @version V1.0  
 * @date 2017年5月24日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
@RestController  
@RequestMapping("/api")  
public class DepartmentController {
	
	Logger log = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	@Qualifier("departmentService")
	DepartmentService departmentService;

	/**
	 * <p class="detail">
	 * 功能：后台保存部门
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月24日 
	 * @param request
	 * @param id 部门ID（传入时为修改，不传时为添加 ）
	 * @param departName 部门名称（必传 ）
	 * @return 
	 */
	@RequestMapping(value = "/back/department/save", produces = "application/json")
	public Map save(HttpServletRequest request) {
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "保存部门失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			if (params.get("id") == null || ExStringUtils.isBlank(params.get("id").toString())) {
				if (params.get("departName") == null || ExStringUtils.isBlank(params.get("departName").toString())) {
					resultMap.put("resultMsg","请输入部门名称");
					return resultMap;
				}
				// 校验用户名是否被注册
				boolean existDepartName = departmentService.existDepartName(params.get("departName")==null?"":params.get("departName").toString());
				if (!existDepartName) {
					resultMap.put("resultMsg", "部门名已存在！");
					return resultMap;
				}
				result = departmentService.save(params);
			} else {
				result = departmentService.modify(params);
			}
			
			if(result > 0){
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "OK");
				resultMap.put("data", result);
			}
		} catch (Exception e) {
			log.info("保存部门报错："+e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：后台删除部门
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月24日 
	 * @param request
	 * @param id 部门ID（必传 ）
	 * @return 
	 */
	@RequestMapping(value = "/back/department/delete", produces = "application/json")
	public Map delete(HttpServletRequest request) {
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "删除部门失败");
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
			result = departmentService.removeById(id);
			
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
	 * 功能：后台分页查询
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月24日 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/department/list", produces = "application/json")
	public Object getDepartmentList(HttpServletRequest request) throws IOException {
		Map<String, Object> departmentMap = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取部门列表失败");
		resultMap.put("data", departmentMap);
		Map paramMap = ExControllerUtils.buildParametersMap(request);
/*		String yhdh = ExRequestUtils.getLoginId(request);
		paramMap.put("yhdh", yhdh);
		Map loginUserData = ExRequestUtils.getLoginUserData(request);*/
		/*if (loginUserData.get("orgCode") != null && !"null".equals(String.valueOf(loginUserData.get("orgCode")))) {
			paramMap.put("orgCode", loginUserData.get("orgCode"));
			xzqh = String.valueOf(loginUserData.get("orgCode")).substring(0, 4);
		} else {
			return blackCarMap;
		}*/
		int pageIndex = ExControllerUtils.getPageIndex(paramMap);
		int pageSize = ExControllerUtils.getPageSize(paramMap);
		paramMap.put("offset", new Page(pageSize, pageIndex).getOffset());
		paramMap.put("show", pageSize);
		List<Map<String, Object>> departmentList = departmentService.findForPageListSearch(paramMap);
		Map<String, Object> map = ExControllerUtils.buildTableData(departmentList, pageIndex, pageSize);
		departmentMap.put("rows", map.get("rows"));
		departmentMap.put("pagination", map.get("pagination"));
		resultMap.put("resultCode", map.get("resultCode"));
		resultMap.put("resultMsg", "OK");
		resultMap.put("data", departmentMap);
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：获取部门下拉列表
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月25日 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/department/optionList", produces = "application/json")
	public Map optionList(HttpServletRequest request) {
		List<Map<String, Object>> departmentList = new ArrayList<Map<String, Object>>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取部门下拉列表失败");
		resultMap.put("data", departmentList);
		Map params = new HashMap();
		try {
			// 查询乡村下拉列表
			departmentList = departmentService.getOptionList();
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", departmentList);
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
	@RequestMapping(value = "/back/department/detail", produces = "application/json")
	public Map queryDetail(HttpServletRequest request) {
		Map<String, Object> department = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取部门详情失败");
		resultMap.put("data", department);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Object idObj = params.get("id");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg","请输入记录ID");
				return resultMap;
			} 
			Long id = Long.valueOf(idObj.toString());
			department = departmentService.queryById(id);
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", department);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
}
