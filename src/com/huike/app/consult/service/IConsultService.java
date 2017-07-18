package com.huike.app.consult.service;

import java.util.List;
import java.util.Map;

import com.huike.base.service.IBasicService;

public interface IConsultService extends IBasicService {
	
	/** 取得当天最大查询号 */
	public String selectMaxId(String startTime);
	
	/** 查询用户详情 */
	public <T> Map queryUserDetailById(T id);
	
	/** 查询出最后期限尚未办理完成的事项 */
	public List<Map<String, Object>> unHandleMessage(Map<String, Object> params);
}
