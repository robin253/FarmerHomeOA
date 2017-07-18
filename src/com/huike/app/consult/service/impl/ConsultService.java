package com.huike.app.consult.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.huike.app.consult.service.IConsultService;
import com.huike.base.except.DaoException;
import com.huike.base.service.BasicServiceSupport;

@Service("consultService")
public class ConsultService extends BasicServiceSupport implements IConsultService {

	public ConsultService(){
		super("com.huike.app.consult.service.IConsultService");
	}
	
	/** 取得当天最大查询号 */
	public String selectMaxId(String startTime){
		String result = "";
		try {
			result = (String) getBasicDao().queryOne(getAllSpaceName("selectMaxId"), startTime);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public <T> Map queryUserDetailById(T id) {
		Map result = new HashMap();
		try {
			result = getBasicDao().queryOne(getAllSpaceName("selectUserDetailByPrimaryKey"), id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 查询出最后期限尚未办理完成的事项 */
	public List<Map<String, Object>> unHandleMessage(Map<String, Object> params){
		List<Map<String, Object>> consultList = new ArrayList<Map<String, Object>>();
		try {
			consultList = getBasicDao().query(getAllSpaceName("unHandleMessage"), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return consultList;
	}
}
