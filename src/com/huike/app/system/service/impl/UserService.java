package com.huike.app.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.huike.app.system.service.IUserService;
import com.huike.base.except.DaoException;
import com.huike.base.service.BasicServiceSupport;

@Service("userService")
public class UserService extends BasicServiceSupport implements IUserService {
	
	public UserService(){
		super("com.huike.app.system.service.IUserService");
	}
	
	@Override
	public Map<String, Object> checkUser(Map<String, Object> map) {
		Map<String, Object> user = new HashMap<String, Object>();
		try {
			//map.put("password", MD5Util.md5(map.get("password").toString()));
			user = (Map<String, Object>) getBasicDao().queryOne(getAllSpaceName("querySingleUser"),map);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;
	}
	
	@Override
	public boolean existUser(String username) {
		int count = 0;
		List<Object> list = new ArrayList<Object>();
		try {
			list = getBasicDao().query(getAllSpaceName("existUser"), username);
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
	
	@Override
	public List<Map<String, Object>> findUserListByCun(Map<String, Object> map){
		List<Map<String, Object>> user = new ArrayList<Map<String, Object>>();
		try {
			//map.put("password", MD5Util.md5(map.get("password").toString()));
			user = (List) getBasicDao().query("selectUserListByCun", map);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;
	}
	
	@Override	
	public List<Map<String, Object>> queryUsersList(Map<String, Object> map){
		List<Map<String, Object>> user = new ArrayList<Map<String, Object>>();
		try {
			//map.put("password", MD5Util.md5(map.get("password").toString()));
			user = (List) getBasicDao().query("selectUserList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;
	}
	
	@Override
	public Integer addReportUser(Map<String, Object> map){
		Integer result = 0;
		try {
			result = getBasicDao().update("addReportUser", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Integer deleteReportUser(Map<String, Object> map){
		Integer result = 0;
		try {
			result = getBasicDao().update("deleteReportUser", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> selectAllReportUserListForSMS(Map<String, Object> map) {
		List<Map<String, Object>> user = new ArrayList<Map<String, Object>>();
		try {
			//map.put("password", MD5Util.md5(map.get("password").toString()));
			user = (List) getBasicDao().query("selectAllReportUserListForSMS", map);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;
	}

	@Override
	public List<Map<String, Object>> selectAllReportUserListForManage(Map<String, Object> map) {
		List<Map<String, Object>> user = new ArrayList<Map<String, Object>>();
		try {
			//map.put("password", MD5Util.md5(map.get("password").toString()));
			user = (List) getBasicDao().query("selectAllReportUserListForManage", map);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;
	}
	
	/** 根据级别查询所有人员 */
	public List<Map<String, Object>> queryUsersByLevel(Map<String, Object> map){
		List<Map<String, Object>> user = new ArrayList<Map<String, Object>>();
		try {
			user = (List) getBasicDao().query("queryUsersByLevel", map);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;
	}
}
