package com.huike.app.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.huike.app.system.service.IVillageService;
import com.huike.base.service.BasicServiceSupport;

@Service("villageService")
public class VillageService extends BasicServiceSupport implements IVillageService {
	
	public VillageService(){
		super("com.huike.app.system.service.IVillageService");
	}
	
	public List<Map<String, Object>> getParentList(Map<String, Object> params){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			result = (List) getBasicDao().query("selectParentList", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Map<String, Object>> getChildrenList(Map<String, Object> params){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			result = (List) getBasicDao().query("selectChildrenList", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Map<String, Object>> getAllVillage(Map<String, Object> params){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			result = (List) getBasicDao().query("selectAllVillage", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
