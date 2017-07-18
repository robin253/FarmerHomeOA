package com.huike.app.transmit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartframework.common.utils.ExStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huike.app.system.model.SmsSendOrder;
import com.huike.app.system.service.impl.UserService;
import com.huike.app.transmit.service.impl.TransmitReceiverService;
import com.huike.app.transmit.service.impl.TransmitService;
import com.huike.base.tools.CacheHelp;
import com.huike.base.tools.ListUtil;
import com.huike.base.tools.Page;
import com.huike.base.tools.sms.SendMessageService;
import com.sunshine.common.utils.ExControllerUtils;

@RestController
@RequestMapping("/api")
public class TransmitController {
	Logger log = LoggerFactory.getLogger(TransmitController.class);

	@Autowired
	@Qualifier("transmitService")
	TransmitService transmitService;
	
	@Autowired
	@Qualifier("transmitReceiverService")
	TransmitReceiverService transmitReceiverService;
	
	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	@Autowired
	SendMessageService sendMessageService;
	
	
	/**
	 * <p class="detail">
	 * 功能：发送文件
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月1日 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/transmit/save", produces = "application/json")
	@Transactional(rollbackFor = Exception.class)  
	public Map save(HttpServletRequest request){
		int result = 0;
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "发送文件失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			if (params.get("receiver") == null || ExStringUtils.isBlank(params.get("receiver").toString())) {
				resultMap.put("resultMsg", "请输入收件人ID");
				return resultMap;
			}
			if (params.get("receiverName") == null || ExStringUtils.isBlank(params.get("receiverName").toString())) {
				resultMap.put("resultMsg", "请输入收件人姓名");
				return resultMap;
			}
			if (params.get("title") == null || ExStringUtils.isBlank(params.get("title").toString())) {
				resultMap.put("resultMsg", "请输入标题");
				return resultMap;
			}
			if (params.get("content") == null || ExStringUtils.isBlank(params.get("content").toString())) {
				resultMap.put("resultMsg", "请输入正文");
				return resultMap;
			}
			// 获取当前用户ID 
			Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
			params.put("sender",sessionUser.get("id").toString());
			result = transmitService.save(params);
			
			if (result > 0) {
				String transmitId = params.get("id").toString();
//				Integer transmitId = transmitService.maxId();
				log.info("发文ID为："+transmitId);
				// 插入收件人
				Map<String, Object> transmitReceiverMap = new HashMap<String, Object>();
				transmitReceiverMap.put("transmitId", transmitId);
				List<Integer> receivers = ListUtil.handleString2Array(params.get("receiver").toString(), ",");
				for(int i=0; i<receivers.size(); i++){
					Long receiverId = Long.valueOf(receivers.get(i).toString());
					transmitReceiverMap.put("receiverId", receiverId);
					Map userMap = userService.queryById(receiverId);
					transmitReceiverMap.put("receiverName", userMap.get("username"));
					transmitReceiverMap.put("receiverPhone", userMap.get("phone"));
					transmitReceiverService.save(transmitReceiverMap);
					
					// 给收文人发送提醒短信
					SmsSendOrder sso = new SmsSendOrder();
					sso.setReceiverPhone(userMap.get("phone").toString());
					sso.setSmsTempid(CacheHelp.SYS_CONFIG_MAP.get("sms_ytx_tempid_1").toString());
					
					//new SendMessageService(sso);
//					new Thread(sendMessageService).start();
					sendMessageService.run(sso);
					
					
				}
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "OK");
				resultMap.put("data", result);
			}
		} catch (Exception e) {
			log.info("发送文件报错：" + e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：查看详情
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月1日 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/transmit/detail", produces = "application/json")
	@Transactional(rollbackFor = Exception.class)  
	public Map detail(HttpServletRequest request){
		Map result = new HashMap();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "查看详情失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			if (params.get("id") == null || ExStringUtils.isBlank(params.get("id").toString())) {
				resultMap.put("resultMsg", "请输入发文ID");
				return resultMap;
			}
			Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
			params.put("receiver", sessionUser.get("id").toString());
			/*if (params.get("receiver") == null || ExStringUtils.isBlank(params.get("receiver").toString())) {
				resultMap.put("resultMsg", "请输入收件人ID");
				return resultMap;
			}*/
			// 查看文件详情
			Long id = Long.valueOf(params.get("id").toString());
			result = transmitService.queryById(id);
			// 修改阅件情况
			Map<String, Object> transmitReceiverMap = new HashMap<String, Object>();
			transmitReceiverMap.put("transmitId", id);
			transmitReceiverMap.put("receiverId", params.get("receiver"));
			transmitReceiverService.modify(transmitReceiverMap);
			
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", result);
		} catch (Exception e) {
			log.info("查看详情报错：" + e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：根据发件人查询收文分页列表
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月2日 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/transmit/list", produces = "application/json")
	public Map receiverList(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "查询发件列表失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			int pageIndex = ExControllerUtils.getPageIndex(params);
			int pageSize = ExControllerUtils.getPageSize(params);
			params.put("offset", new Page(pageSize, pageIndex).getOffset());
			params.put("show", pageSize);
			
			Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
			params.put("sender", sessionUser.get("id").toString());
			/*if (params.get("sender") == null || ExStringUtils.isBlank(params.get("sender").toString())) {
				resultMap.put("resultMsg", "请输入发件人ID");
				return resultMap;
			}*/
			List transmitList = transmitService.findForPageListSearch(params);
			Map<String, Object> map = ExControllerUtils.buildTableData(transmitList, pageIndex, pageSize);
			result.put("rows", map.get("rows"));
			result.put("pagination", map.get("pagination"));
			resultMap.put("resultCode", map.get("resultCode"));
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", result);
		} catch (Exception e) {
			log.info("查询发件列表错误:"+e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
}
