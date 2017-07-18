package com.huike.base.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.huike.base.except.DaoException;

/**
 * 抽象操作定义
 * @author Administrator
 *
 */
public abstract class AbstractBatisDao implements IBasicDao, ITemplateDao<SqlSessionTemplate>{

	@Autowired
	@Qualifier("sqlSessionTemplate")
	protected SqlSessionTemplate optemplate;
	
	public SqlSessionTemplate getOptTemplate() throws DaoException{
		if(this.optemplate == null) {
			throw new DaoException("数据源获取失败!");
		}
		return this.optemplate;
	}
	
	public <T> int insert(String insertId, T _params) throws DaoException {

		return getOptTemplate().insert(insertId, _params);
	}

	public <T> int update(String updateId, T _params) throws DaoException {

		return getOptTemplate().update(updateId, _params);
	}

	public <T> int delete(String deleteId, T _params) throws DaoException {

		return getOptTemplate().delete(deleteId, _params);
	}

	public <T> List<T> query(String queryString, Map<String, Object> _params)
			throws DaoException {

		return getOptTemplate().selectList(queryString, _params);
	}

	public <T> List<T> query(String queryString, Object _params)
			throws DaoException {

		return getOptTemplate().selectList(queryString, _params);
	}

	public Object queryOne(String queryString, Object _params)
			throws DaoException {

		return getOptTemplate().selectOne(queryString, _params);
	}

	public Object queryOne(String queryString, Map<String, Object> _params)
			throws DaoException {
		return getOptTemplate().selectOne(queryString, _params);
	}
}
