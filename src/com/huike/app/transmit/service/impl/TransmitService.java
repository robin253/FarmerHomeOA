package com.huike.app.transmit.service.impl;

import org.springframework.stereotype.Service;

import com.huike.app.transmit.service.ITransmitService;
import com.huike.base.service.BasicServiceSupport;

@Service("transmitService")
public class TransmitService extends BasicServiceSupport implements ITransmitService{
	
	public TransmitService(){
		super("com.huike.app.transmit.service.ITransmitService");
	}
	
	public Integer maxId(){
		Integer result = 0;
		try {
			result = (Integer) getBasicDao().queryOne(getAllSpaceName("maxId"), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
