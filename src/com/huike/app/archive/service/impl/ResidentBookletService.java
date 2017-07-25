package com.huike.app.archive.service.impl;

import org.springframework.stereotype.Service;

import com.huike.app.archive.service.IResidentBookletService;
import com.huike.base.service.BasicServiceSupport;

@Service("residentBookletService")
public class ResidentBookletService  extends BasicServiceSupport implements IResidentBookletService{
	
	public ResidentBookletService(){
		super("com.huike.app.archive.service.IResidentBookletService");
	}
	
}
