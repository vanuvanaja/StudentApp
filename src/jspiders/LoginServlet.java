package jspiders;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		
		
		String regno = req.getParameter("regno");
		String password = req.getParameter("password");
		resp.setContentType("text/html");
		PrintWriter out= resp.getWriter();
		out.print("Regno="+ regno);
		out.print("<br>Password="+ password);
		
	}

}
