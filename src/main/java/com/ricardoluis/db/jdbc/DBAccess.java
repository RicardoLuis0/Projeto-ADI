package com.ricardoluis.db.jdbc;

import com.ricardoluis.db.data.DBUser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DBAccess extends JDBCConnector{

	public static Pattern valid_names;
	
	static {
		valid_names=Pattern.compile("[A-Za-z0-9_]+");
	}
	
	private static boolean isValidName(String name) {//A-Za-z0-9_
		return valid_names.matcher(name).matches();
	}
	
	private static boolean isValidBirthDate(String birthdate) {//dd-mm-yy
		String[] bdarr=birthdate.split("-");
		if(bdarr.length!=3)return false;
		try {
			int day=Integer.parseInt(bdarr[0]);
			int month=Integer.parseInt(bdarr[1]);
			int year=Integer.parseInt(bdarr[2]);
			return (day>0&&day<32&&month>0&&month<13&&year>-1&&year<100);
		}catch(Exception e) {
			return false;
		}
	}
	
	public ArrayList<DBUser> searchUsers(String search_params) throws SQLException {
		if(search_params.isEmpty())throw new IllegalArgumentException("Invalid empty search parameter");
		String sql="select * from users where";
		for(String s:search_params.split(" ")) {
			if(isValidName(s)) {
				sql+=" name like '%"+s+"%'";
			} else {
				throw new IllegalArgumentException("Invalid search parameter: '"+s+"'");
			}
		}
		return executeQuery(sql,(ResultSet r)-> new DBUser(r.getString(2),r.getString(3)));
	}
	
	public boolean addUser(String name,String birthdate) throws SQLException{
		if(name.isEmpty())throw new IllegalArgumentException("Invalid empty name");
		if(birthdate.isEmpty())throw new IllegalArgumentException("Invalid empty birthdate");
		if(isValidName(name)) {
			if(isValidBirthDate(birthdate)) {
				String sql="insert into users (name,birthdate) values ('"+name+"','"+birthdate+"')";
				return (executeUpdate(sql)>0);
			}else {
				throw new IllegalArgumentException("Invalid birth date: '"+birthdate+"'");
			}
		} else {
			throw new IllegalArgumentException("Invalid name: '"+name+"'");
		}
	}

	public boolean updateUserBirthdate(String name,String birthdate) throws SQLException {
		if(name.isEmpty())throw new IllegalArgumentException("Invalid empty name");
		if(birthdate.isEmpty())throw new IllegalArgumentException("Invalid empty birthdate");
		if(isValidName(name)) {
			if(isValidBirthDate(birthdate)) {
				String sql="update users set birthdate = '"+birthdate+"' where name = '"+name+"'";
				return (executeUpdate(sql)>0);
			} else {
				throw new IllegalArgumentException("Invalid birth date: '"+birthdate+"'");
			}
		} else {
			throw new IllegalArgumentException("Invalid name: '"+name+"'");
		}
	}

	public boolean deleteUser(String name) throws SQLException {
		if(name.isEmpty())throw new IllegalArgumentException("Invalid empty name");
		if(isValidName(name)) {
			String sql="delete from users where name = '"+name+"'";
			return (executeUpdate(sql)>0);
		} else {
			throw new IllegalArgumentException("Invalid name: '"+name+"'");
		}
	}
}
