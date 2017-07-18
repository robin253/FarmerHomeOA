package com.huike.app.transmit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.huike.app.transmit.service.ITransmitReceiverService;
import com.huike.base.except.DaoException;
import com.huike.base.service.BasicServiceSupport;

@Service("transmitReceiverService")
public class TransmitReceiverService extends BasicServiceSupport implements ITransmitReceiverService{

	public TransmitReceiverService(){
		super("com.huike.app.transmit.service.ITransmitReceiverService");
	}

	@Override
	public List<String> readDetail(Integer transmitId) {
		List<String> list = new ArrayList<String>();
		try {
			list = getBasicDao().query(getAllSpaceName("readDetail"), transmitId);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> unReadDetail(Integer transmitId) {
		List<String> list = new ArrayList<String>();
		try {
			list = getBasicDao().query(getAllSpaceName("unReadDetail"), transmitId);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return list;
	}
}
