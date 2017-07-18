package com.huike.app.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.huike.app.system.service.IDepartmentService;
import com.huike.base.except.DaoException;
import com.huike.base.service.BasicServiceSupport;

@Service("departmentService")
public class DepartmentService extends BasicServiceSupport implements IDepartmentService {
	
	public DepartmentService(){
		super("com.huike.app.system.service.IDepartmentService");
	}
	
	public List<Map<String, Object>> getOptionList(){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			result = (List) getBasicDao().query("selectOptionList", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public boolean existDepartName(String departName) {
		int count = 0;
		List<Object> list = new ArrayList<Object>();
		try {
			list = getBasicDao().query(getAllSpaceName("existDepart"), departName);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		if(list!=null && list.size()>0){
			count = (int) list.get(0);
		}
		boolean flag = false;
		if (count <= 0)
			flag = true;
		return flag;
	}
}
