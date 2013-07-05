/*
 * Copyright (c) 2013 LimeCodes Media
 * See the file license.txt for copying permission.
 */
package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import extras.md5;
import main.SQLite_helper;

public class Authentication {

	public int login(String uname, String pword) throws Throwable {
		md5 enc = new md5();
		SQLite_helper log = new SQLite_helper();
		ResultSet user = log.retrieve("users","username", uname);
		if(!user.next()) {
			return 0;
		} else {
			String username = user.getString("username");
			if(username.compareTo(uname) == 0) {
				String pword_hash = enc.encrypt(pword);
				String password = user.getString("password");
				if(password.compareTo(pword_hash) == 0) {
					enc.close();
					if(user.getBoolean("isAdmin")) {
						return 1;
					} else {
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
				return 0;
			}
		}
	}
	
}
