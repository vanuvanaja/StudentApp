package com.jspiders;

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
import javax.servlet.http.HttpSession;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.StdentBean;
import com.mysql.jdbc.Driver;

public class ChangePasswordServlet1 extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if (session != null) {
			String newpassword = req.getParameter("Newpassword");
			String retypenewpassword = req.getParameter("RetypeNewpassword");
			int regno = Integer.parseInt(req.getParameter("regno"));
			String password = req.getParameter("currpassword");
			//resp.setContentType("text/html");
			// PrintWriter out = resp.getWriter();
			String msg;
			if (newpassword.equalsIgnoreCase(retypenewpassword)) {

				StudentDAO dao = ServiceGenrator.genrateDao();
				boolean check = dao.changepassword(regno, password, newpassword);
				if (check) {
					// out.print("updated");
					msg = "password changed";
				}

				else {
					// out.print("incorrect password");
					msg = "regno and password not matched";
				}

			} else {
				// out.print("password not macthing");
				msg = "password not matching";
			}
			session.setAttribute("msg", msg);
			resp.sendRedirect("./body?page=Home");
			
		} else {
			resp.sendRedirect("./login.html");
		}

	}
}
