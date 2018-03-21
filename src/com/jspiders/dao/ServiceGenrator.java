package com.jspiders.dao;

public class ServiceGenrator {
	public static StudentDAO genrateDao() {
		
		JDBCImpl impl=new JDBCImpl();
		StudentDAO dao = (StudentDAO) impl;
		
		return dao;
	}
}
