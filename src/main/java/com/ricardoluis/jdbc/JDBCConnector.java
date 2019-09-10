package com.ricardoluis.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public abstract class JDBCConnector{//template method pattern

	public abstract Connection openConnection() throws SQLException ;
	public abstract void closeConnection(Connection conn) throws SQLException ;

	@FunctionalInterface
	public interface RSGetter<T>{
		T get(ResultSet rs) throws SQLException;
	}

	private static <T> ArrayList<T> RSToArrayList(ResultSet rs,RSGetter<T> getter) throws SQLException {
		ArrayList<T> result=null;
		while(rs.next()) {
			if(result==null) result=new ArrayList<T>();
			result.add(getter.get(rs));
		}
		return result;
	}

	public final int executeUpdate(String sql) throws SQLException {
		Connection c=openConnection();
		try(Statement stmt=c.createStatement()){
			return stmt.executeUpdate(sql);
		}finally {
			closeConnection(c);
		}
	}

	public final <T> ArrayList<T> executeQuery(String sql, RSGetter<T> getter) throws SQLException {
		Connection c=openConnection();
		try(Statement stmt=c.createStatement()){
			try(ResultSet rs=stmt.executeQuery(sql)){
				return RSToArrayList(rs, getter);
			}
		}finally {
			closeConnection(c);
		}
	}

}
