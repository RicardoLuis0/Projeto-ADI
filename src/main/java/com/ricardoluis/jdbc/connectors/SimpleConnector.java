package com.ricardoluis.jdbc.connectors;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.ricardoluis.jdbc.JDBCConnector;

public class SimpleConnector extends JDBCConnector {

	@Override
	public Connection openConnection() throws SQLException {
		return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ADI_Test?user=root&password=");
	}

	@Override
	public void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}

}
