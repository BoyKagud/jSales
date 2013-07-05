package main;

import java.sql.ResultSet;
import java.sql.SQLException;

import extras.md5;

import main.SQLite_helper;

public class Users {

	int ID;
	String fName = "";
	String lName = "";
	String mi = "";
	String uName = "";
	String sex = "";
	boolean isAdmin = false;
	private SQLite_helper sql;
	
	public Users() {
		
	}
	
	public Users(int id) throws Throwable {
		sql = new SQLite_helper();
		ResultSet info = sql.getInfoByID("users", id);
		fName = info.getString("first_name");
		lName = info.getString("last_name");
		mi = info.getString("middle_initial");
		uName = info.getString("username");
		sex = info.getString("sex");
		isAdmin = info.getBoolean("isAdmin");
		info.close();
		sql.destruct();
	}
	
	public boolean regUser(String uname, String pword, String fname, String lname, String mi, String sex, boolean isAdmin) throws Throwable {
		sql = new SQLite_helper();
		String query = "INSERT INTO users(`first_name`, `last_name`, `middle_initial`, `username`, `password`, `sex`, `isAdmin`) VALUES(" +
				"'"+fname+"', '"+lname+"', '"+mi+"', '"+uname+"', '"+pword+"', '"+sex+"', '"+isAdmin+"');";
//		System.out.println(query);
		boolean isClosed = sql.sqlExecute(query);
//		sql.destruct();
		return isClosed;
	}
	
	public void updateUser(String prodName, String serial, double price, int stock, int id) throws Throwable {
		sql = new SQLite_helper();
		String query = "UPDATE `users` SET 'product_name'='"+prodName+"', " +
				"'serial_code'='"+serial+"', " +
				"'price'="+price+", " +
				"'stock'="+stock+" " +
				"WHERE id="+id+";";
		System.out.println(query);
		sql.update(query);
//		sql.destruct();
	}
	
	public Integer login(String uname, String pword) throws Throwable {
		md5 enc = new md5();
		SQLite_helper log = new SQLite_helper();
		ResultSet user = log.retrieve("users","username", uname);
		if(!user.next()) {
			enc.close();
			log.destruct();
			user.close();
			return 0;
		} else {
			String username = user.getString("username");
			if(username.compareTo(uname) == 0) {
				String pword_hash = enc.encrypt(pword);
				String password = user.getString("password");
				if(password.compareTo(pword_hash) == 0) {
					enc.close();
					if(user.getString("isAdmin").equals("true")) {
						enc.close();
						log.destruct();
						user.close();
						return 1;
					} else {
						enc.close();
						log.destruct();
						user.close();
						return 2;
					}
				} else {
					enc.close();
					log.destruct();
					return 0;
				}
			} else {
				enc.close();
				log.destruct();
				user.close();
				return 0;
			}
		}
	}
	
}
