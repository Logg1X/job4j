<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<table>
    <c:if test="${users != null && !users.isEmpty()}">
        <%--<%List<User> users = (List<User>) request.getAttribute("users");%>--%>
        <%--<%if (users != null && !users.isEmpty()) {%>--%>
        <tr>
            <th>
                <form action="${pageContext.servletContext.contextPath}/createUser" method="get">
                    <input type="submit" value="ID      +">
                </form>
            </th>
            <th>NAME</th>
            <th>LOGIN</th>
            <th>EMAIL</th>
            <th>EDIT</th>
            <th>DELETE</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.mail}"/></td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/edit?id=<%="id"%>" method="get">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/users" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${users.isEmpty()}">
        <tr>
            <td>Users list is empty. Click '+' to add
                <form action="${pageContext.servletContext.contextPath}/createUser" method="get">
                    <input type="submit" value="ID      +">
                </form>
            </td>
        </tr>
    </c:if>
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
            border: 2px solid #252421;
            padding: 6px;
        }

        input {
            height: 30px;
            width: 90px;
            top: 50%;
            left: 50%;
        }

    </style>
</head>
</html>
