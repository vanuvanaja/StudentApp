package jspiders;

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

import com.mysql.jdbc.Driver;

public class LoginServlet2 extends HttpServlet {
	
	

protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	

	String regno = req.getParameter("regno");
	String password = req.getParameter("password");
	resp.setContentType("text/html");
	PrintWriter out= resp.getWriter();	

	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	
	try {
		Driver ref =new Driver();
		DriverManager.registerDriver(ref);
		
		String url="jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";
		con=DriverManager.getConnection(url);
		
		String sql="select  * from student si, guardian_info gi, student_otherinfo so"
				+ " where si.regno=gi.regno and si.regno=so.regno"
				+ " and so.regno=? and so.password=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,Integer.parseInt(regno));
		pstmt.setString(2, password);
		
		boolean check=pstmt.execute();
		if(check)
		{	rs= pstmt.getResultSet();
				if(rs.next())
					{
						RequestDispatcher dispatcher=req.getRequestDispatcher("header.html");
						dispatcher.include(req, resp);
						out.print("<br>");
						//out.print("<h1 align='center' color='blue'>valid data");
					out.print("<table border=1>"
							+ "<tr><th>Regno</th>"
							+ "<th>First Nmae</th>"
							+ "<th>Middle Name</th>"
							+ "<th>Last Name</th>"
							+ "<th>Guardian FName</th>"
							+ "<th>Guardian MName</th>"
							+ "<th>Guardian LName</th>"
							+ "<th>IS admin</th></tr>"
					+ "<tr><td>" +rs.getInt("regno")+"</td>"
					+ "<td>" +rs.getString("fname")+"</td>"
					+ "<td>" +rs.getString("mname")+"</td>"
					+ "<td>" +rs.getString("lname")+"</td>"
					+ "<td>" +rs.getString("gfname")+"</td>"
					+ "<td>" +rs.getString("gmname")+"</td>"
					+ "<td>" +rs.getString("glname")+"</td>"
					+ "<td>" +rs.getString("isadmin")+"</td>"
					+ "</table>");
					if(rs.getString("isadmin").equalsIgnoreCase("y"))
					{
						out.print("<a href='./ViewAllStudentsServlet1'>click here to vieve all students</a>");
					}
					}
					else
					{
						RequestDispatcher dispatcher2=req.getRequestDispatcher("header.html");
					dispatcher2.include(req, resp);
						out.print("<h1>incorrect credentialss</h1>");
						out.print("<br>");
						RequestDispatcher dispatcher1=req.getRequestDispatcher("login.html");
						dispatcher1.include(req, resp);
						
					}
					
					
					out.print("<br>");
					RequestDispatcher dispatcher1=req.getRequestDispatcher("footer.html");
					dispatcher1.include(req, resp);
					}
	
		
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	finally {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
			
			
			
			

}

}
