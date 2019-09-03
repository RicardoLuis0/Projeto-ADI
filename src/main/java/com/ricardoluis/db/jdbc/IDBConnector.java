package com.ricardoluis.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDBConnector {

	@FunctionalInterface
	public interface RSGetter<T>{
		T get(ResultSet rs) throws SQLException;
	}

	public static <T> ArrayList<T> RSToArrayList(ResultSet rs,RSGetter<T> getter) throws SQLException {
		ArrayList<T> result=null;
		while(rs.next()) {
			if(result==null) result=new ArrayList<T>();
			result.add(getter.get(rs));
		}
		return result;
	}
	
	public int executeUpdate(String sql) throws SQLException;
	
	public <T> ArrayList<T> executeQuery(String sql,RSGetter<T> getter) throws SQLException;

}
