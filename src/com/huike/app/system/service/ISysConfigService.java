package com.huike.app.system.service;

import java.util.Map;

import com.huike.base.service.IBasicService;

public interface ISysConfigService extends IBasicService{

	public Map selectKeyValueByGroupCode(Map<String, Object> params);
}
