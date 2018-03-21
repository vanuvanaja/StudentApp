<%@page import="com.jspiders.dto.StdentBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<m
eta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<%
StdentBean data=(StdentBean)session.getAttribute("data");
%>


<body>
<table>
<tr>
<th>Regno</th>
<th>First Name</th>
<th>Middle Name</th>
<th>Last  Name</th>
<th>GFirst Name</th>
<th>GMiddle Name</th>
<th>GLast Name</th>
<th>Isadmin</th>
</tr>
<tr>
<td><%= data.getRegno() %></td>
<td><%= data.getFname() %></td>
<td><%= data.getMname() %></td>
<td><%= data.getLname() %></td>
<td><%= data.getGfname() %></td>
<td><%= data.getGmname() %></td>
<td><%= data.getGlname() %></td>
<td><%= data.getIsadmin() %></td>
</tr>
</table>
<% if(data.getIsadmin().equalsIgnoreCase("y")){ %>
<a href="./ViewAllStudentsServlet1">click here to viewe all students</a>
<% } %>
<% 
String msg=(String)session.getAttribute("msg");
session.removeAttribute("msg");
if(msg!=null)
{
	
%>
<p><%=msg %></p>
<%} %>

</body>
</html>