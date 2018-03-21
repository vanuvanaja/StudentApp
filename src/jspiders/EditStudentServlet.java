package jspiders;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class EditStudentServlet  extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int regno=Integer.parseInt(req.getParameter("regno"));
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String url="jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";
		
		String sql ="select * from student si,guardian_info gi, student_otherinfo so"
				+ " where si.regno=gi.regno and si.regno=so.regno and si.regno=?";
		
		try {
			Driver ref =new Driver();
			DriverManager.registerDriver(ref);
			con=DriverManager.getConnection(url);
			
			
			stmt=con.prepareStatement(sql);
			stmt.setInt(1,regno);
			rs=stmt.executeQuery();
			
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			
			if(rs.next())
			{
				String htmlresp="<html><body><form action='./UpdateStudentServlet' method='Post'>"
						+ "<table><tr><td>Regno:</td>"
						+ "<td><input type='text' name='regno' value='"+regno+"'></td></tr>"
						
						+ "<tr><td>Fname:</td>"
						+ "<td><input type='text' name='fname' value='"+rs.getString("fname")+"'></td></tr>"
						
						+ "<tr><td>Mname:</td> "
						+ "<td><input type='text' name='mname' value='"+rs.getString("mname")+"'></td></tr>"
						
						+ "<tr><td>Lname:</td>"
						+ "<td><input type='text' name='lname' value='"+rs.getString("lname")+"'></td></tr>"
						
						+ "<tr><td>GFname:</td>" 
						+ "<td><input type='text' name='gfname' value='"+rs.getString("gfname")+"'></td></tr>"
						
						+ "<tr><td>GMname:</td>"
						+ "<td><input type='text' name='gmname' value='"+rs.getString("gmname")+"'></td></tr>"
						
						+ "<tr><td>GLname:</td>"
						+ "<td><input type='text' name='glname' value='"+rs.getString("glname")+"'></td></tr>"
						
						+ "<tr><td>IsAdmin:</td>"
						+ "<td><select name='isadmin'>";
				if(rs.getString("isadmin").equalsIgnoreCase("Y")) {
					htmlresp+= "<option value='Y' selected>Yes</option>"
						+ "<option value='N'>No</option></select></td></tr>";
				}else {
					htmlresp+= "<option value='N' selected>No</option>"
							+ "<option value='Y'>Yes</option></select></td></tr>";
				}
					htmlresp+= "<tr><td>Password:</td>"
						+ "<td><input type='password' name='password' value='"+rs.getString("password")+"'></td></tr>"
						
						+ "<tr><td><input type='submit' value='Update'></td>"
						+ "<td><input type='reset' value='reset'></td></tr></table></form></body></html>";
				out.print(htmlresp);
			}
			
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
