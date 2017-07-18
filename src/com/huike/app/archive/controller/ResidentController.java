package com.huike.app.archive.controller;

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

import com.huike.app.archive.service.impl.ResidentService;
import com.huike.base.tools.CacheHelp;
import com.huike.base.tools.ListUtil;
import com.huike.base.tools.Page;
import com.sunshine.common.utils.ExControllerUtils;

/**
 * <p class="detail">
 * 功能：农户信息控制类
 * </p>
 * 
 * @ClassName: ResidentController
 * @version V1.0
 * @date 2017年5月26日
 * @author Zerlinda Copyright 2015 tsou.com, Inc. All rights reserved
 */
@RestController
@RequestMapping("/api")
public class ResidentController {

	Logger log = LoggerFactory.getLogger(ResidentController.class);

	@Autowired
	@Qualifier("residentService")
	ResidentService residentService;

	/**
	 * <p class="detail">
	 * 功能：后台保存农户信息
	 * </p>
	 * 
	 * @author Zerlinda
	 * @date 2017年5月26日
	 * @param request
	 * @param id
	 *            记录ID（传入时为修改，不传时为添加 ）
	 * @param name
	 *            姓名（必传 ）
	 * @return
	 */
	@RequestMapping(value = "/back/resident/save", produces = "application/json")
	public Map save(HttpServletRequest request) {
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "保存农户失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			if (params.get("id") == null || ExStringUtils.isBlank(params.get("id").toString())) {
				if (params.get("name") == null || ExStringUtils.isBlank(params.get("name").toString())) {
					resultMap.put("resultMsg", "请输入农户名称");
					return resultMap;
				}
				Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
				params.put("xiangId", sessionUser.get("xiangId").toString());
				params.put("cunId", sessionUser.get("cunId").toString());
				result = residentService.save(params);
			} else {
				result = residentService.modify(params);
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
	@RequestMapping(value = "/back/resident/detail", produces = "application/json")
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
			Long id = Long.valueOf(idObj.toString());
			user = residentService.queryById(id);
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
	@RequestMapping(value = "/back/resident/list", produces = "application/json")
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
			List<Map<String, Object>> userList = residentService.findForPageListSearch(paramMap);
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
	@RequestMapping(value = "/back/resident/delete", produces = "application/json")
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
			Long id = Long.valueOf(idObj.toString());
			result = residentService.removeById(id);

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
	 * <p class="detail">
	 * 功能：后台农户统计
	 * </p>
	 * 
	 * @author Zerlinda
	 * @date 2017年5月26日
	 * @param request
	 * @param statisticsType 1 饼状图 ； 2柱形图
	 * @return
	 */
	@RequestMapping(value = "/back/resident/statistics", produces = "application/json")
	public Map statistics(HttpServletRequest request) {
		List result = new ArrayList();
		Map tempMap = new HashMap();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "农户统计失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Object statisticsTypeObj = params.get("statisticsType");
			if (statisticsTypeObj == null || ExStringUtils.isBlank(statisticsTypeObj.toString())) {
				resultMap.put("resultMsg", "请传入统计类型");
				return resultMap;
			}
			Object educationObj = params.get("education");
			Object politicsObj = params.get("politics");
			Object cunObj = params.get("cunId");
			Object xiangObj = params.get("xiangId");
			
			if (cunObj != null && ExStringUtils.isNotBlank(cunObj.toString())){
				String[] cunIds = StringUtils.split(cunObj.toString(), ",");
				params.put("cunId", cunIds);
			}
			
			if(xiangObj != null && ExStringUtils.isNotBlank(xiangObj.toString())){
				String[] xiangIds = StringUtils.split(xiangObj.toString(), ",");
				params.put("xiangId", xiangIds);
			}
			
			if (educationObj != null && ExStringUtils.isNotBlank(educationObj.toString())
					&& educationObj.toString().equals("all")) { // 按教育程度分组
				resultList = residentService.statisticsGroupByEducation(params);
			}else if (politicsObj != null && ExStringUtils.isNotBlank(politicsObj.toString())
					&& politicsObj.toString().equals("all")) { // 按教育程度分组
				resultList = residentService.statisticsGroupByPolitics(params);
			}else if(cunObj != null && ExStringUtils.isNotBlank(cunObj.toString())){
				resultList = residentService.statisticsGroupByCun(params); // 按村分组
			}else{
				resultList = residentService.statisticsGroupByXiang(params); // 按乡分组
			}
			List value = new ArrayList();
			List name = new ArrayList();
			if(statisticsTypeObj != null && ExStringUtils.isNotBlank(statisticsTypeObj.toString())){
				if("2".equals(statisticsTypeObj.toString())){
					for(int i = 0 ; i < resultList.size() ; i++) {
						Map temp = resultList.get(i);
						value.add(temp.get("value"));
						name.add(temp.get("name"));
					}
					tempMap.put("value", value);
					tempMap.put("name", name);
					resultMap.put("data", tempMap);
				}
				if("1".equals(statisticsTypeObj.toString())){
					resultMap.put("data", resultList);
				}
			}
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
}
