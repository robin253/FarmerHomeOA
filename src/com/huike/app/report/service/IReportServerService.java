package com.huike.app.report.service;

import java.util.List;
import java.util.Map;

import com.huike.base.service.IBasicService;

public interface IReportServerService extends IBasicService {

	/** 服务报表统计  */
	public List<Map<String, Object>> statistics(Map<String, Object> params);	
}
