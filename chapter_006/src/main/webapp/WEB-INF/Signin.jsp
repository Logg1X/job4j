<%--
  Created by IntelliJ IDEA.
  User: Per4mancerror
  Date: 22.12.2018
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<table>
    <c:if test="${param.signOut == true}">
        <c:set scope="session" var="currentUser" value="${null}"/>
        <c:set scope="request" var="result" value="signOut"/>
    </c:if>
<c:if test="${currentUser == null}">
    <th>Sign In:</th>
    <form action="${pageContext.servletContext.contextPath}/signin" method="post">
        <tr>
            <td>Login :</td>
            <td><input type="text" name="login"/></td>
        </tr>
        <tr>
            <td>Password :</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>

                <input type="submit" value="Sign in">
            </td>
    </form>
    <td>
        <form action="${pageContext.servletContext.contextPath}/createUser" method="get">
            <input type="submit" value="Registration">
        </form>
    </td>
    </tr>
</c:if>
<c:if test="${currentUser != null}">
    <th>Sign out:</th>
    <form name="Sign Out" method="get">
    <tr>
    <td>
    <input type="hidden" name="signOut" value="${true}"/>
    <input type="submit" value="Sign out">
    </td>
    </form>
    <%--<td>--%>
    <%--<form action="${pageContext.servletContext.contextPath}/createUser" method="get">--%>
    <%--<input type="submit" value="Registration">--%>
    <%--</form>--%>
    <%--</td>--%>
    </tr>
    </c:if>
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
    </body>
    </html>
