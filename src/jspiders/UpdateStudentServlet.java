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


public class UpdateStudentServlet  extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query1= " update student_otherinfo set isadmin=?,password=? "
				+ " where  regno=?";
		String query2= " update student set fname=?,mname=?,lname=?"
				+ " where regno=?";
		String query3=" update guardian_info set gfname=?, gmname=?,glname=?"
				+ " where regno=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		//String regnum = req.getParameter("regno");
		
		//int regno = Integer.parseInt(regnum);
		
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
		
		String url = "jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";
		
		
		try {
			Driver ref =new Driver();
			DriverManager.registerDriver(ref);
			
			con=DriverManager.getConnection(url);
			
			pstmt=con.prepareStatement(query2);
			pstmt.setString(1, fname);
			pstmt.setString(2, mname);
			pstmt.setString(3, lname);
			pstmt.setInt(4, regno);
			int count1=pstmt.executeUpdate();
			
			
		
			pstmt1=con.prepareStatement(query3);
			pstmt1.setString(1, gfname);
			pstmt1.setString(2, gmname);
			pstmt1.setString(3, glname);
			pstmt1.setInt(4, regno);
			int count2=pstmt1.executeUpdate();
			
			pstmt2=con.prepareStatement(query1);
			pstmt2.setString(1, isadmin);
			pstmt2.setString(2, password);
			pstmt2.setInt(3, regno);
			int count3=pstmt2.executeUpdate();
			
			
			resp.setContentType("text/html");
			PrintWriter out=resp.getWriter();
			if(count1>0 && count2>0 && count3>0)
			 {
				 out.print("ubdated");
			 }
			else {
				out.print("not updated");
			}
			
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
