package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import extras.md5;
/*
 * Author: Ricardo Benitez
 * Contributors: Cyril Yaranon, Justin Dela Cruz
 */
public class SQLite_helper {
	
    private final String urlToDb = "jdbc:sqlite:iBusinessMate.db";
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public SQLite_helper() throws Throwable {
    	// load driver; connect to DB
    	Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection(urlToDb);
		
		// test if Tables have already been set up; initialize if not
		try {
			pst = con.prepareStatement("SELECT * FROM users;");
			rs = pst.executeQuery();
			if(!rs.next()) init_db();
			rs.close();
			pst.close();
		} catch(SQLException k) {
			init_db();
		}		
    }
    
    public boolean init_db() throws Throwable{
        try {
        	// Create Tables in DB: users, items; Update DB
            pst = con.prepareStatement("CREATE TABLE IF NOT EXISTS users(" +
            		"`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
            		"`first_name` VARCHAR(50) NOT NULL, " +
            		"`last_name` VARCHAR(50) NOT NULL, " +
            		"`middle_initial` VARCHAR(1) NOT NULL, " +
            		"`username` VARCHAR(15) NOT NULL, " +
            		"`password` VARCHAR(80) NOT NULL, " +
            		"`sex` VARCHAR(6) NOT NULL, " +
            		"`isAdmin` BOOLEAN NOT NULL" +
            		");");
            pst.executeUpdate();
            
            pst = con.prepareStatement("CREATE TABLE IF NOT EXISTS items(" +
            		"`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
            		"`product_name` VARCHAR(50) NOT NULL, " +
            		"`serial_code` VARCHAR(11) NOT NULL, " +
            		"`price` DOUBLE NOT NULL, " +
            		"`stock` INTEGER NOT NULL, " +
            		"`sold` INTEGER NOT NULL" +
            		");");
            pst.executeUpdate();
            
            pst = con.prepareStatement("CREATE TABLE IF NOT EXISTS sales( " +
            		"`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
            		"`date` DATE NOT NULL, " +
            		"`item` INTEGER NOT NULL, " +
            		"`quantity` INTEGER NOT NULL, " +
            		"`price` DOUBLE NOT NULL, " +
            		"FOREIGN KEY(item) REFERENCES items(id)" +
            		");");
            pst.executeUpdate();

            // create a default user
            setDefUser();
	    	this.destruct();
            return true;
        } catch (SQLException ex) {
//        	throw ex;
    		return false;
        }
    }
    
    private void setDefUser() throws Throwable {
        // Initialize default user
        // --> for security encrypt password by md5
        String def_pass_raw = "root";
        md5 md5_pass = new md5();
        String def_pass = md5_pass.encrypt(def_pass_raw);
        
        //Insert into DB
        pst = con.prepareStatement("INSERT INTO users(" +
        		"`first_name`, `last_name`, `middle_initial`, " +
        		"`username`, `password`, `sex`, `isAdmin`) " +
        		"VALUES(" +
        		"'fn', 'ln', 'a', 'admin', '"+def_pass+"', 'male', 'true');");
        pst.executeUpdate();
    	this.destruct();
    }
    
    public boolean sqlExecute(String query) throws Throwable{
    	try {
	    	pst = con.prepareStatement(query);
	        pst.executeUpdate();
	    	this.destruct();
	    	return true;
    	} catch (SQLException ex) {
	    	this.destruct();
    		throw ex;
    	}
    }
    
    public int insert(String table, String[] cols, String[] vals) throws Throwable {
    	// one-table insert
    	String query = "";
    	int i = 0;
    	if (cols.length == vals.length) {
	    	for( String column: cols ) {
	    		query += "INSERT INTO "+table+"("+column+") VALUES("+vals[i]+");";
	    		i++;
	    	}
	    	sqlExecute(query);
	        ResultSet key = pst.getGeneratedKeys();
	    	return key.getInt(1);
    	} else {
    		return -1;
    	}
    }
    
    public int update(String table, String[] cols, String[] vals, int id) throws Throwable {
    	// one-table insert
    	String query = "UPDATE "+table+" SET ";
    	int i = 0;
    	if (cols.length == vals.length) {
	    	for( String column: cols ) {
	    		if(i > 0) query += ", ";
	    		query += column+"="+vals[i];
	    		i++;
	    	}
	    	query += "where id="+id+";";
	    	sqlExecute(query);
	    	ResultSet key = pst.getGeneratedKeys();
	    	return key.getInt(1);
    	} else {
	    	this.destruct();
    		return -1;
    	}
    }
    
    public void update(String query) throws SQLException {
    	Statement st = con.createStatement();
    	st.executeUpdate(query);
    	st.close();
    }
    
    public void delete(String table, int id) throws Throwable {
    	try {
    		if(!table.equals("")) {
    			Statement st = con.createStatement();
	    		String query = "DELETE FROM "+table+" WHERE id='"+id+"'";
//	    		System.out.println(query);
		        st.executeUpdate(query);
		        st.close();
    		} 
    	} catch(SQLException ex) {
    		throw ex;
    	}
    }
    
    public ResultSet retrieve(String table, String col, int val) throws Throwable {
    	try {
	    	pst = con.prepareStatement("SELECT * FROM "+table+" WHERE `"+col+"`='"+val+"';");
	    	return pst.executeQuery();
    	} catch (SQLException ex) {
	    	this.destruct();
    		return null;
    	}
    }
    
    public ResultSet retrieve(String table, String col, String val) throws Throwable {
    	try {
	    	pst = con.prepareStatement("SELECT * FROM "+table+" WHERE `"+col+"`='"+val+"';");
	    	return pst.executeQuery();
    	} catch (SQLException ex) {
    		this.destruct();
    		return null;
    	}
    }
    
    public ResultSet getInfoByID(String table, int id) throws Throwable {
    	try {
	    	pst = con.prepareStatement("SELECT * FROM "+table+" WHERE `id`='"+id+"';");
	    	return pst.executeQuery();
    	} catch (SQLException ex) {
	    	this.destruct();
    		return null;
    	}
    }
    
    public ResultSet getInfoByCode(String code) throws Throwable {
    	pst = con.prepareStatement("SELECT * FROM items WHERE serial_code='"+code+"';");
    	return pst.executeQuery();
    }
    
    public ResultSet getBulk(String table) throws Throwable {
    	pst = con.prepareStatement("SELECT * FROM "+table+";");
    	return pst.executeQuery();
    }
    
    public void destruct() throws Throwable {
//    	rs.close();
//    	pst.close();
//    	con.close();
    	this.finalize();
    }
    
}
