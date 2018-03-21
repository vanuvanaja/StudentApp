package jspiders;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAllStudentsServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con=null;
		//Statement stmt=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String url="jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";
		String query="select * from student si, guardian_info gi, student_otherinfo so"
				+ " where si.regno=gi.regno and si.regno=so.regno and si.regno between ? and ?";
		try {
				 Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection(url);
				
				
				pstmt=con.prepareStatement(query);
				
				String stringFrom=req.getParameter("from");
				String stringTo = req.getParameter("to");
				
				int from=1;
				int to=5;
				
				if(stringFrom!=null&&stringTo!=null)
				{
				from =Integer.parseInt(stringFrom);
				to = Integer.parseInt(stringTo);
				
				}
				
				pstmt.setInt(1,from);
				pstmt.setInt(2, to);
				
				rs=pstmt.executeQuery();
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				
				///include 
				RequestDispatcher dispatcher=req.getRequestDispatcher("header.html");
				dispatcher.include(req, resp);
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
				while(rs.next())
				{
					out.print(" <tr><td>" +rs.getInt("regno")+"</td>"
							+ "<td>" +rs.getString("fname")+"</td>"
							+ "<td>" +rs.getString("mname")+"</td>"
							+ "<td>" +rs.getString("lname")+"</td>"
							+ "<td>" +rs.getString("gfname")+"</td>"
							+ "<td>" +rs.getString("gmname")+"</td>"
							+ "<td>" +rs.getString("glname")+"</td>"
							+ "<td>" +rs.getString("isadmin")+"</td>"
							+ "<td><a href='./DeleteStudentservlet?regno="+ rs.getInt("regno")+"'>delete</a></td></tr>");
				}
				out.println("</table>");
				
				out.println("<a href='./ViewAllStudentsServlet?from="+(from+5)
						+"&to="+(to+5)+"'>Next</a>");
				
				if(from!=1) {
				out.println("<a href='./ViewAllStudentsServlet?from="+(from-5)
				
				+"&to="+(to-5)+"'>Prev</a>");
				}
				
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null)

				{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
			if(rs!=null)

		{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
		}
		
	}

}
