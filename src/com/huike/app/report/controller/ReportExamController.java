package com.huike.app.report.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartframework.common.utils.ExDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.huike.app.report.service.impl.ReportExamService;
import com.huike.app.report.service.impl.ReportFinanceService;
import com.huike.app.report.service.impl.ReportManageService;
import com.huike.app.report.service.impl.ReportServerService;
import com.huike.base.tools.CacheHelp;
import com.huike.base.tools.DateRange;
import com.huike.base.tools.DateUtils;
import com.huike.base.tools.Page;
import com.huike.base.tools.excel.ExcelView;
import com.huike.base.tools.excel.ExportMemberVo;
import com.huike.base.tools.excel.FinanceExcelView;
import com.huike.base.tools.excel.ServerExcelView;
import com.sunshine.common.utils.ExControllerUtils;

/**
 * <p class="detail">
 * 功能：报表审核控制类
 * </p>
 * @ClassName: ReportEaxmController 
 * @version V1.0  
 * @date 2017年6月6日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
@RestController  
@RequestMapping("/api")  
public class ReportExamController {

	Logger log = LoggerFactory.getLogger(ReportExamController.class);

	@Autowired
	@Qualifier("reportExamService")
	ReportExamService reportExamService;
	
	@Autowired
	@Qualifier("reportFinanceService")
	ReportFinanceService reportFinanceService;
	
	@Autowired
	@Qualifier("reportServerService")
	ReportServerService reportServerService;
	
	@Autowired
	@Qualifier("reportManageService")
	ReportManageService reportManageService;
	
	/**
	 * <p class="detail">
	 * 功能：上传人查询已上传的报表
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月7日 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/reportEaxm/list", produces = "application/json")
	public Map getReportEaxmList(HttpServletRequest request) throws IOException {
		Map<String, Object> reportEaxmMap = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取报表列表失败");
		resultMap.put("data", reportEaxmMap);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
			paramMap.put("userId", sessionUser.get("id").toString());
			/*Object idObj = paramMap.get("ids");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg","请输入用户ID");
				return resultMap;
			}*/
			Integer[] status = {0,4};
			paramMap.put("status", status);
			int pageIndex = ExControllerUtils.getPageIndex(paramMap);
			int pageSize = ExControllerUtils.getPageSize(paramMap);
			paramMap.put("offset", new Page(pageSize, pageIndex).getOffset());
			paramMap.put("show", pageSize);
			List<Map<String, Object>> reportEaxmList = reportExamService.findForPageListSearch(paramMap);
			Map<String, Object> map = ExControllerUtils.buildTableData(reportEaxmList, pageIndex, pageSize);
			reportEaxmMap.put("rows", map.get("rows"));
			reportEaxmMap.put("pagination", map.get("pagination"));
			resultMap.put("resultCode", map.get("resultCode"));
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", reportEaxmMap);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：报表审核查询待审核的报表
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月13日 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/reportEaxm/handleList", produces = "application/json")
	public Map gethandleList(HttpServletRequest request) throws IOException {
		Map<String, Object> reportEaxmMap = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取报表列表失败");
		resultMap.put("data", reportEaxmMap);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
			//paramMap.put("userId", sessionUser.get("id").toString());
			String level = sessionUser.get("level").toString();
			if("1".equals(level)){ // 当前用户为乡级时，则传入xiangId，status 1审核中（乡）
				paramMap.put("xiangId", sessionUser.get("xiangId").toString());
				Integer[] status = {1};
				paramMap.put("status", status);
//				paramMap.put("status", "1");
			}
			if("0".equals(level)){ // 当前用户为县级时，则传入status 2审核中（县）
				Integer[] status = {2};
				paramMap.put("status", status);
//				paramMap.put("status", "2");
			}
			if("2".equals(level)){ // // 当前用户为村级时，返回null.
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "村级没有查看权限");
				return resultMap;
			}
			
			int pageIndex = ExControllerUtils.getPageIndex(paramMap);
			int pageSize = ExControllerUtils.getPageSize(paramMap);
			paramMap.put("offset", new Page(pageSize, pageIndex).getOffset());
			paramMap.put("show", pageSize);
			List<Map<String, Object>> reportEaxmList = reportExamService.findForPageListSearch(paramMap);
			Map<String, Object> map = ExControllerUtils.buildTableData(reportEaxmList, pageIndex, pageSize);
			reportEaxmMap.put("rows", map.get("rows"));
			reportEaxmMap.put("pagination", map.get("pagination"));
			resultMap.put("resultCode", map.get("resultCode"));
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", reportEaxmMap);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	
	/**
	 * <p class="detail">
	 * 功能：报表归档查询列表
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月13日 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/reportEaxm/finishedList", produces = "application/json")
	public Map getFinishedList(HttpServletRequest request) throws IOException {
		Map<String, Object> reportEaxmMap = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取报表列表失败");
		resultMap.put("data", reportEaxmMap);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
//			paramMap.put("userId", sessionUser.get("id").toString());
			String level = sessionUser.get("level").toString();
			if("1".equals(level)){ // 当前用户为乡级时，则传入xiangId
				paramMap.put("xiangId", sessionUser.get("xiangId").toString());
			}
			if("2".equals(level)){ // 当前用户为村级时，返回null.
				paramMap.put("cunId", sessionUser.get("cunId").toString());
			}
			Integer[] status = {3};
			paramMap.put("status", status);
//			paramMap.put("status", "3");
			int pageIndex = ExControllerUtils.getPageIndex(paramMap);
			int pageSize = ExControllerUtils.getPageSize(paramMap);
			paramMap.put("offset", new Page(pageSize, pageIndex).getOffset());
			paramMap.put("show", pageSize);
			List<Map<String, Object>> reportEaxmList = reportExamService.findForPageListSearch(paramMap);
			Map<String, Object> map = ExControllerUtils.buildTableData(reportEaxmList, pageIndex, pageSize);
			reportEaxmMap.put("rows", map.get("rows"));
			reportEaxmMap.put("pagination", map.get("pagination"));
			resultMap.put("resultCode", map.get("resultCode"));
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", reportEaxmMap);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：查看要求
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月13日 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/reportEaxm/queryUpdateRequest", produces = "application/json")
	public Map queryUpdateRequest(HttpServletRequest request) throws IOException {
		String updateRequest = "";
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "获取修改要求失败");
		resultMap.put("data", updateRequest);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			if(paramMap.get("id")==null || "".equals(paramMap.get("id").toString())){
				resultMap.put("resultMsg", "请传入记录ID");
				return resultMap;
			}
			updateRequest = reportExamService.queryUpdateRequest(paramMap);
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", updateRequest);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：提交
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月13日 
	 * @param request
	 * @param id 审核id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/back/reportEaxm/submit", produces = "application/json")
	public Map submitReport(HttpServletRequest request) throws IOException {
		Integer result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "提交失败");
		resultMap.put("data", result);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			if(paramMap.get("id")==null || "".equals(paramMap.get("id").toString())){
				resultMap.put("resultMsg", "请传入记录ID");
				return resultMap;
			}
			if(paramMap.get("status")==null || "".equals(paramMap.get("status").toString())){
				resultMap.put("resultMsg", "请传入状态");
				return resultMap;
			}
			if("1".equals(paramMap.get("status").toString()) && (paramMap.get("reportType")==null || "".equals(paramMap.get("reportType").toString()))){
				resultMap.put("resultMsg", "请传入报表类型");
				return resultMap;
			}
			result = reportExamService.modify(paramMap);
			if("1".equals(paramMap.get("status").toString()) && "1".equals(result.toString())){
				// 修改报表备案表
				Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
				Map<String, Object> reportManageMap = new HashMap<String, Object>();
				reportManageMap.put("xiangId", sessionUser.get("xiangId")); // where 条件
				reportManageMap.put("cunId", sessionUser.get("cunId")); // where 条件
				reportManageMap.put("reportType", paramMap.get("reportType").toString()); // where 条件
				// 获取当前时间
				// 获取当前季度
				DateRange currentQuarter = DateUtils.getThisQuarter();
				reportManageMap.put("createTime", ExDateUtils.formatDate(currentQuarter.getStart())); // where 条件
				// 当前时间与当前季度相差天数
				int diff = ExDateUtils.getDiffDay(currentQuarter.getStart(), ExDateUtils.getTodayDate());
				if(diff>5){
					reportManageMap.put("isUpload", "2"); // 大于5天逾期上传
				}else{
					reportManageMap.put("isUpload", "1"); // 小于5天正常上传
				}
				reportManageService.modify(reportManageMap);
			}
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
	 * 导出到excel
	 */
    @RequestMapping(value = "/reportEaxm/downExcel", produces = "application/json")
    public ModelAndView exportOrderSecureList(HttpServletRequest request) throws IOException{
    	
/*    	Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "下载失败");
		resultMap.put("data", excelView);
		
		Map paramMap = ExControllerUtils.buildParametersMap(request);
		if(paramMap.get("id")==null || "".equals(paramMap.get("id").toString())){
			resultMap.put("resultMsg", "请传入记录ID");
			return resultMap;
		}
		if(paramMap.get("reporttype")==null || "".equals(paramMap.get("reporttype").toString())){
			resultMap.put("resultMsg", "请传入报表类型");
			return resultMap;
		}*/
    	Map paramMap = ExControllerUtils.buildParametersMap(request);
    	ExcelView excelView = null;
		// 获取excel数据
		Map<String, Object> report = new HashMap<String, Object>();
		Long id = Long.valueOf(paramMap.get("id").toString());
		if("1".equals(paramMap.get("reporttype").toString())){
			report = reportFinanceService.queryById(id);
			report.put("name","财务报表");
			excelView = new FinanceExcelView();
	        return new ModelAndView(excelView, report);
		}
		if("2".equals(paramMap.get("reporttype").toString())){
			report = reportServerService.queryById(id);
			report.put("name","服务报表");
			excelView = new ServerExcelView();
		}
	/*	resultMap.put("resultCode", 0);
		resultMap.put("resultMsg", "OK");
		return resultMap;*/
		
        return new ModelAndView(excelView, report);
	}
}
