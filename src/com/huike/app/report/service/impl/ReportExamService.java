package com.huike.app.report.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.huike.app.report.service.IReportExamService;
import com.huike.base.except.DaoException;
import com.huike.base.service.BasicServiceSupport;

@Service("reportExamService")
public class ReportExamService extends BasicServiceSupport implements IReportExamService{
	
	public ReportExamService(){
		super("com.huike.app.report.service.IReportExamService");
	}

	@Override
	public String queryUpdateRequest(Map params) {
		String updateRequest = "";
		try {
			updateRequest = (String)getBasicDao().queryOne(getAllSpaceName("queryUpdateRequest"), params);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return updateRequest;
	}
}
