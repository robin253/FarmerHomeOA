package com.huike.app.consult.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartframework.common.utils.ExDateUtils;
import org.smartframework.common.utils.ExStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huike.app.consult.service.impl.ConsultService;
import com.huike.app.system.model.SmsSendOrder;
import com.huike.app.system.service.impl.UserService;
import com.huike.base.tools.CacheHelp;
import com.huike.base.tools.Page;
import com.huike.base.tools.sms.SendMessageService;
import com.sunshine.common.utils.ExControllerUtils;

/**
 * <p class="detail">
 * 功能：咨询控制类 
 * </p>
 * @ClassName: ConsultController 
 * @version V1.0  
 * @date 2017年5月27日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
@RestController  
@RequestMapping("/api")  
public class ConsultController {
	
	Logger log = LoggerFactory.getLogger(ConsultController.class);
	
	@Autowired
	@Qualifier("consultService")
	ConsultService consultService;
	
	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	@Autowired
	SendMessageService sendMessageService;

	/**
	 * <p class="detail">
	 * 功能：官网留言咨询
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月27日 
	 * @param request
	 * @param consultQuestion 咨询问题	必填
	 * @param consultantAddress 咨询人地址  选填
	 * @param consultantMail 咨询人邮箱  选填
	 * @param consultantName 咨询人姓名	必填
	 * @param consultantPhone 咨询人手机号  必填
	 * @param isPublic 	是否公开（0公开，1不公开）  选填，默认公开
	 * @return
	 */
	@RequestMapping(value = "/consult/save", produces = "application/json")
	public Map save(HttpServletRequest request) {
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "提交咨询失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			if (params.get("consultQuestion") == null || ExStringUtils.isBlank(params.get("consultQuestion").toString())) {
				resultMap.put("resultMsg","请输入咨询的内容");
				return resultMap;
			}
			if (params.get("consultantName") == null || ExStringUtils.isBlank(params.get("consultantName").toString())) {
				resultMap.put("resultMsg","请输入姓名");
				return resultMap;
			}
			if (params.get("consultantPhone") == null || ExStringUtils.isBlank(params.get("consultantPhone").toString())) {
				resultMap.put("resultMsg","请输入联系方式");
				return resultMap;
			}
			// 生成查询号（生成规则yyyymmdd+有序数）
			String today = ExDateUtils.format(new Date(), "yyyyMMdd");
			String maxId = consultService.selectMaxId(today);
			String tail = ("0".equals(maxId))?"1":Integer.parseInt(consultService.selectMaxId(today).substring(8))+1+"";
			String id = today + tail;
			params.put("id", id);
			result = consultService.save(params);
			if(result > 0){
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "OK");
				resultMap.put("data", id);
			}
		} catch (Exception e) {
			log.info("提交留言咨询报错："+e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：官网根据查询号查询咨询情况
	 * </p>
	 * 
	 * @author Zerlinda
	 * @date 2017年5月27日
	 * @param request
	 * @param id 查询号（必传）
	 * @return
	 */
	@RequestMapping(value = "/consult/detail", produces = "application/json")
	public Map queryDetail(HttpServletRequest request) {
		Map<String, Object> consultant = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "查询咨询详情失败");
		resultMap.put("data", consultant);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Object idObj = params.get("id");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg", "请输入查询号");
				return resultMap;
			}
			consultant = consultService.queryById(idObj.toString());
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", consultant);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：历史咨询信息
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月31日 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/consult/list", produces = "application/json")
	public Map getConsultList(HttpServletRequest request) throws IOException {
		Map<String, Object> consultMap = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "查询历史咨询失败");
		resultMap.put("data", consultMap);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			int pageIndex = ExControllerUtils.getPageIndex(paramMap);
			int pageSize = ExControllerUtils.getPageSize(paramMap);
			paramMap.put("offset", new Page(pageSize, pageIndex).getOffset());
			paramMap.put("show", pageSize);
			paramMap.put("isPublic", 0);
			List<Map<String, Object>> consultList = consultService.findForPageListSearch(paramMap);
			Map<String, Object> map = ExControllerUtils.buildTableData(consultList, pageIndex, pageSize);
			consultMap.put("rows", map.get("rows"));
			consultMap.put("pagination", map.get("pagination"));
			resultMap.put("resultCode", map.get("resultCode"));
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", consultMap);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：用户对办理结果评价
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月31日 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/consult/evaluate", produces = "application/json")
	public Map evaluate(HttpServletRequest request) {
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "评价失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			if (params.get("id") == null || ExStringUtils.isBlank(params.get("id").toString())) {
				resultMap.put("resultMsg","请请传入查询号");
				return resultMap;
			}
			if (params.get("handleFeedback") == null || ExStringUtils.isBlank(params.get("handleFeedback").toString())) {
				resultMap.put("resultMsg","请传入评价结果");
				return resultMap;
			}
			result = consultService.modify(params);
			if(result > 0){
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "OK");
				resultMap.put("data", result);
			}
		} catch (Exception e) {
			log.info("评价报错："+e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * 事项列表
	 * 县级：查看，分配，短信提醒
	 * 部级 乡级：答复办理
	 */
	@RequestMapping(value = "/back/consult/handleList", produces = "application/json")
	public Map getHandleList(HttpServletRequest request) throws IOException {
		Map<String, Object> consultMap = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "查询事项列表失败");
		resultMap.put("data", consultMap);
		try {
			Map paramMap = ExControllerUtils.buildParametersMap(request);
			Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
			
			// 创建时间 start <= createTime<= end 同时为null或者同时不为null
			// 咨询人姓名 模糊查询
			// 办理状态
			// 满意度
			// 当前用户
			// 县级可看所有
			// 其余则只能查看他自己的。
			if(!"0".equals(sessionUser.get("level").toString())){
				paramMap.put("handleUserId", sessionUser.get("id").toString());
			}
			int pageIndex = ExControllerUtils.getPageIndex(paramMap);
			int pageSize = ExControllerUtils.getPageSize(paramMap);
			paramMap.put("offset", new Page(pageSize, pageIndex).getOffset());
			paramMap.put("show", pageSize);
			List<Map<String, Object>> consultList = consultService.findForPageListSearchByKey(paramMap, "handlePageList");
			Map<String, Object> map = ExControllerUtils.buildTableData(consultList, pageIndex, pageSize);
			consultMap.put("rows", map.get("rows"));
			consultMap.put("pagination", map.get("pagination"));
			resultMap.put("resultCode", map.get("resultCode"));
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", consultMap);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：后台根据查询号查询咨询情况详情
	 * </p>
	 * 
	 * @author Zerlinda
	 * @date 2017年5月27日
	 * @param request
	 * @param id 查询号（必传）
	 * @return
	 */
	@RequestMapping(value = "/back/consult/detail", produces = "application/json")
	public Map queryUserDetail(HttpServletRequest request) {
		Map<String, Object> consultant = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "查询咨询详情失败");
		resultMap.put("data", consultant);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Object idObj = params.get("id");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg", "请输入查询号");
				return resultMap;
			}
			consultant = consultService.queryUserDetailById(idObj.toString());
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", consultant);
		} catch (Exception e) {
			log.info(e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：交办
	 * </p>
	 * 
	 * @author Zerlinda
	 * @date 2017年5月27日
	 * @param request
	 * @param id 查询号（必传）
	 * @return
	 */
	@RequestMapping(value = "/back/consult/assign", produces = "application/json")
	public Map assign(HttpServletRequest request) {
		Map resultMap = new HashMap();
		int result = 0;
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "交办失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Object idObj = params.get("id");
			Object handleUserIdObj = params.get("handleUserId");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg", "请输入记录ID");
				return resultMap;
			}
			if (handleUserIdObj == null || ExStringUtils.isBlank(handleUserIdObj.toString())) {
				resultMap.put("resultMsg", "请输入交办对象");
				return resultMap;
			}
			params.put("handleStatus", 2);
			result = consultService.modify(params);
			// TODO 发送提醒短信
			// 获取交办对象手机号
			Long userId = Long.valueOf(handleUserIdObj.toString());
			// 获取交办对象手机号
			String phone = userService.queryById(userId).get("phone").toString();
			// 发送短信
			SmsSendOrder sso = new SmsSendOrder();
			sso.setReceiverPhone(phone);
			sso.setSmsTempid(CacheHelp.SYS_CONFIG_MAP.get("sms_ytx_tempid_4").toString());
			//new SendMessageService(sso);
//			new Thread(sendMessageService).start();
			sendMessageService.run(sso);
			// TODO 6天后再次提醒
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
	 * 功能：办理
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月1日 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/consult/handle", produces = "application/json")
	public Map handle(HttpServletRequest request){
		Map resultMap = new HashMap();
		int result = 0;
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "办理失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Object idObj = params.get("id");
			Object handleResultObj = params.get("handleResult");
			if (idObj == null || ExStringUtils.isBlank(idObj.toString())) {
				resultMap.put("resultMsg", "请输入记录ID");
				return resultMap;
			}
			if (handleResultObj == null || ExStringUtils.isBlank(handleResultObj.toString())) {
				resultMap.put("resultMsg", "请输入问题办理内容");
				return resultMap;
			}
			params.put("handleStatus", 3);
			result = consultService.modify(params);
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
