package com.huike.app.archive.service;

import java.util.List;
import java.util.Map;

import com.huike.base.service.IBasicService;

public interface IResidentService extends IBasicService{
	
	/** 根据外键删除农户 */
	public <T> Integer removeByForId(T entityId);

	/** 农户统计:按教育程度分组 */
	public List<Map<String, Object>> statisticsGroupByEducation(Map<String, Object> params) throws Exception;
	
	/** 农户统计:按政治面貌分组 */
	public List<Map<String, Object>> statisticsGroupByPolitics(Map<String, Object> params) throws Exception;
	
	/** 农户统计:按乡分组 */
	public List<Map<String, Object>> statisticsGroupByXiang(Map<String, Object> params) throws Exception;
	
	/** 农户统计:按村分组 */
	public List<Map<String, Object>> statisticsGroupByCun(Map<String, Object> params) throws Exception;
}
