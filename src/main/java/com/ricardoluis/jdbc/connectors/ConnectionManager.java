package com.ricardoluis.jdbc.connectors;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;

import com.mysql.jdbc.Connection;

/**
 * 
 * Opens and keeps track of connections, if a connection is retrieved, it must be returned, only returns valid connections
 * 
 * @author Ricardo
 *
 */

public class ConnectionManager {
	
	/**
	 * Deque of kept connections
	 */
	private static final ArrayDeque<Connection> connections;
	
	static {
		connections=new ArrayDeque<>();
	}
	
	/**
	 * Opens a new connection to the database
	 * @return newly opened connection
	 * @throws SQLException
	 */
	private static Connection openConnection() throws SQLException {
		return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ADI_Test?user=root&password=");
	}
	
	/**
	 * Retrieves a database connection
	 * @return a valid database connection
	 */
	public static Connection retrieveConnection() {
		try {
			while(!connections.isEmpty()) {
				//retrieve a connection from the deque, if it's valid, return it,
				//otherwise loop until finding a valid one or reaching the end of the deque
				Connection c=connections.pop();
				if(c.isValid(0)) {
					return c;
				}
			}
			return openConnection();//if the deque is empty, return a newly opened connection
		}catch(SQLException e) {
			throw new RuntimeException("Cannot Connect to Database: "+e.getMessage());
		}
	}
	
	/**
	 * Returns a database connection
	 * @param conn connection to be returned
	 */
	public static void returnConnection(Connection conn) {
		try {
			if(conn!=null&&conn.isValid(0)) {
				connections.push(conn);//if the connection is valid, return it
			}
		}catch(SQLException e) {
			//do nothing
		}
	}
}
