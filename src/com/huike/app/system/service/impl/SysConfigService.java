package com.huike.app.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.huike.app.system.service.ISysConfigService;
import com.huike.base.except.DaoException;
import com.huike.base.service.BasicServiceSupport;

@Service("sysConfigService")
public class SysConfigService extends BasicServiceSupport implements ISysConfigService{

	public SysConfigService(){
		super("com.huike.app.system.service.ISysConfigService");
	}

	@Override
	public Map selectKeyValueByGroupCode(Map<String, Object> params){
		Map sys_sms_config = new HashMap();
		try {
			List list = getBasicDao().query(getAllSpaceName("selectKeyValueByGroupCode"), params);
			for(int i = 0; i<list.size(); i++){
		   		 Map param = (Map)list.get(i);
		   		 sys_sms_config.put(param.get("ckey").toString(),param.get("cvalue").toString());
		   	 }
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return sys_sms_config;
	}
}
