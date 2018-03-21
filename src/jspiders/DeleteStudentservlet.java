package jspiders;

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

import com.mysql.jdbc.Driver;

public class DeleteStudentservlet  extends HttpServlet{

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String regnum= req.getParameter("regno");
		int regno = Integer.parseInt(regnum);
		
		String query="delete from student where regno=?";
		String query1="delete from guardian_info where regno=?";
		String query2="delete from student_otherinfo where regno=?";
		
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		
		
		try {
			Driver ref=new Driver();
			DriverManager.registerDriver(ref);
			
			
			String url="jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";
			con= DriverManager.getConnection(url);
			
			pstmt= con.prepareStatement(query);
			pstmt.setInt(1, regno);
			pstmt.executeUpdate();
			
			pstmt1= con.prepareStatement(query1);
			pstmt1.setInt(1, regno);
			pstmt1.executeUpdate();
			
			pstmt2= con.prepareStatement(query2);
			pstmt2.setInt(1, regno);
			pstmt2.executeUpdate();
			
			PrintWriter out= resp.getWriter();
			out.print("Student deleted");
			
			
			
			
			
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
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(pstmt1!=null) {
				try {
					pstmt1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(pstmt2!=null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	}
