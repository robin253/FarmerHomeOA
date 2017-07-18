package com.huike.base.dao;

import com.huike.base.except.DaoException;

/**
 * 注入操作模板
 * 如:
 *   Mybatis:SqlSessionTemplate
 *   Spring: JdbcTemplate
 *   Hibernate:HiberateSport
 * @author OY
 * @param <T>
 */
public interface ITemplateDao<T> {

	T getOptTemplate() throws DaoException;
}
