package com.huike.app.transmit.service;

import java.util.List;

import com.huike.base.service.IBasicService;

public interface ITransmitReceiverService extends IBasicService {

	/** 已读人员名单 */
	public List<String> readDetail(Integer transmitId);
	
	/** 未读人员名单 */
	public List<String> unReadDetail(Integer transmitId);
}
