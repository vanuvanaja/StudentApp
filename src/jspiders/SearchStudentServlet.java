package jspiders;

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

import com.mysql.jdbc.Driver;

public class SearchStudentServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int regno=Integer.parseInt(req.getParameter("regno"));
		
		String query="select * from student si, student_otherinfo so,guardian_info gi"
				+ " where si.regno=so.regno and si.regno=gi.regno and si.regno=? ";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		PrintWriter out = resp.getWriter();
		
		try {
			Driver ref=new Driver();
			DriverManager.registerDriver(ref);
			
		String url="jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";
		con=DriverManager.getConnection(url);
		
		pstmt=con.prepareStatement(query);	
		pstmt.setInt(1, regno);
		rs=pstmt.executeQuery();
		resp.setContentType("text/html");
		
		if(rs.next())
		{
			String htmlresp="<table border=1>"
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
			+ "<td>" +rs.getString("password")+"</td>"
			+ "</table>";

			out.print(htmlresp);
		}
	
		else {
			String err ="error regno not present";
			out.print(err);
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
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
		
	}

}
