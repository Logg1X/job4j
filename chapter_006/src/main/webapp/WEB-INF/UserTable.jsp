<%@ page import="crud.servlet.User" %>
<%@ page import="crud.servlet.Validate" %>
<%@ page import="crud.servlet.ValidateService" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: p.toporov
  Date: 12.12.2018
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<table>
    <%!private final Validate logic = ValidateService.getInstance();%>
    <%List<User> users = logic.findAll();%>
    <%if (users != null && !users.isEmpty()) {%>
    <tr>
        <th>
            <form action="<%=request.getContextPath()%>/createUser" method="get">
                <input type="submit" value="ID      +">
            </form>
        </th>
        <th>NAME</th>
        <th>LOGIN</th>
        <th>EMAIL</th>
        <th>EDIT</th>
        <th>DELETE</th>
    </tr>
    <%} else {%>
    <tr>
        <td>Users list is empty. Click '+' to add
            <form action="<%=request.getContextPath()%>/createUser" method="get">
                <input type="submit" value="ID      +">
            </form>
        </td>
    </tr>
    <%}%>
    <%for (User user : users) {%>
    <tr>
    <td><%=user.getId()%>

    </td>
    <td><%=user.getName()%>
    </td>
    <td><%=user.getLogin()%>
    </td>
    <td><%=user.getMail()%>
    </td>
    <td>
    <form action="<%=request.getContextPath()%>/edit?id=<%="id"%>" method="get">
    <input type="hidden" name="id" value="<%=user.getId()%>">
    <input type="submit" value="Edit">
    </form>
    </td>
    <td>
    <form action="<%=request.getContextPath()%>/users?action=<%="action"%>id=<%="id"%>" method="post">
    <input type="hidden" name="action" value="delete">
    <input type="hidden" name="id" value="<%=user.getId()%>">
    <input type="submit" value="Delete">
    </form>
    </td>
    </tr>
    <%}%>
</table>
</body>
<head>
    <title>User Table</title>
    <style type="text/css">
        table {
            border-collapse: collapse;
        }

        th {
            background: #a596cc;
            text-align: center;
        }

        td, th {
            border: 1px solid #252421;
            padding: 4px;
        }
    </style>
</head>
</html>
