package com.jspiders;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.StdentBean;
import com.mysql.jdbc.Driver;

public class UpdateStudentServlet1 extends HttpServlet {
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int regno = Integer.parseInt(req.getParameter("regno"));
		String fname = req.getParameter("fname");
		String mname = req.getParameter("mname");
		String lname =req.getParameter("lname");
		
		String gfname=req.getParameter("gfname");
		String gmname=req.getParameter("gmname");
		String glname=req.getParameter("glname");
		String isadmin=req.getParameter("isadmin");
		
		//String isadmin=req.getParameter("");
		String password=req.getParameter("password");
		 StdentBean data=new StdentBean();
		 data.setRegno(regno);
		  data.setFname(fname);
		  data.setMname(mname);
		  data.setLname(lname);
		  data.setGfname(gfname);
		  data.setGmname(gmname);
		  data.setGlname(glname);
		  data.setIsadmin(isadmin);
		  data.setPassword(password);
		  
		  
		  StudentDAO dao=ServiceGenrator.genrateDao();
		  dao.UbdateStudentdetails(data);
		 
		  int from=regno/5*5+1;
			int to=from+4;
		

	
			resp.sendRedirect("./ViewAllStudentsServlet1?from="+from+"&to="+to);///it ill always execute doGet method
		 
		
	
		
	}


}
