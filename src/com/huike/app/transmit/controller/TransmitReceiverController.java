package com.huike.app.transmit.controller;

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

import com.huike.app.transmit.service.impl.TransmitReceiverService;
import com.huike.base.tools.CacheHelp;
import com.huike.base.tools.Page;
import com.sunshine.common.utils.ExControllerUtils;

@RestController
@RequestMapping("/api")
public class TransmitReceiverController {

	Logger log = LoggerFactory.getLogger(TransmitReceiverController.class);

	@Autowired
	@Qualifier("transmitReceiverService")
	TransmitReceiverService transmitReceiverService;
	
	/**
	 * <p class="detail">
	 * 功能：根据收件人查询收文分页列表
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月2日 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/transmitReceiver/list", produces = "application/json")
	public Map receiverList(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "查询收件列表失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			int pageIndex = ExControllerUtils.getPageIndex(params);
			int pageSize = ExControllerUtils.getPageSize(params);
			params.put("offset", new Page(pageSize, pageIndex).getOffset());
			params.put("show", pageSize);
			Map<String, Object> sessionUser = (Map<String, Object>) CacheHelp.SESSION_USER_MAP.get(request.getParameter("sessionId"));
			params.put("receiverId", sessionUser.get("id").toString());
			/*if (params.get("receiverId") == null || ExStringUtils.isBlank(params.get("receiverId").toString())) {
				resultMap.put("resultMsg", "请输入收件人ID");
				return resultMap;
			}*/
			List transmitList = transmitReceiverService.findForPageListSearch(params);
			Map<String, Object> map = ExControllerUtils.buildTableData(transmitList, pageIndex, pageSize);
			result.put("rows", map.get("rows"));
			result.put("pagination", map.get("pagination"));
			resultMap.put("resultCode", map.get("resultCode"));
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", result);
		} catch (Exception e) {
			log.info("查询收件列表失败:"+e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * <p class="detail">
	 * 功能：查看阅件情况
	 * </p>
	 * @author Zerlinda
	 * @date 2017年6月2日 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/transmitReceiver/readDetail", produces = "application/json")
	public Map readDetail(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		resultMap.put("resultMsg", "查询阅件情况失败");
		resultMap.put("data", result);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			int pageIndex = ExControllerUtils.getPageIndex(params);
			int pageSize = ExControllerUtils.getPageSize(params);
			if (params.get("id") == null || ExStringUtils.isBlank(params.get("id").toString())) {
				resultMap.put("resultMsg", "请发件ID");
				return resultMap;
			}
			Integer transmitId = Integer.parseInt(params.get("id").toString());
			List readName = transmitReceiverService.readDetail(transmitId);
			List unReadName = transmitReceiverService.unReadDetail(transmitId);
			result.put("readName", getValues(readName,"readName"));
			result.put("unReadName", getValues(unReadName,"unReadName"));
			resultMap.put("resultMsg", "OK");
			resultMap.put("data", result);
		} catch (Exception e) {
			log.info("查询收件列表失败:"+e.getMessage());
			return resultMap;
		}
		return resultMap;
	}
	
	private List getValues(List list, String key){
		List values = new ArrayList();
		for(int i = 0; i< list.size(); i++){
			Map temp = (Map)list.get(i);
			values.add(temp.get(key));
		}
		return values;
	}
}
