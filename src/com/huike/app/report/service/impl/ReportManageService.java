package com.huike.app.report.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.huike.app.report.service.IReportManageService;
import com.huike.base.service.BasicServiceSupport;

@Service("reportManageService")
public class ReportManageService extends BasicServiceSupport implements IReportManageService {
	
	public ReportManageService(){
		super("com.huike.app.report.service.IReportManageService");
	}

	@Override
	public List<Map<String, Object>> selectUnuploadList(Map<String, Object> params) {
		List<Map<String, Object>> user = new ArrayList<Map<String, Object>>();
		try {
			user = (List) getBasicDao().query("selectUnuploadList", params);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;
	}
}
