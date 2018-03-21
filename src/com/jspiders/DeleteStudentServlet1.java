package com.jspiders;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.StdentBean;
import com.mysql.jdbc.Driver;

public class DeleteStudentServlet1  extends HttpServlet{
	
	
	
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String regnum= req.getParameter("regno");
		int regno = Integer.parseInt(regnum);
		
		
			PrintWriter out= resp.getWriter();
			
			
			 StudentDAO dao=ServiceGenrator.genrateDao();
			 dao.deletestudentservlet(regno);
			 
			
				out.print("student deleted");
				
			
//				int temp=regno/5;
//				int from=temp*5+1;
//				int to=from+4;
				
				int from=regno/5*5+1;
				int to=from+4;
			
//			RequestDispatcher dispatcher;
//			dispatcher=req.getRequestDispatcher("./ViewAllStudentsServlet1"+from+"&to"+to);
//			dispatcher.forward(req, resp);
				
				///since 
				//req.getRequestDispatcher("./ViewAllStudentsServlet1"+from+"&to"+to).forward(req, resp);
		
		resp.sendRedirect("./ViewAllStudentsServlet1?from="+from+"&to="+to);///it ill always execute doGet method
		}
		
	}


