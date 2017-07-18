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

import com.huike.app.system.service.impl.VillageService;
import com.huike.base.tools.CacheHelp;
import com.sunshine.common.utils.ExControllerUtils;

/**
 * <p class="detail">
 * 功能：乡村控制类
 * </p>
 * @ClassName: VillageController 
 * @version V1.0  
 * @date 2017年5月25日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
@RestController  
@RequestMapping("/api")  
public class VillageController {

	Logger log = LoggerFactory.getLogger(VillageController.class);
	
	@Autowired
	@Qualifier("villageService")
	VillageService villageService;
	
	/**
	 * <p class="detail">
	 * 功能：后台保存乡村
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月25日 
	 * @param request
	 * @param id 乡村ID（传入时为修改，不传时为添加 ）
	 * @param villageName 乡村名称（添加时必传 ）
	 * @param level 级别 0为乡级，1为村级（添加时必传）
	 * @param parentId 父级ID 添加乡时值为0，添加村时值为所属乡级ID（添加村时必传）
	 * @return 
	 */
	@RequestMapping(value = "/back/village/save", produces = "application/json")
	public Map save(HttpServletRequest request) {
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "保存乡村失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			if (params.get("id") == null || ExStringUtils.isBlank(params.get("id").toString())) {
				// 级别（0为乡级，1为村级）
				if (params.get("level") == null || ExStringUtils.isBlank(params.get("level").toString())) {
					resultMap.put("resultMsg","请输入级别");
					return resultMap;
				}
				// 乡级parentId=0,村级parentId所属乡的ID
				if("0".equals(params.get("level"))){
					params.put("parentId", "0");
				}
				if("1".equals(params.get("level"))){
					if (params.get("parentId") == null || ExStringUtils.isBlank(params.get("parentId").toString())) {
						resultMap.put("resultMsg","请输入村级ID");
						return resultMap;
					}
				}
				// 乡村名
				if (params.get("villageName") == null || ExStringUtils.isBlank(params.get("villageName").toString())) {
					resultMap.put("resultMsg","请输入乡村名称");
					return resultMap;
				}
				result = villageService.save(params);
			} else {
				result = villageService.modify(params);
			}
			
			if(result > 0){
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "OK");
				resultMap.put("data", result);
			}
		} catch (Exception e) {
			log.info("保存乡村报错："+e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：后台删除乡村
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月25日 
	 * @param request
	 * @param id 乡村ID（必传 ）
	 * @return 
	 */
	@RequestMapping(value = "/back/village/delete", produces = "application/json")
	public Map delete(HttpServletRequest request) {
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "删除乡村失败");
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
			result = villageService.removeById(id);
			
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
	 * 功能：后台查询乡下拉列表
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月25日 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/village/parentList", produces = "application/json")
	public Map parentList(HttpServletRequest request) {
		List<Map<String, Object>> parentList = new ArrayList<Map<String, Object>>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "查询乡下拉列表失败");
		resultMap.put("data", parentList);
		Map params = new HashMap();
		try {
			// 查询乡村下拉列表
			parentList = villageService.getParentList(params);
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", parentList);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：后台查询乡下拉列表（县级获取所有乡，乡级仅获取所在乡）
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月25日 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/village/parentListByLevel", produces = "application/json")
	public Map parentListByLevel(HttpServletRequest request) {
		List<Map<String, Object>> parentList = new ArrayList<Map<String, Object>>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "查询乡下拉列表失败");
		resultMap.put("data", parentList);
		Map params = new HashMap();
		try {
			// 乡只能查看自己乡下面的村，无法查看其它乡下面的村
			Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
			String level = sessionUser.get("level").toString();
			log.info("当前登录用户级别为："+level);
			if("0".equals(level)){
				// 查询乡村下拉列表
				parentList = villageService.getParentList(params);
			}
			if("1".equals(level)){
				params.put("xiangId", sessionUser.get("xiangId").toString());
				// 查询乡村下拉列表
				parentList = villageService.getParentList(params);
			}
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", parentList);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：获取隶属于某乡的村下拉列表
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月25日 
	 * @param request
	 * @param parentId 父级ID(必传)
	 * @return
	 */
	@RequestMapping(value = "/back/village/childrenList", produces = "application/json")
	public Map childrenList(HttpServletRequest request) {
		List<Map<String, Object>> parentList = new ArrayList<Map<String, Object>>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "查询村下拉列表失败");
		resultMap.put("data", parentList);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Object parentIdObj = params.get("parentId");
			if (parentIdObj == null || ExStringUtils.isBlank(parentIdObj.toString())) {
				resultMap.put("resultMsg","请输入父级ID");
				return resultMap;
			} 
			//Integer parentId = Integer.valueOf(parentIdObj.toString());
			// 查询乡村下拉列表
			parentList = villageService.getChildrenList(params);
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", parentList);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：获取隶属于某乡的村下拉列表（县级查看所有村，乡级返回所在乡下的所有村，村级返回所在村）
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月25日 
	 * @param request
	 * @param parentId 父级ID(必传)
	 * @return
	 */
	@RequestMapping(value = "/back/village/childrenListByLevel", produces = "application/json")
	public Map childrenListByLevel(HttpServletRequest request) {
		List<Map<String, Object>> parentList = new ArrayList<Map<String, Object>>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "查询村下拉列表失败");
		resultMap.put("data", parentList);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			
			// 乡只能查看自己乡下面的村，无法查看其它乡下面的村
			Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
			String level = sessionUser.get("level").toString();
			log.info("当前登录用户级别为："+level);
			if("0".equals(level)){ // 返货所有村
				// 查询乡村下拉列表
				parentList = villageService.getChildrenList(null);
			}
			if("1".equals(level)){
				params.put("xiangId", sessionUser.get("xiangId").toString());
				//Integer parentId = Integer.valueOf(sessionUser.get("xiangId").toString());
				// 查询乡村下拉列表
				parentList = villageService.getChildrenList(params);
			}
			if("2".equals(level)){
				Map villageMap = new HashMap();
				villageMap.put("id", sessionUser.get("cunId").toString());
				villageMap.put("villageName", sessionUser.get("cunName").toString());
				parentList.add(villageMap);
			}
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", parentList);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：后台查询乡村列表
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月25日 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/village/list", produces = "application/json")
	public Object getVillageList(HttpServletRequest request){
		Map<String, Object> villageMap = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取乡村列表失败");
		resultMap.put("data", villageMap);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			List<Map<String, Object>> villageList = villageService.getAllVillage(paramMap);
			List<Map<String, Object>> parentList = new ArrayList<Map<String, Object>>();
			for(Map<String, Object> village : villageList){
				if("0".equals(village.get("parentId").toString())){
					Map<String, Object> parentMap = new HashMap<String, Object>();
					parentMap.put("id", village.get("id"));
					parentMap.put("xiang", village.get("villageName"));
					List<Map<String, Object>> childrenList = new ArrayList<Map<String, Object>>();
					for(Map<String, Object> child : villageList){
						if(village.get("id").toString().equals(child.get("parentId").toString())){
						Map<String, Object> childMap = new HashMap<String, Object>();
						childMap.put("id", child.get("id"));
						childMap.put("cun", child.get("villageName"));
						childMap.put("parentId", child.get("parentId"));
						childrenList.add(childMap);
						}
					}
					parentMap.put("childrenList", childrenList);
					parentList.add(parentMap);
				}
			}
			villageMap.put("parentList",parentList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		resultMap.put("resultMsg", "OK");
		resultMap.put("data", villageMap);
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
	@RequestMapping(value = "/back/village/detail", produces = "application/json")
	public Map queryDetail(HttpServletRequest request) {
		Map<String, Object> village = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取乡村详情失败");
		resultMap.put("data", village);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Object idObj = params.get("id");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg","请输入记录ID");
				return resultMap;
			} 
			Long id = Long.valueOf(idObj.toString());
			village = villageService.queryById(id);
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", village);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
}
