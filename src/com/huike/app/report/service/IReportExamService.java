package com.huike.app.report.service;

import java.util.Map;

import com.huike.base.service.IBasicService;

public interface IReportExamService extends IBasicService {

	/** 根据记录ID查询修改要求 */
	public String queryUpdateRequest(Map params);
}
