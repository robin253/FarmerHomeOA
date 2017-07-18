package com.huike.app.report.controller;

import java.util.HashMap;
import java.util.List;
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
import com.huike.app.report.service.impl.ReportManageService;
import com.huike.app.report.service.impl.ReportServerService;
import com.huike.base.tools.CacheHelp;
import com.huike.base.tools.DateRange;
import com.huike.base.tools.DateUtils;
import com.sunshine.common.utils.ExControllerUtils;

/**
 * <p class="detail">
 * 功能：服务报表控制类
 * </p>
 * @ClassName: ReportServerController 
 * @version V1.0  
 * @date 2017年6月14日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
@RestController  
@RequestMapping("/api") 
public class ReportServerController {
	Logger log = LoggerFactory.getLogger(ReportServerController.class);

	@Autowired
	@Qualifier("reportServerService")
	ReportServerService reportServerService;
	
	@Autowired
	@Qualifier("reportExamService")
	ReportExamService reportExamService;
	
	@Autowired
	@Qualifier("reportManageService")
	ReportManageService reportManageService;
	
	/**
	 * <p class="detail">
	 * 功能：服务报表填写(保存)
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月15日 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/reportServer/save", produces = "application/json")
	public Map save(HttpServletRequest request) {
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "保存服务报表失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Map reportServerParams = new HashMap();
			if (params.get("id") == null || ExStringUtils.isBlank(params.get("id").toString())) {
				if (params.get("title") == null || ExStringUtils.isBlank(params.get("title").toString())) {
					resultMap.put("resultMsg","请输入报表标题");
					return resultMap;
				}
				if (params.get("reportType") == null || ExStringUtils.isBlank(params.get("reportType").toString())) {
					resultMap.put("resultMsg","请输入报表类型");
					return resultMap;
				}
				String reportServer = params.get("reportServer")==null?"":params.get("reportServer").toString();
				if(ExStringUtils.isBlank(reportServer)){
					resultMap.put("resultMsg", "请传入服务报表数据！");
					return resultMap;
				}else{
					String[] reportServers = StringUtils.split(reportServer.toString(), ",");
					reportServerParams.put("reportServer", reportServers);
				}
				result = reportServerService.save(reportServerParams);
				log.info(reportServerParams.get("id").toString());
				// 新增报表审核记录
				Map<String, Object> reportExamMap = new HashMap<String, Object>();
				
				Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
				reportExamMap.put("userId", sessionUser.get("id"));
				reportExamMap.put("userName", sessionUser.get("username"));
				reportExamMap.put("xiangName", sessionUser.get("xiangName"));
				reportExamMap.put("xiangId", sessionUser.get("xiangId"));
				reportExamMap.put("cunName", sessionUser.get("cunName"));
				reportExamMap.put("cunId", sessionUser.get("cunId"));
				reportExamMap.put("reportId", reportServerParams.get("id").toString());
				reportExamMap.put("reportName", params.get("title").toString());
				reportExamMap.put("status", "0");
				reportExamMap.put("reportType", params.get("reportType").toString());
				reportExamService.save(reportExamMap);
			} else {
//				reportServerParams.put("id",params.get("id").toString());
				result = reportServerService.modify(params);
				
				Map<String, Object> reportExamMap = new HashMap<String, Object>();
				reportExamMap.put("reportId", params.get("id").toString());
				reportExamMap.put("reportType", params.get("reportType").toString());
				reportExamMap.put("reportName", params.get("title").toString());
				reportExamService.modify(reportExamMap);
			}
			if(result > 0) {
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
	@RequestMapping(value = "/back/reportServer/detail", produces = "application/json")
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
			Map<String, Object> queryMap = reportServerService.queryById(id);
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
	
	/** 报表统计 */
	@RequestMapping(value = "/back/reportServer/statistics", produces = "application/json")
	public Map statistics(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		// 咨询受理
		Map<String, Object> charts = new HashMap<String, Object>();
		// 咨询办结
		Map<String, Object> components = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "提交失败");
		resultMap.put("data", result);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			String type = paramMap.get("type")==null?"":paramMap.get("type").toString();
			String year = paramMap.get("year")==null?"":paramMap.get("year").toString();
			String season = paramMap.get("season")==null?"":paramMap.get("season").toString();
			if(ExStringUtils.isBlank(type)){
				resultMap.put("resultMsg", "请选择类型");
				return resultMap;
			}
			if(ExStringUtils.isBlank(year)){
				resultMap.put("resultMsg", "请选择年份");
				return resultMap;
			}
			Map<String, String> createTime = DateUtils.getYearSeasonRange(year,season);
			paramMap.put("startTime", createTime.get("start"));
			paramMap.put("endTime", createTime.get("end"));
			
			String [] fwlx_sl = new String[]{};
			String [] fwlx_bj = new String[]{};
			// 咨询受理
			String [] zxsl = {"就业服务咨询受理","创业融资咨询受理","法律服务咨询受理","政策服务咨询受理","项目申办咨询受理","专家指导咨询受理","技能培训咨询受理","保险业务咨询受理","气象信息咨询受理","供需信息咨询受理"};
			// 咨询办结
			String [] zxbj = {"就业服务咨询办结","创业融资咨询办结","法律服务咨询办结","政策服务咨询办结","项目申办咨询办结","专家指导咨询办结","技能培训咨询办结","保险业务咨询办结","气象信息咨询办结","供需信息咨询办结"};
			// 代办受理
			String [] dbsl = {"就业服务代办受理","创业融资代办受理","法律服务代办受理","政策服务代办受理","项目申办代办受理","专家指导代办受理","技能培训代办受理","保险业务代办受理","气象信息代办受理","供需信息代办受理"};
			// 代办办结
			String [] dbbj = {"就业服务代办办结","创业融资代办办结","法律服务代办办结","政策服务代办办结","项目申办代办办结","专家指导代办办结","技能培训代办办结","保险业务代办办结","气象信息代办办结","供需信息代办办结"};
			// 其他受理
			String [] qtsl = {"就业服务其他受理","创业融资其他受理","法律服务其他受理","政策服务其他受理","项目申办其他受理","专家指导其他受理","技能培训其他受理","保险业务其他受理","气象信息其他受理","供需信息其他受理"};
			// 其他办结
			String [] qtbj = {"就业服务其他办结","创业融资其他办结","法律服务其他办结","政策服务其他办结","项目申办其他办结","专家指导代办办结","技能培训其他办结","保险业务其他办结","气象信息其他办结","供需信息其他办结"};
			if(paramMap.get("type")!=null && ExStringUtils.isNotBlank(paramMap.get("type").toString())){
				switch (paramMap.get("type").toString()){
				case "1" : fwlx_sl = zxsl; // 1 咨询
						   fwlx_bj = zxbj;
				break;
				case "2" : fwlx_sl = dbsl; // 2代办
				   		   fwlx_bj = dbbj;
				break;
				case "3" : fwlx_sl = qtsl; // 3其他
		   		   		   fwlx_bj = qtbj;
				break;
				}
			}
			// 查询
			List<Map<String, Object>> reportList = reportServerService.statistics(paramMap);
			if(reportList!=null && reportList.size()!=0){
				
				Map<String, Object> temp = reportList.get(0);
				
				Integer max = 100;
				Integer employmentAccpet = Integer.parseInt(temp==null?"0":((temp.get("employmentAccpet")=="")?"0":temp.get("employmentAccpet").toString()));
				charts.put(fwlx_sl[0], employmentAccpet);
				max = employmentAccpet;
				
				Integer businessAccpet = Integer.parseInt(temp==null?"0":((temp.get("businessAccpet")=="")?"0":temp.get("businessAccpet").toString()));
				charts.put(fwlx_sl[1], businessAccpet);
				if(businessAccpet > max){
					max = businessAccpet;
				}
				
				Integer lawAccpet = Integer.parseInt(temp==null?"0":((temp.get("lawAccpet")=="")?"0":temp.get("lawAccpet").toString()));
				charts.put(fwlx_sl[2], lawAccpet);
				if(lawAccpet > max){
					max = lawAccpet;
				}
				
				Integer policyAccpet = Integer.parseInt(temp==null?"0":((temp.get("policyAccpet")=="")?"0":temp.get("policyAccpet").toString()));
				charts.put(fwlx_sl[3], policyAccpet);
				if(policyAccpet > max){
					max = policyAccpet;
				}
				
				Integer projectAccpet = Integer.parseInt(temp==null?"0":((temp.get("projectAccpet")=="")?"0":temp.get("projectAccpet").toString()));
				charts.put(fwlx_sl[4], projectAccpet);
				if(policyAccpet > max){
					max = policyAccpet;
				}
				
				Integer professorAccpet = Integer.parseInt(temp==null?"0":((temp.get("professorAccpet")=="")?"0":temp.get("professorAccpet").toString()));
				charts.put(fwlx_sl[5], professorAccpet);
				if(professorAccpet > max){
					max = professorAccpet;
				}
				
				Integer trainAccpet = Integer.parseInt(temp==null?"0":((temp.get("trainAccpet")=="")?"0":temp.get("trainAccpet").toString()));
				charts.put(fwlx_sl[6], trainAccpet);
				if(trainAccpet > max){
					max = trainAccpet;
				}
				
				Integer insuranceAccpet = Integer.parseInt(temp==null?"0":((temp.get("insuranceAccpet")=="")?"0":temp.get("insuranceAccpet").toString()));
				charts.put(fwlx_sl[7], insuranceAccpet);
				if(insuranceAccpet > max){
					max = insuranceAccpet;
				}
				
				Integer weatherAccpet = Integer.parseInt(temp==null?"0":((temp.get("weatherAccpet")=="")?"0":temp.get("weatherAccpet").toString()));
				charts.put(fwlx_sl[8], weatherAccpet);
				if(insuranceAccpet > max){
					max = weatherAccpet;
				}
				
				Integer supplyAccpet = Integer.parseInt(temp==null?"0":((temp.get("supplyAccpet")=="")?"0":temp.get("supplyAccpet").toString()));
				charts.put(fwlx_sl[9], supplyAccpet);
				if(supplyAccpet > max){
					max = supplyAccpet;
				}
				result.put("charts", charts);
				
				Integer employmentDeal = Integer.parseInt(temp==null?"0":((temp.get("employmentDeal")=="")?"0":temp.get("employmentDeal").toString()));
				components.put(fwlx_bj[0], employmentDeal);
				if(employmentDeal > max){
					max = employmentDeal;
				}
				
				Integer businessDeal = Integer.parseInt(temp==null?"0":((temp.get("businessDeal")=="")?"0":temp.get("businessDeal").toString()));
				components.put(fwlx_bj[1], businessDeal);
				if(businessDeal > max){
					max = businessDeal;
				}
				
				Integer lawDeal = Integer.parseInt(temp==null?"0":((temp.get("lawDeal")=="")?"0":temp.get("lawDeal").toString()));
				components.put(fwlx_bj[2], temp==null?"0":temp.get("lawDeal"));
				if(lawDeal > max){
					max = lawDeal;
				}
				
				Integer policyDeal = Integer.parseInt(temp==null?"0":((temp.get("policyDeal")=="")?"0":temp.get("policyDeal").toString()));
				components.put(fwlx_bj[3], policyDeal);
				if(policyDeal > max){
					max = policyDeal;
				}
				
				Integer projectDeal = Integer.parseInt(temp==null?"0":((temp.get("projectDeal")=="")?"0":temp.get("projectDeal").toString()));
				components.put(fwlx_bj[4], temp==null?"0":temp.get("projectDeal"));
				if(projectDeal > max){
					max = projectDeal;
				}
				
				Integer professorDeal = Integer.parseInt(temp==null?"0":((temp.get("professorDeal")=="")?"0":temp.get("professorDeal").toString()));
				components.put(fwlx_bj[5], professorDeal);
				if(professorDeal > max){
					max = professorDeal;
				}
				
				Integer trainDeal = Integer.parseInt(temp==null?"0":((temp.get("trainDeal")=="")?"0":temp.get("trainDeal").toString()));
				components.put(fwlx_bj[6], trainDeal);
				if(trainDeal > max){
					max = trainDeal;
				}
				
				Integer insuranceDeal = Integer.parseInt(temp==null?"0":((temp.get("insuranceDeal")=="")?"0":temp.get("insuranceDeal").toString()));
				components.put(fwlx_bj[7], insuranceDeal);
				if(insuranceDeal > max){
					max = insuranceDeal;
				}
				
				Integer weatherDeal = Integer.parseInt(temp==null?"0":((temp.get("weatherDeal")=="")?"0":temp.get("weatherDeal").toString()));
				components.put(fwlx_bj[8], weatherDeal);
				if(weatherDeal > max){
					max = weatherDeal;
				}
				
				Integer supplyDeal = Integer.parseInt(temp==null?"0":((temp.get("supplyDeal")=="")?"0":temp.get("supplyDeal").toString()));
				components.put(fwlx_bj[9], supplyDeal);
				if(supplyDeal > max){
					max = supplyDeal;
				}
				result.put("components", components);
				result.put("max", max+10);
			}
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	
    public static int getMax(int[] values)  
    {  
        int tmp=Integer.MIN_VALUE;  
          
        if(null!=values)  
        {  
            tmp=values[0];  
            for(int i=0; i<values.length; i++)  
            {  
                if(tmp<values[i])  
                {  
                    tmp = values[i];  
                }  
            }  
        }  
          
        return tmp;       
    } 
	
	/**
	 * 服务报表下载
	 */
}
