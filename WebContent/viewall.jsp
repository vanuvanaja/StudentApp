<%@page import="com.jspiders.dto.StdentBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<% List<StdentBean>  list =(List) request.getAttribute("data");
String stringFrom=request.getParameter("from");
		String stringTo = request.getParameter("to");
		int from=1;
		int to=5;
		if(stringFrom!=null&&stringTo!=null)
		{
		from =Integer.parseInt(stringFrom);
		to = Integer.parseInt(stringTo);
		
		}
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
<%for(StdentBean data : list){ %>
<tr>
	<td><%=data.getRegno() %> </td>
	<td><%=data.getFname() %></td>
	<td><%=data.getMname() %></td>
	<td><%=data.getLname() %></td>
	<td><%=data.getGfname() %></td>
	<td><%=data.getGmname() %></td>
	<td><%=data.getGlname() %></td>
	<td><%=data.getIsadmin() %></td>
	<td> <a href="./DeleteStudentServlet1?regno=<%=data.getRegno()%>">delete></a></td>
	<td><a href="./EditStudentServlet1?regno=<%=data.getRegno()%>">edit</a></td>
</tr>
<%} %>

</table>
<%if(from!=1){ %>

<a href="./ViewAllStudentsServlet1?from=<%=from-5 %>%to=<%=to-5 %>">&lt;&lt;prev</a>
<%} %>
<a href="./ViewAllStudentsServlet1?from=<%=from+5 %>%to=<%=to+5 %>">next&gt;&gt;</a>



</body>
</html>