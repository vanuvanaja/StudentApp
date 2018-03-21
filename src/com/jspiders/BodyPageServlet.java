package com.jspiders;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/body")

public class BodyPageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String page = req.getParameter("page");
		String url = "";
		if (page.equalsIgnoreCase("Home")) {
			url = "./Home.jsp";
		} else if (page.equalsIgnoreCase("SearchStudent")) {
			url = "./search.html";
		} else if (page.equalsIgnoreCase("ChangePassword")) {
			url = "./change_password.html";
		} else if (page.equalsIgnoreCase("CreateProfile")) {
			url = "./createprofile.html";
		} else if (page.equalsIgnoreCase("Logout")) {
			url = "./logout";
			req.getRequestDispatcher(url).forward(req, resp);
			return;
		}

		RequestDispatcher dispacher;
		dispacher = req.getRequestDispatcher("./header.jsp");
		dispacher.include(req, resp);

		dispacher = req.getRequestDispatcher(url);
		dispacher.include(req, resp);

		dispacher = req.getRequestDispatcher("./footer.html");
		dispacher.include(req, resp);

	}
}
