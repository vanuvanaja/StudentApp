package com.jspiders;

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
import javax.servlet.http.HttpSession;

import com.jspiders.dao.ServiceGenrator;
import com.jspiders.dao.StudentDAO;
import com.jspiders.dto.StdentBean;
import com.mysql.jdbc.Driver;

public class CreateProfileServlet1 extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			String msg;

			StdentBean bean = (StdentBean) session.getAttribute("data");
			if (bean.getIsadmin().equalsIgnoreCase("y")) {
				int regno = Integer.parseInt(req.getParameter("regno"));
				String fname = req.getParameter("fname");
				String mname = req.getParameter("mname");
				String lname = req.getParameter("lname");
				String gfname = req.getParameter("gfname");
				String gmname = req.getParameter("gmname");
				String glname = req.getParameter("glname");
				String isadmin = req.getParameter("isadmin");
				String password = req.getParameter("password");

				StdentBean data = new StdentBean();
				data.setRegno(regno);
				data.setFname(fname);
				data.setMname(mname);
				data.setLname(lname);
				data.setGfname(gfname);
				data.setGmname(gmname);
				data.setGlname(glname);
				data.setIsadmin(isadmin);
				data.setPassword(password);

				StudentDAO dao = ServiceGenrator.genrateDao();
				boolean check = dao.createprofile(data);
				// PrintWriter out=resp.getWriter();
				if (check)
					// out.println("<h1>profile creted</h1>");
					msg = "profile creted";
				else
					// out.println("<h1>profile Not creted</h1>");
					msg = "profile not created";
			} else {
				msg = "Become Admin and login";
			}
			session.setAttribute("msg", msg);
			resp.sendRedirect("./body?page=Home");
		} else {
			resp.sendRedirect("./login.html");
		}

	}
}
