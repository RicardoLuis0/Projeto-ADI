package com.ricardoluis.db.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ricardoluis.db.data.DBUser;

public class DBAccess {
	public ArrayList<DBUser> searchUsers(String search_params) throws SQLException {
		throw new RuntimeException("unimplemented");
	}
	
	public boolean addUser(String name,String birthdate) throws SQLException{
		throw new RuntimeException("unimplemented");
	}

	public boolean updateUserBirthdate(String name,String birthdate) throws SQLException {
		throw new RuntimeException("unimplemented");
	}

	public boolean deleteUser(String name) throws SQLException {
		throw new RuntimeException("unimplemented");
	}
}
