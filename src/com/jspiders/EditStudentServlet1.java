package com.jspiders;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.StdentBean;
import com.mysql.jdbc.Driver;

public class EditStudentServlet1 extends HttpServlet {
	
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int regno=Integer.parseInt(req.getParameter("regno"));
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		StudentDAO dao=ServiceGenrator.genrateDao();
		 StdentBean data= dao.getstudentdeatails(regno);
		
		
			if(data!=null)
			{
				String htmlresp="<html><body><form action='./UpdateStudentServlet1' method='post'>"
						+ "<table><tr><td>Regno:</td>"
						+ "<td><input type='text' name='regno' value='"+regno+"'></td></tr>"
						
						+ "<tr><td>Fname:</td>"
						+ "<td><input type='text' name='fname' value='"+data.getFname()+"'></td></tr>"
						
						+ "<tr><td>Mname:</td> "
						+ "<td><input type='text' name='mname' value='"+data.getMname()+"'></td></tr>"
						
						+ "<tr><td>Lname:</td>"
						+ "<td><input type='text' name='lname' value='"+data.getLname()+"'></td></tr>"
						
						+ "<tr><td>GFname:</td>" 
						+ "<td><input type='text' name='gfname' value='"+data.getGfname()+"'></td></tr>"
						
						+ "<tr><td>GMname:</td>"
						+ "<td><input type='text' name='gmname' value='"+data.getGmname()+"'></td></tr>"
						
						+ "<tr><td>GLname:</td>"
						+ "<td><input type='text' name='glname' value='"+data.getGlname()+"'></td></tr>"
						
						+ "<tr><td>IsAdmin:</td>"
						+ "<td><select name='isadmin'>";
				if(data.getIsadmin().equalsIgnoreCase("Y")) {
					htmlresp+= "<option value='Y' selected>Yes</option>"
						+ "<option value='N'>No</option></select></td></tr>";
				}else {
					htmlresp+= "<option value='N' selected>No</option>"
							+ "<option value='Y'>Yes</option></select></td></tr>";
				}
					htmlresp+= "<tr><td>Password:</td>"
						+ "<td><input type='password' name='password' value='"+data.getPassword()+"'></td></tr>"
						
						+ "<tr><td><input type='submit' value='Update'></td>"
						+ "<td><input type='reset' value='reset'></td></tr></table></form></body></html>";
				out.print(htmlresp);
			}
			
}
		
	}

