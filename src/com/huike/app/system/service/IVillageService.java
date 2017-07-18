package com.huike.app.system.service;

import java.util.List;
import java.util.Map;

import com.huike.base.service.IBasicService;

public interface IVillageService extends IBasicService{
	
	/** 获取乡级下拉列表 */
	public List<Map<String, Object>> getParentList(Map<String, Object> params);
	
	/** 获取隶属于某乡的所有村 */
	public List<Map<String, Object>> getChildrenList(Map<String, Object> params);
	
	/** 获取乡村列表 */
	public List<Map<String, Object>> getAllVillage(Map<String, Object> params);
}
