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

public class ChangePasswordServlet  extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String query="update student_otherinfo set password=?"
				+ " where regno=? and password=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		PrintWriter out=resp.getWriter();
		
		String newpassword=req.getParameter("Newpassword");	
		String retypenewpassword=req.getParameter("RetypeNewpassword");
		
		if(newpassword.equalsIgnoreCase(retypenewpassword)) {
		
		try {
			Driver ref=new Driver();
			DriverManager.registerDriver(ref);

			String url="jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";
			con= DriverManager.getConnection(url);
			pstmt = con.prepareStatement(query);
			
			
			//Getting values from the  form
			int regno=Integer.parseInt(req.getParameter("regno"));
			String oldpassword=req.getParameter("currpassword");
			//String newpassword=req.getParameter("Newpassword");	
			//String retypenewpassword=req.getParameter("RetypeNewpassword");
			//pstmt.setInt(1, regno);
			//pstmt.setString(2, password);
			//int count=pstmt.executeUpdate();
			
			pstmt.setString(1,newpassword);
			pstmt.setInt(2, regno);
			pstmt.setString(3, oldpassword);
			
			
			int count=pstmt.executeUpdate();
			
	
			 if(count>0)
			{
				out.print("password changed");
			}
			
			
		
			else  {
				out.print("incorrect password");
			}
			
			
		}
		
			
			
		 catch (SQLException e) {
			e.printStackTrace();
		}
		
		}else {
			out.print("password not matching");
		}
		
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
		
	}

}


