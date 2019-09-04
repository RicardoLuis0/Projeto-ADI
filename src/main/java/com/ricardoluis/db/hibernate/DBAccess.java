package com.ricardoluis.db.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ricardoluis.db.data.DBUser;

public class DBAccess {
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
	
	
	public List<DBUser> searchUsers(String search_params) throws SQLException {
		List<DBUser> l=null;
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	String[] args=search_params.split(" ");
        	for(String s:args) {
        		if(!isValidName(s))return new ArrayList<>();;//if one isn't valid, return empty ArrayList
        	}
        	transaction = session.beginTransaction();
        	@SuppressWarnings("unchecked")
			Query<DBUser> query=session.createQuery("from DBUser as u where "+(" u.name like ? ".repeat(args.length)));
        	for(int i=0;i<args.length;i++) {
        		query.setParameter(i, "%"+args[i]+"%");
        	}
        	l=(List<DBUser>) query.list();
        	transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return new ArrayList<>();//on error return empty ArrayList
        }
        if(l!=null)return l;
        else return new ArrayList<>();
	}
	
	public boolean addUser(String name,String birthdate) throws SQLException{
		if(isValidName(name)&&isValidBirthDate(birthdate)) {
			DBUser user = new DBUser(name,birthdate);
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();
	            // save the student objects
	            session.save(user);
	            // commit transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	            return false;
	        }
	        return true;
		}else {
			return false;
		}
	}

	public boolean updateUserBirthdate(String name,String birthdate) throws SQLException {
		throw new RuntimeException("unimplemented");
	}

	public boolean deleteUser(String name) throws SQLException {
		throw new RuntimeException("unimplemented");
	}
}
