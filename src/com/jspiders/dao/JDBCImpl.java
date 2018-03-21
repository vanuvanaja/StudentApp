package com.jspiders.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspiders.dto.StdentBean;
import com.mysql.jdbc.Driver;

public class JDBCImpl implements StudentDAO {
	
	public StdentBean authenticate(int regno, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StdentBean data = null;

		try {
			Driver ref = new Driver();
			DriverManager.registerDriver(ref);
			String url = "jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";
			con = DriverManager.getConnection(url);

			String sql = "select  * from student si, guardian_info gi, student_otherinfo so"
					+ " where si.regno=gi.regno and si.regno=so.regno" + " and so.regno=? and so.password=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regno);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			// StdentBean data=null;
			if (rs.next()) {
				data = new StdentBean();
				data.setRegno(rs.getInt("regno"));
				data.setFname(rs.getString("fname"));
				data.setMname(rs.getString("mname"));
				data.setLname(rs.getString("lname"));
				data.setGfname(rs.getString("gfname"));
				data.setGmname(rs.getString("gmname"));
				data.setGlname(rs.getString("glname"));
				data.setPassword(rs.getString("password"));
				data.setIsadmin(rs.getString("isadmin"));

			}

			return data;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public StdentBean getstudentdeatails(int regno) {
		String query = "select * from student si, student_otherinfo so,guardian_info gi"
				+ " where si.regno=so.regno and si.regno=gi.regno and si.regno=? ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Driver ref = new Driver();
			DriverManager.registerDriver(ref);

			String url = "jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";
			con = DriverManager.getConnection(url);

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, regno);
			rs = pstmt.executeQuery();
			StdentBean data = null;
			if (rs.next()) {
				data = new StdentBean();
				data.setRegno(rs.getInt("regno"));
				data.setFname(rs.getString("fname"));
				data.setMname(rs.getString("mname"));
				data.setLname(rs.getString("lname"));
				data.setGfname(rs.getString("gfname"));
				data.setGmname(rs.getString("gmname"));
				data.setGlname(rs.getString("glname"));
				data.setIsadmin(rs.getString("isadmin"));
				data.setPassword(rs.getString("password"));

			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	public void UbdateStudentdetails(StdentBean data) {
		String query1 = " update student_otherinfo set isadmin=?,password=? " + " where  regno=?";
		String query2 = " update student set fname=?,mname=?,lname=?" + " where regno=?";
		String query3 = " update guardian_info set gfname=?, gmname=?,glname=?" + " where regno=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		// String regnum = req.getParameter("regno");

		String url = "jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";

		try {
			Driver ref = new Driver();
			DriverManager.registerDriver(ref);

			con = DriverManager.getConnection(url);

			pstmt = con.prepareStatement(query2);
			pstmt.setString(1, data.getFname());
			pstmt.setString(2, data.getMname());
			pstmt.setString(3, data.getLname());
			pstmt.setInt(4, data.getRegno());
			int count1 = pstmt.executeUpdate();

			pstmt1 = con.prepareStatement(query3);
			pstmt1.setString(1, data.getGfname());
			pstmt1.setString(2, data.getGmname());
			pstmt1.setString(3, data.getGlname());
			pstmt1.setInt(4, data.getRegno());
			int count2 = pstmt1.executeUpdate();

			pstmt2 = con.prepareStatement(query1);
			pstmt2.setString(1, data.getIsadmin());
			pstmt2.setString(2, data.getPassword());
			pstmt2.setInt(3, data.getRegno());
			int count3 = pstmt2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (pstmt1 != null) {
				try {
					pstmt1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public boolean createprofile(StdentBean data) {

		String query = "insert into student values(?,?,?,?)";
		String query1 = "insert into guardian_info values(?,?,?,?)";
		String query2 = "insert into student_otherinfo values(?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			Driver ref = new Driver();
			DriverManager.registerDriver(ref);

			String dburl = "jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";
			con = DriverManager.getConnection(dburl);
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, data.getRegno());
			pstmt.setString(2, data.getFname());
			pstmt.setString(3, data.getMname());
			pstmt.setString(4, data.getLname());
			int count = pstmt.executeUpdate();

			pstmt1 = con.prepareStatement(query1);
			pstmt1.setInt(1, data.getRegno());
			pstmt1.setString(2, data.getGfname());
			pstmt1.setString(3, data.getGmname());
			pstmt1.setString(4, data.getGlname());
			int count1 = pstmt1.executeUpdate();

			pstmt2 = con.prepareStatement(query2);
			pstmt2.setInt(1, data.getRegno());
			pstmt2.setString(2, data.getIsadmin());
			pstmt2.setString(3, data.getPassword());
			int count2 = pstmt2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean changepassword(int regno, String password, String newpassword) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update student_otherinfo set password=?" + " where regno=? and password=?";

		try {
			Driver ref = new Driver();
			DriverManager.registerDriver(ref);

			String url = "jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";
			con = DriverManager.getConnection(url);
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, newpassword);
			pstmt.setInt(2, regno);
			pstmt.setString(3, password);

			int count = pstmt.executeUpdate();

			if (count > 0)

				return true;

			else
				return false;

		}

		catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public List<StdentBean> getallstudentdetails(int from, int to) {

		List<StdentBean> list = new ArrayList<StdentBean>();
		Connection con = null;
		// Statement stmt=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/vanu?user=j2ee&password=j2ee";
		String query = "select * from student si, guardian_info gi, student_otherinfo so"
				+ " where si.regno=gi.regno and si.regno=so.regno and si.regno between ? and ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url);

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, from);
			pstmt.setInt(2, to);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StdentBean data = new StdentBean();
				data.setRegno(rs.getInt("regno"));
				data.setFname(rs.getString("fname"));
				data.setMname(rs.getString("mname"));
				data.setLname(rs.getString("lname"));
				data.setGfname(rs.getString("gfname"));
				data.setGmname(rs.getString("gmname"));
				data.setGlname(rs.getString("glname"));
				data.setIsadmin(rs.getString("isadmin"));
				data.setPassword(rs.getString("password"));
				list.add(data);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null)

			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null)

			{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}



	public boolean deletestudentservlet(int regno) {
		
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
		
		
		
		return false;
	}
}