<!DOCTYPE html>
<%@page import="com.jspiders.dto.StdentBean"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%
StdentBean data=(StdentBean)session.getAttribute("data");
%>

<body>
<table border="1">
<tr>
<h2>
<td><a href="./body?page=Home">home</a></td>

<td> <a href="./body?page=SearchStudent">search</a></td>
<td> <a  href="./body?page=ChangePassword">changepassword</a></td>
<%
if(data.getIsadmin().equalsIgnoreCase("Y"))
{
 %>
<td> <a href="./body?page=CreateProfile">createprofile</a><td>

<%} %>
<td> <a  href="./body?page=Logout">Logout</a></td>
</h2>
</tr>
</table>

</body>
</html>