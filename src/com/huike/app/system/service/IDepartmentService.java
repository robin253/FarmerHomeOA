package com.huike.app.system.service;

import java.util.List;
import java.util.Map;

import com.huike.base.service.IBasicService;

public interface IDepartmentService extends IBasicService{
	
	/** 查询部门下拉列表 */
	public List<Map<String, Object>> getOptionList();
	
	/** 部门名是否重复 */
	public boolean existDepartName(String departName);
}