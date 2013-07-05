/*
 * Copyright (c) 2013 LimeCodes Media
 * See the file license.txt for copying permission.
 */
package main;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.SQLite_helper;

public class Items {
	
	private int ID;
	private String prodName;
	private String serial;
	private double price;
	private int stock;
	private int sold;
	private SQLite_helper sql;
	
	public Items() {
		
	}
	
	public Items(String code) throws Throwable {
		sql = new SQLite_helper();
		if(validate(code)) {
			ResultSet info = sql.getInfoByCode(code);
			ID = info.getInt("id");
			prodName = info.getString("product_name");
			serial = info.getString("serial_code");
			price = info.getDouble("price");
			stock = info.getInt("stock");
			sold = info.getInt("sold");
			info.close();
		}
		sql.destruct();
	}
	
	public boolean validate(String code) throws Throwable {
		sql = new SQLite_helper();
		ResultSet set = sql.getInfoByCode(code);
		try{
			set.getString("product_name");
			set.close();
			sql.destruct();
			return true;
		} catch(Exception ex) {
//			ex.printStackTrace();
			set.close();
			sql.destruct();
			return false;
		}
	}
	
	public boolean regItem(String prodName, String serial, double price, int stock) throws Throwable {
		sql = new SQLite_helper();
		String query = "INSERT INTO items(`product_name`, `serial_code`, `price`, `stock`, `sold`) VALUES(" +
				"'"+prodName+"', '"+serial+"', "+price+", "+stock+", 0);";
//		System.out.println(query);
		boolean isClosed = sql.sqlExecute(query);
//		sql.destruct();
		return isClosed;
	}
	
	public void updateItem(String prodName, String serial, double price, int stock, int id) throws Throwable {
		sql = new SQLite_helper();
		String query = "UPDATE `items` SET 'product_name'='"+prodName+"', " +
				"'serial_code'='"+serial+"', " +
				"'price'="+price+", " +
				"'stock'="+stock+" " +
				"WHERE id="+id+";";
		System.out.println(query);
		sql.update(query);
//		sql.destruct();
	}
	
	public void updateItem(int id, int units) throws Throwable {
		sql = new SQLite_helper();
		String query = "UPDATE `items` SET 'stock'=stock-"+units+", 'sold'=sold+"+units+" WHERE id="+id+";";
		System.out.println(query);
		sql.update(query);
	}
	
	public void setPrice() {
		
	}
	
	public void setName() {
		
	}
	
	public void setCode() {
		
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getID() {
		return ID;
	}
	
	public String getName() {
		return prodName;
	}
	
}
