package com.huike.app.report.service;

import java.util.List;
import java.util.Map;

import com.huike.base.service.IBasicService;

public interface IReportManageService extends IBasicService {

	/** 查询出未上传报表的用户发送提醒短信 */
	public List<Map<String, Object>> selectUnuploadList(Map<String, Object> params);
}
