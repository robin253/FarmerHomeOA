package com.huike.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.smartframework.common.ExArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;

import com.huike.base.dao.IBasicDao;
import com.huike.base.except.DaoException;
import com.huike.base.tools.SecurityUtil;
import com.huike.base.tools.StringTools;

public abstract class BasicServiceSupport {

	/**
	 * 命名空间
	 */
	private String spaceName;
	
	/**
	 * 统一DAO持久操作实现类
	 */
	@Autowired
	@Qualifier("basicDao")
	private IBasicDao basicDao;
	
	public BasicServiceSupport() {
	}
	
	public BasicServiceSupport(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public IBasicDao getBasicDao() {
		return basicDao;
	}

	public String getAllSpaceName(String queryStr) throws IllegalArgumentException {
		if(spaceName == null || "".equals(spaceName)) {
			throw new IllegalArgumentException("请调用构造方法初始化XML配置的命名空间!");
		}
		return StringTools.getNameSpace(this.spaceName, queryStr);
	}
	
	/** 获取用户 */
	public UserDetails getUserDetails() {
		return new SecurityUtil().getUserDetails();
	}
	
	
	public <T> Integer removeById(T entityId) {
		Integer result = 0;
		try {
			result = getBasicDao().delete(getAllSpaceName("deleteByPrimaryKey"), entityId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public <T> Integer deleteForBatch(List<T> ids) {
		Integer result = 0;
		try {
			result = getBasicDao().delete(getAllSpaceName("deleteForBatch"), ids);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Integer save(Map<String, Object> params) {
		Integer result = 0;
		try {
			result = getBasicDao().insert(getAllSpaceName("insert"), params);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public <T> Map queryById(T id) {
		Map result = new HashMap();
		try {
			result = getBasicDao().queryOne(getAllSpaceName("selectByPrimaryKey"), id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List findForPageListSearch(Map<String, Object> params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ExArrayList resultList = new ExArrayList();
		try {
			list = getBasicDao().query(getAllSpaceName("selectPageList"), params);
			for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
				Object item = localIterator.next();
				Map itemMap = (Map) item;
				Map rowMap = new CaseInsensitiveMap();
				rowMap.putAll(itemMap);
				resultList.add(rowMap);
			}
			resultList.setTotal((Integer)getBasicDao().queryOne(getAllSpaceName("selectCountByMap"), params));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	public Integer findForPageTotalSearch(Map<String, Object> params) {
		Integer result = 0;
		try {
			result = (Integer) getBasicDao().queryOne(getAllSpaceName("selectCountByMap"), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List findForPageListSearchByKey(Map<String, Object> params, String key) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ExArrayList resultList = new ExArrayList();
		try {
			list = getBasicDao().query(getAllSpaceName(key), params);
			for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
				Object item = localIterator.next();
				Map itemMap = (Map) item;
				Map rowMap = new CaseInsensitiveMap();
				rowMap.putAll(itemMap);
				resultList.add(rowMap);
			}
			resultList.setTotal((Integer)getBasicDao().queryOne(getAllSpaceName(key+"Count"), params));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	public Integer modify(Map<String, Object> params) {
		Integer result = 0;
		try {
			result = getBasicDao().update(getAllSpaceName("updateByPrimaryKey"), params);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return result;
	}
}
