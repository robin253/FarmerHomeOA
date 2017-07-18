package com.huike.app.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.smartframework.common.utils.ExStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.huike.app.system.model.Menu;
import com.huike.app.system.model.Res;
import com.huike.app.system.service.IResService;
import com.huike.base.except.DaoException;
import com.huike.base.model.AbstractRes;
import com.huike.base.service.BasicServiceSupport;

@Service("resService")
public class ResService extends BasicServiceSupport implements IResService {

	public ResService(){
		super("com.huike.app.system.service.IResService");
	}
	
	@Override
	public Integer addRoleRes(Map<String, Object> params) {
		Integer result = 0;
		if(params.get("roleid") == null || ExStringUtils.isBlank(params.get("roleid").toString())){
			return result;
		}
		if(params.get("resid") == null || ExStringUtils.isBlank(params.get("resid").toString())){
			return result;
		}
		try {
			result = getBasicDao().insert(getAllSpaceName("addRoleRes"), params);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Integer modify(Map<String, Object> params) {
		Integer result = 0;
		Object idObj = params.get("id");
		if(idObj == null || ExStringUtils.isBlank(idObj.toString())){
			return result;
		}
		try {
			result = getBasicDao().update(getAllSpaceName("updateRes"), params);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Integer removeRelationByResId(Integer ResId) {
		Integer result = 0;
		try {
			result = getBasicDao().delete(getAllSpaceName("deleteRelationByResId"), ResId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Integer removeRelationByRoleId(Integer RoleId) {
		Integer result = 0;
		try {
			result = getBasicDao().delete(getAllSpaceName("deleteRelationByRoleId"), RoleId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Integer findForPageTotalSearch(Map<String, Object> params) {
		Integer result = 0;
		try {
			result = (Integer) getBasicDao().queryOne(getAllSpaceName("queryResForPageTotalSearch"), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List findForPageListSearch(Map<String, Object> params) {
		List<Res> list = new ArrayList<Res>();
		try {
			list = getBasicDao().query(getAllSpaceName("queryResForPageListSearch"), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Integer deleteRelationByResIds(List<Integer> ids) {
		Integer result = 0;
		try {
			result = getBasicDao().delete(getAllSpaceName("deleteRelationByResIds"), ids);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer removeRelationByRoleIds(List<Integer> roleIds) {
		Integer result = 0;
		try {
			result = getBasicDao().delete(getAllSpaceName("deleteRelationByRoleIds"), roleIds);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List queryResIdsByRoleId(Integer roleId){
		List residList = new ArrayList();
		try {
			residList = getBasicDao().query(getAllSpaceName("queryResIdsByRoleId"), roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return residList;
	}
	
	@Override
	public List queryAllMenu(Map<String, Object> params) {
		List<Map> list = new ArrayList<Map>();
		List<Map> menus = new ArrayList<Map>();
		try {
			list = getBasicDao().query(getAllSpaceName("queryForAll"), params);
			menus = this.menuConvert(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menus;
	}
	
	@Override
	public List queryMenuByRole(Integer params) {
		List<Map> list = new ArrayList<Map>();
		List<Map> menus = new ArrayList<Map>();
		try {
			list = getBasicDao().query(getAllSpaceName("queryMenuByRole"), params);
			menus = this.menuConvert(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menus;
	}
	
	 public List<Map>  menuConvert(List<Map> list )
	 {
		    List<Map> result = new ArrayList<Map>();
			List<Map> listParent = new ArrayList<Map>();
			List<Map> listChild = new ArrayList<Map>();
			for (Map sysMenu : list) {
				if (sysMenu.get("parentId")==null || "0".equals(sysMenu.get("parentId").toString())){
					listParent.add(sysMenu);
				} else {
					listChild.add(sysMenu);
				}
			}
			for (Map sysMenu : listParent) {
				List<Map> child = new ArrayList<Map>();
				for (Map sysMenuChild : listChild) {
					if (sysMenu.get("id").toString().equals(sysMenuChild.get("parentId").toString())) {
						child.add(sysMenuChild);
					}
				}
				sysMenu.put("childMenus", child);
				result.add(sysMenu);
			}
			return result;
	 }
}
