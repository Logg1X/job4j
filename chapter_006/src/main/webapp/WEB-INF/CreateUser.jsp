<%--
  Created by IntelliJ IDEA.
  User: Per4mancerror
  Date: 13.12.2018
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/createUser" method="post">
    Name : <input type="text" name="name"/>
    Login : <input type="text" name="login"/>
    Email : <input type="text" name="email"/>
    <input type="submit" value="create">
</form>
</body>
</html>
