package com.huike.app.report.service.impl;

import org.springframework.stereotype.Service;

import com.huike.app.report.service.IReportFinanceService;
import com.huike.base.service.BasicServiceSupport;

/**
 * <p class="detail">
 * 功能：财务报表逻辑控制层
 * </p>
 * @ClassName: ReportFinanceService 
 * @version V1.0  
 * @date 2017年6月7日 
 * @author Zerlinda
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */
@Service("reportFinanceService")
public class ReportFinanceService extends BasicServiceSupport implements IReportFinanceService {

	public ReportFinanceService(){
		super("com.huike.app.report.service.IReportFinanceService");
	}
}
