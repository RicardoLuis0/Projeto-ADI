package com.ricardoluis.jdbc.connectors;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.ricardoluis.jdbc.JDBCConnector;

public class ManagedConnector extends JDBCConnector {

	@Override
	public Connection openConnection() throws SQLException {
		return ConnectionManager.retrieveConnection();
	}

	@Override
	public void closeConnection(Connection conn) throws SQLException {
		ConnectionManager.returnConnection(conn);
	}

}
