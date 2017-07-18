package com.huike.base.service;

import java.util.List;
import java.util.Map;

public interface IBasicService {
	
	
	/***************************基础增删查改通用接口*****************************/
	
	
	/**
	 * 新增单条记录
	 * @param saveId mapper.xml中的方法id
	 * @param params 
	 * @return
	 */
	public Integer save(Map<String, Object> params);
	
	/**
	 * 修改单个资源
	 * @param model
	 * @return
	 */
	public Integer modify(Map<String, Object> params);
	
	/**
	 * 删除单个资源
	 * @param ResId
	 */
	public <T> Integer removeById(T entityId);
	
	/**
	 * 根据ID批量删除
	 * @param ids 
	 * @return
	 */
	public <T> Integer deleteForBatch(List<T> ids);
	
	/**
	 * 根据ID查询单个角色
	 * @param RoleId
	 */
	public <T> Map queryById(T id);
	
	/**
	 * 通过类型、路径、描述搜索
	 * 
	 * @param params
	 * @return Integer
	 */
	public Integer findForPageTotalSearch(Map<String, Object> params);
	
	/**
	 * 通过类型、路径、描述搜索
	 * 
	 * @param params
	 * @return List<User>
	 */
	public List findForPageListSearch(Map<String, Object> params);
	
	/**
	 * <p class="detail">
	 * 功能：其他分页查询 key为mapper文件里面的id, 总数sql id 为key+"count"
	 * </p>
	 * @author Zerlinda
	 * @date 2017年5月31日 
	 * @param params
	 * @param key
	 * @return
	 */
	public List findForPageListSearchByKey(Map<String, Object> params, String key);
}
