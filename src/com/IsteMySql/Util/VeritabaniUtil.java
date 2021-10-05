package com.IsteMySql.Util;
import java.sql.*;
public class VeritabaniUtil {
	

	static Connection conn=null;
	
	public static Connection Baglan() {
		try {
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow","root","mysql");
			return conn;
		}catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
		}
	}
	
	


