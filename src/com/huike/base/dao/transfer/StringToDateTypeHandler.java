package com.huike.base.dao.transfer;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import com.huike.base.tools.DateTools;
/**
 * 字符串与日期转换
 * 写入时：将日期字符串转化为数据库日期
 * 读取时：将日期转化为字符串日期
 * 1) 实现自定义类型转换器
 * 2) 注册自定义类型转换器(mybatis-config.xml)
 * <typeHandlers>
 *	<typeHandler handler="com.sunshine.basic.dao.transfer.StringToDateTypeHandler"
 *		javaType="java.lang.String" jdbcType="TIMESTAMP" />
 * </typeHandlers>
 * 3) 引用自定义转换器(自动查找)
 *    指定jdbcType, javaType
 * @author oy
 *
 */
@MappedTypes({String.class})
@MappedJdbcTypes({JdbcType.TIMESTAMP})
public class StringToDateTypeHandler implements TypeHandler<String> {

	public String getResult(ResultSet rs, String columnName) throws SQLException {
		Date date = rs.getDate(columnName);
		long timeLong = date.getTime();
		return DateTools.longToTime(timeLong);
	}

	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		Date date = rs.getDate(columnIndex);
		long timeLong = date.getTime();
		return DateTools.longToTime(timeLong);
	}

	public String getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		Date date = cs.getDate(columnIndex);
		long timeLong = date.getTime();
		return DateTools.longToTime(timeLong);
	}
	
	public void setParameter(PreparedStatement ps, int columnIndex, String parameter,
			JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
            ps.setNull(columnIndex, Types.VARCHAR);
		} else {
			try {
				// SimpleDateFormat多线程安全 multip pointer
				//long timelong = DateTools.dateTimeToLong(parameter); 
				SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date date = datetimeFormat.parse(parameter);
				Date _date = new java.sql.Date(date.getTime());
				ps.setDate(columnIndex, _date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
