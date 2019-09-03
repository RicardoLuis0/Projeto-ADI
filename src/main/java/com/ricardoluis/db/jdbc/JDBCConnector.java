package com.ricardoluis.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class JDBCConnector implements IDBConnector {

	@Override
	public int executeUpdate(String sql) throws SQLException {
		Connection c=ConnectionManager.retrieveConnection();
		try(Statement stmt=c.createStatement()){
			return stmt.executeUpdate(sql);
		}finally {
			ConnectionManager.returnConnection(c);
		}
	}

	@Override
	public <T> ArrayList<T> executeQuery(String sql, RSGetter<T> getter) throws SQLException {
		Connection c=ConnectionManager.retrieveConnection();
		try(Statement stmt=c.createStatement()){
			try(ResultSet rs=stmt.executeQuery(sql)){
				return IDBConnector.RSToArrayList(rs, getter);
			}
		}finally {
			ConnectionManager.returnConnection(c);
		}
	}

}
