package com.huike.app.report.controller;

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

import com.huike.app.report.service.impl.ReportManageService;
import com.huike.base.tools.Page;
import com.sunshine.common.utils.ExControllerUtils;

/**
 * <p class="detail">
 * 功能：报表上传备案控制类
 * </p>
 * @ClassName: ReportManageController 
 * @version V1.0  
 * @date 2017年6月19日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
@RestController  
@RequestMapping("/api") 
public class ReportManageController {
	Logger log = LoggerFactory.getLogger(ReportServerController.class);

	@Autowired
	@Qualifier("reportManageService")
	ReportManageService reportManageService;
	
	/**
	 * 未上报管理 按条件查询
	 */
	@RequestMapping(value = "/back/reportManage/list", produces = "application/json")
	public Map<String, Object> getReportManageList(HttpServletRequest request){
		Map<String, Object> reportManageMap = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取报表备案列表失败");
		resultMap.put("data", reportManageMap);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			// 开始时间结束时间 startTime endTime
			// 上报情况 isUpload 1 未上报 2逾期上报
			Object statusObj = paramMap.get("isUpload");
			if(statusObj == null || ExStringUtils.isBlank(statusObj.toString())){
				resultMap.put("resultMsg", "请选择上报情况");
				return resultMap;
			}
			int pageIndex = ExControllerUtils.getPageIndex(paramMap);
			int pageSize = ExControllerUtils.getPageSize(paramMap);
			paramMap.put("offset", new Page(pageSize, pageIndex).getOffset());
			paramMap.put("show", pageSize);
			List<Map<String, Object>> reportManageList = reportManageService.findForPageListSearch(paramMap);
			Map<String, Object> map = ExControllerUtils.buildTableData(reportManageList, pageIndex, pageSize);
			reportManageMap.put("rows", map.get("rows"));
			reportManageMap.put("pagination", map.get("pagination"));
			resultMap.put("resultCode", map.get("resultCode"));
			resultMap.put("resultMsg", "OK");
			//resultMap.put("data", reportManageMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}
}
