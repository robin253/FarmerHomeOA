package com.huike.app.report.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartframework.common.utils.ExDateUtils;
import org.smartframework.common.utils.ExStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huike.app.report.service.impl.ReportExamService;
import com.huike.app.report.service.impl.ReportFinanceService;
import com.huike.app.report.service.impl.ReportManageService;
import com.huike.base.tools.CacheHelp;
import com.huike.base.tools.DateRange;
import com.huike.base.tools.DateUtils;
import com.sunshine.common.utils.ExControllerUtils;

/**
 * <p class="detail">
 * 功能：财务报表控制类
 * </p>
 * @ClassName: ReportFinanceController 
 * @version V1.0  
 * @date 2017年6月7日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
@RestController  
@RequestMapping("/api") 
public class ReportFinanceController {

	Logger log = LoggerFactory.getLogger(ReportFinanceController.class);

	@Autowired
	@Qualifier("reportFinanceService")
	ReportFinanceService reportFinanceService;
	
	@Autowired
	@Qualifier("reportExamService")
	ReportExamService reportExamService;
	
	/**
	 * <p class="detail">
	 * 功能：财务报表填写(保存)
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月13日 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/reportFinance/save", produces = "application/json")
	public Map save(HttpServletRequest request) {
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "保存财务报表失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			
			Map reportFinanceParams = new HashMap();
			if (params.get("id") == null || ExStringUtils.isBlank(params.get("id").toString())) {
				if (params.get("title") == null || ExStringUtils.isBlank(params.get("title").toString())) {
					resultMap.put("resultMsg","请输入报表标题");
					return resultMap;
				}
				String reportFinance = params.get("reportFinance")==null?"":params.get("reportFinance").toString();
				if(ExStringUtils.isBlank(reportFinance)){
					resultMap.put("resultMsg", "请传入财务报表数据！");
					return resultMap;
				}else{
					String[] reportFinances = StringUtils.split(reportFinance.toString(), ",");
					reportFinanceParams.put("reportFinance", reportFinances);
				}
				result = reportFinanceService.save(reportFinanceParams);
				log.info(reportFinanceParams.get("id").toString());
				// 新增报表审核记录
				Map<String, Object> reportExamMap = new HashMap<String, Object>();
				
				Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
				reportExamMap.put("userId", sessionUser.get("id"));
				reportExamMap.put("userName", sessionUser.get("username"));
				reportExamMap.put("xiangName", sessionUser.get("xiangName"));
				reportExamMap.put("xiangId", sessionUser.get("xiangId"));
				reportExamMap.put("cunName", sessionUser.get("cunName"));
				reportExamMap.put("cunId", sessionUser.get("cunId"));
				reportExamMap.put("reportId", reportFinanceParams.get("id").toString());
				reportExamMap.put("reportName", params.get("title").toString());
				reportExamMap.put("status", "0");
				reportExamMap.put("reportType", params.get("reportType").toString());
				reportExamService.save(reportExamMap);
			} else {
				//reportFinanceParams.put("id",params.get("id").toString());
				result = reportFinanceService.modify(params);
				Map<String, Object> reportExamMap = new HashMap<String, Object>();
				reportExamMap.put("reportId", params.get("id").toString());
				reportExamMap.put("reportType", params.get("reportType").toString());
				reportExamMap.put("reportName", params.get("title").toString());
				reportExamService.modify(reportExamMap);
			} 
			if (result > 0) {
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "OK");
				resultMap.put("data", result);
			}
		} catch (Exception e) {
			log.info("保存财务报表信息报错：" + e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：查看详情
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月13日 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/reportFinance/detail", produces = "application/json")
	public Map queryById(HttpServletRequest request) {
		Map<String, Object> financeMap = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "查看详情失败");
		resultMap.put("data", financeMap);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			if (params.get("reportId") == null || ExStringUtils.isBlank(params.get("reportId").toString())) {
				resultMap.put("resultMsg","请输入报表ID");
				return resultMap;
			}
			Long id = Long.valueOf(params.get("reportId").toString());
			Map<String, Object> queryMap = reportFinanceService.queryById(id);
			if(queryMap != null){
				financeMap = queryMap;
			}
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", financeMap);
		} catch (Exception e) {
			log.info("保存财务报表信息报错：" + e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
}
