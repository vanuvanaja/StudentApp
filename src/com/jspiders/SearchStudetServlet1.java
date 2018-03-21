package com.jspiders;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.StdentBean;
import com.mysql.jdbc.Driver;

public class SearchStudetServlet1 extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int regno=Integer.parseInt(req.getParameter("regno"));
		PrintWriter out = resp.getWriter();
	     resp.setContentType("text/html");
	     StudentDAO dao=ServiceGenrator.genrateDao();
		 StdentBean data= dao.getstudentdeatails(regno);
		 RequestDispatcher dispatcher;
		
		if(data!=null)
		{
			req.setAttribute("data", data);
			
			
			 dispatcher=req.getRequestDispatcher("./header.jsp");
			 dispatcher.include(req, resp);

			 dispatcher=req.getRequestDispatcher("./search.jsp");
			 dispatcher.include(req, resp);
			/*
			String htmlresp="<table border=1>"
					+ "<tr><th>Regno</th>"
					+ "<th>First Nmae</th>"
					+ "<th>Middle Name</th>"
					+ "<th>Last Name</th>"
					+ "<th>Guardian FName</th>"
					+ "<th>Guardian MName</th>"
					+ "<th>Guardian LName</th>"
					+ "<th>IS admin</th>"
					+ "<th>password</th></tr>"
			+ "<tr><td>" +data.getRegno()+"</td>"
			+ "<td>" +data.getFname()+"</td>"
			+ "<td>" +data.getMname()+"</td>"
			+ "<td>" +data.getLname()+"</td>"
			+ "<td>" +data.getGfname()+"</td>"
			+ "<td>" +data.getGmname()+"</td>"
			+ "<td>" +data.getGlname()+"</td>"
			+ "<td>" +data.getIsadmin()+"</td>"
			//+ "<td>" +data.getPassword()+"</td>"
			+ "</table>";

			out.print(htmlresp);*/
		}
		

		}
	
		
		
		
		
	}



