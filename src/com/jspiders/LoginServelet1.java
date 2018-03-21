package com.jspiders;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.dao.JDBCImpl;
import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.StdentBean;
import com.mysql.jdbc.Driver;

public class LoginServelet1 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String regno = req.getParameter("regno");
		String password = req.getParameter("password");
		String checked = req.getParameter("check");
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		/// Avoiding dependency injection
		// JDBCImpl impl=new JDBCImpl();
		// StdentBean datas = impl.authenticate(Integer.parseInt(regno),password);

		StudentDAO dao = ServiceGenrator.genrateDao();
		StdentBean datas = dao.authenticate(Integer.parseInt(regno), password);

		RequestDispatcher dispatcher;
		if (datas != null)
		{
			if(checked!=null)
			{
				Cookie cokie=new Cookie("regno", regno);
					resp.addCookie(cokie);	
			}

		
			HttpSession session = req.getSession();
			session.setAttribute("data", datas);

			dispatcher = req.getRequestDispatcher("./header.jsp");
			dispatcher.include(req, resp);

			dispatcher = req.getRequestDispatcher("./Home.jsp");
			dispatcher.include(req, resp);

			dispatcher = req.getRequestDispatcher("./footer.html");
			dispatcher.include(req, resp);

		} else {

			out.print("<h1>incorrect credentialss</h1>");
			out.print("<br>");
			dispatcher = req.getRequestDispatcher("./login.html");
			dispatcher.include(req, resp);
		}

	}

}
