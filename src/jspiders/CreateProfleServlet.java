package jspiders;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class CreateProfleServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String query="insert into student values(?,?,?,?)";
		String query1="insert into guardian_info values(?,?,?,?)";
		String query2="insert into student_otherinfo values(?,?,?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		
		
		
			try {
				Driver ref = new Driver();
				DriverManager.registerDriver(ref);
				
				 String dburl="jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";
				 con=DriverManager.getConnection(dburl);
				con.setAutoCommit(false);
				 int regno=Integer.parseInt(req.getParameter("regno"));
				 String fname= req.getParameter("fname");
				 String mname= req.getParameter("mname");
				 String lname= req.getParameter("lname");
				  String gfname= req.getParameter("gfname");
				  String gmname= req.getParameter("gmname");
				  String glname= req.getParameter("glname");
				  String isadmin=req.getParameter("isadmin");
				  String password=req.getParameter("password");
				 
				  pstmt= con.prepareStatement(query);
				  pstmt.setInt(1, regno);
				  pstmt.setString(2, fname);
				  pstmt.setString(3, mname);
				  pstmt.setString(4, lname);
				  int count=pstmt.executeUpdate();
				  
				  pstmt1= con.prepareStatement(query1);
				  pstmt1.setInt(1, regno);
				  pstmt1.setString(2, gfname);
				  pstmt1.setString(3, gmname);
				  pstmt1.setString(4, glname);
				  int count1=pstmt1.executeUpdate();
				  
				  pstmt2= con.prepareStatement(query2);
				  pstmt2.setInt(1, regno);
				  pstmt2.setString(2, isadmin);
				  pstmt2.setString(3, password);
				  int count2=pstmt2.executeUpdate();
				   
				  
				  PrintWriter out=resp.getWriter();
				  if(count>0 && count1>0 && count2>0)
				  {
					 con.commit();
					 out.print("<br>");
						RequestDispatcher dispatcher1=req.getRequestDispatcher("header.html");
						dispatcher1.include(req, resp);
					 out.print("profile cteated"); 
					 out.print("<br>");
						RequestDispatcher dispatcher2=req.getRequestDispatcher("footer.html");
						dispatcher2.include(req, resp);
					 
				  }
				  else {
					  out.print("profile not created");	
					  out.print("<br>");
						RequestDispatcher dispatcher1=req.getRequestDispatcher("createprofile.html");
						dispatcher1.include(req, resp);
					  }
				  
				  
				  
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
	

}
