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
<form action="${pageContext.servletContext.contextPath}/createUser" method="post">
    <table>
        <th>Create new user:</th>
        <tr>
            <td>Name :</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Login :</td>
            <td><input type="text" name="login"/></td>
        </tr>
        <tr>
            <td>Password :</td>
            <td><input type="text" name="password"/></td>
        </tr>
        <tr>
            <td>Email :</td>
            <td><input type="text" name="email"/></td>
        </tr>
        </br>
        <th>
            <input type="submit" value="create">
        </th>
    </table>
    <style type="text/css">
        table {
            border-collapse: collapse;
        }

        td {
            border: 2px solid #252421;
            padding: 6px;
        }

        input {
            height: 30px;
            width: 130px;
            top: 50%;
            left: 50%;
        }
    </style>
</form>
</body>
</html>
