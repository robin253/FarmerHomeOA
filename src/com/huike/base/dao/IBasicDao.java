package com.huike.base.dao;

import java.util.List;
import java.util.Map;

import com.huike.base.except.DaoException;

/**
 * DAO超级接口
 * @author Administrator
 *
 */
public interface IBasicDao {

	/**
	 * 单表插入记录
	 * @param insertId
	 * @param _params
	 * @return 记录数 
	 * @throws DaoException
	 */
	public <T> int insert(String insertId, T _params) throws DaoException;
	
	/**
	 * 更新单表
	 * @param updateId
	 * @param _params
	 * @return 记录数 
	 * @throws DaoException
	 */
	public <T> int update(String updateId, T _params) throws DaoException;
	
	/**
	 * 删除记录
	 * @param deleteId
	 * @param _params
	 * @return
	 * @throws DaoException
	 */
	public <T> int delete(String deleteId, T _params) throws DaoException;
	
	/**
	 * 返回查询一览表的信息
	 * @param queryString
	 * @param _params
	 * @return
	 * @throws DaoException
	 */
	public <T> List<T> query(String queryString, Map<String, Object> _params) throws DaoException;

	/**
	 * 查询相关列表信息
	 * @param queryString
	 * @param _params
	 * @return
	 * @throws DaoException
	 */
	public <T> List<T> query(String queryString, Object _params) throws DaoException;

	/**
	 * 查询单个数据
	 * @param queryString
	 * @param _params
	 * @return
	 * @throws DaoException
	 */
	public <T> T queryOne(String queryString, Object _params) throws DaoException;
	
	/**
	 * 查询单个数据
	 * @param queryString
	 * @param _params
	 * @return
	 * @throws DaoException
	 */
	public Object queryOne(String queryString, Map<String, Object> _params) throws DaoException;
	
}
