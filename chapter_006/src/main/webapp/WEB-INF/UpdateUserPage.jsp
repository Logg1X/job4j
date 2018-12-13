<%@ page import="crud.servlet.StoresException" %>
<%@ page import="crud.servlet.User" %>
<%@ page import="crud.servlet.Validate" %>
<%@ page import="crud.servlet.ValidateService" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: p.toporov
  Date: 13.12.2018
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<%!private final Validate logic = ValidateService.getInstance();%>
<body>
<%User user = (User) request.getAttribute("user");%>
<%if (user != null) {%>
<form action="<%=request.getContextPath()%>/edit" method="post">
    <input name="id" type="hidden" value="<%=user.getId()%>"/>
    Name : <input type="text" name="name" value="<%=user.getName()%>">
    Login : <input type="text" name="login" value="<%=user.getLogin()%>">
    Email : <input type="text" name="email" value="<%=user.getMail()%>">
    <input type="submit" name="edit">
</form>
    <%}%>
    <%=request.getAttribute("result")%>
<%--<form action="<%=request.getContextPath()%>/usersTable" method="get">--%>
    <%--<input type="submit" value="<----">--%>
<%--</form>--%>
</body>
</html>
