<%@page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<%
Cookie[] cookies=request.getCookies();
String regno="";
if(cookies!=null)
{
	for(Cookie  cookie:cookies)
	{
		if(cookie.getName().equalsIgnoreCase("regno")){
			regno=cookie.getValue();
		}
	}
}
%>

<body>
        
    <fieldset>
     <legend>Login to studentApp</legend>
<form action="http://localhost:8081/StudentsApp/LoginServlet1" method="Post">
<table>
    
    <tr>
   <td>Reg no:</td>
   <td> <input type="number" name="regno" value="<%=regno %>"></td>
    </tr>
   
   <tr>
   <td> Password:</td>
   <td><input type="password" name="password"></td></tr>
   
   <tr>
   <td> Remember me:
   </td>
   <td><input type="checkbox" name="check" value="checked"></td>
   </tr>
 
   <tr>
   <td> <input type="submit" name="submit" value="sing in"></td>
    </tr>


</table>
</form>
</fieldset>
    
</body>
</html>