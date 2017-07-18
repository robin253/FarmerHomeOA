package com.huike.base.dao.transfer;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
/**
 * 将数据库BLOB类型转JAVA byte数组
 * @author OY 2015/11/19
 *
 */
public class BlobToByteArrayTypeHandler implements TypeHandler<byte[]>{

	public byte[] getResult(ResultSet arg0, String arg1) throws SQLException {

		return null;
	}

	public byte[] getResult(ResultSet arg0, int arg1) throws SQLException {

		return null;
	}

	public byte[] getResult(CallableStatement arg0, int arg1)
			throws SQLException {

		return null;
	}

	public void setParameter(PreparedStatement arg0, int arg1, byte[] arg2,
			JdbcType arg3) throws SQLException {
		
	}
}
