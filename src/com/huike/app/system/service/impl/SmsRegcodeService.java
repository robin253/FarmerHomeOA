package com.huike.app.system.service.impl;

import org.springframework.stereotype.Service;

import com.huike.app.system.service.ISmsRegcodeService;
import com.huike.base.service.BasicServiceSupport;

@Service("smsRegcodeService")
public class SmsRegcodeService extends BasicServiceSupport implements ISmsRegcodeService{

	public SmsRegcodeService(){
		super("com.huike.app.system.service.ISmsRegcodeService");
	}
}
