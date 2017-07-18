package com.huike.app.system.service;

import java.util.List;
import java.util.Map;

import com.huike.base.service.IBasicService;

public interface IResService extends IBasicService {
	
	/**
	 * 为角色分配资源
	 * @param params
	 * @return 操作成功个数
	 */
	public Integer addRoleRes(Map<String, Object> params);
	
	/**
	 * 根据资源ID删除角色-资源关系
	 * @param ResId
	 */
	public Integer removeRelationByResId(Integer resId);
	
	/**
	 * 根据资源ID批量删除角色-资源
	 * @param ids
	 * @return
	 */
	public Integer deleteRelationByResIds(List<Integer> resIds);
	
	/**
	 * 根据角色ID删除角色-资源关系
	 * @param ResId
	 */
	public Integer removeRelationByRoleId(Integer roleId);
	
	/**
	 * 根据角色ID批量删除角色-资源关系
	 * @param ResId
	 */
	public Integer removeRelationByRoleIds(List<Integer> roleIds);
	
	/**
	 * 通过角色ID查询角色的资源ID集合
	 * @param roleId
	 * @return
	 */
	public List queryResIdsByRoleId(Integer roleId);
	
	/**
	 * 查询菜单栏
	 * @param params
	 * @return
	 */
	public List queryAllMenu(Map<String, Object> params);
	
	/**
	 * 根据角色查询菜单栏
	 * @param params
	 * @return
	 */
	public List queryMenuByRole(Integer params);

}
