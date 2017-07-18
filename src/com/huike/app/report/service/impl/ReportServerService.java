package com.huike.app.report.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.huike.app.report.service.IReportServerService;
import com.huike.base.except.DaoException;
import com.huike.base.service.BasicServiceSupport;

/**
 * <p class="detail">
 * 功能：服务报表逻辑控制层
 * </p>
 * @ClassName: ReportServerService 
 * @version V1.0  
 * @date 2017年6月14日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
@Service("reportServerService")
public class ReportServerService extends BasicServiceSupport implements IReportServerService {
	
	public ReportServerService(){
		super("com.huike.app.report.service.IReportServerService");
	}
	
	/** 服务报表统计  */
	public List<Map<String, Object>> statistics(Map<String, Object> params){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			result = getBasicDao().query(getAllSpaceName("statistics"), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
