package com.jspiders;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.StdentBean;

public class ViewAllStudentsServlet1  extends HttpServlet{
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String stringFrom=req.getParameter("from");
		String stringTo = req.getParameter("to");
		int from=1;
		int to=5;
		if(stringFrom!=null&&stringTo!=null)
		{
		from =Integer.parseInt(stringFrom);
		to = Integer.parseInt(stringTo);
		
		}
		StudentDAO dao=ServiceGenrator.genrateDao();
		List<StdentBean> list =dao.getallstudentdetails(from, to);
		
		req.getRequestDispatcher("./header.jsp").include(req, resp);
		
		req.setAttribute("data", list);
		req.getRequestDispatcher("viewall.jsp").forward(req,resp);
		
		req.getRequestDispatcher("./footer.html").include(req, resp);
		
		/*resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
				
				///include 
				//RequestDispatcher dispatcher=req.getRequestDispatcher("header.Jsp");
				//dispatcher.include(req, resp);
				out.print("Student Information");
				out.println();
				out.println("<table border=1>"
						+ "<tr><th>Regno</th>"
						+ "<th>First Nmae</th>"
						+ "<th>Middle Name</th>"
						+ "<th>Last Name</th>"
						+ "<th>Guardian FName</th>"
						+ "<th>Guardian MName</th>"
						+ "<th>Guardian LName</th>"
						+ "<th>IS admin</th></tr>");
				for(StdentBean data:list)
				{
					out.print(" <tr><td>" +data.getRegno()+"</td>"
							+ "<td>" +data.getFname()+"</td>"
							+ "<td>" +data.getMname()+"</td>"
							+ "<td>" +data.getLname()+"</td>"
							+ "<td>" +data.getGfname()+"</td>"
							+ "<td>" +data.getGmname()+"</td>"
							+ "<td>" +data.getGlname()+"</td>"
							+ "<td>" +data.getIsadmin()+"</td>"
							+ "<td><a href='./DeleteStudentServlet1?regno="+ data.getRegno()+"'>delete+"'>delete</a></td>"
							+ "<td><a href='./EditStudentServlet1?regno="+ data.getRegno()+"'>edit</a></td>"
							+ "</tr>");
				}
				out.println("</table>");
				
				out.println("<a href='./ViewAllStudentsServlet1?from="+(from+5)
						+"&to="+(to+5)+"'>Next</a>");
				
				if(from!=1) {
				out.println("<a href='./ViewAllStudentsServlet1?from="+(from-5)
				
				+"&to="+(to-5)+"'>Prev</a>");
				}
				
		*/
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		
	}


}
