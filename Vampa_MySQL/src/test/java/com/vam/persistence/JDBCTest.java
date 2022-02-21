package com.vam.persistence;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.Test;
 
public class JDBCTest{
	static { 
	    try { 
	        Class.forName("com.mysql.cj.jdbc.Driver"); 
	    } catch(Exception e) { 
	        e.printStackTrace(); 
	    } 
	} 

	@Test 
	public void testConnection() { 
	    try(Connection con = DriverManager.getConnection( 
	            "jdbc:mysql://localhost:3306/ksm?serverTimezone=Asia/Seoul", 
	            "root", 
	            "rootpw")){ 
	        System.out.println(con); 
	    } catch (Exception e) { 
	        fail(e.getMessage()); 
	    } 

	}  
	
}