<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<c:if test="${user != null}">
    <table>
    <th>Update user:</th>
    <form action="${pageContext.servletContext.contextPath}/edit" method="post">
    <input name="id" type="hidden" value="${user.id}"/>
    <tr>
    <td>Name :</td>
    <td><input type="text" name="name" value="${user.name}"></td>
    </tr>
    <tr>
    <td>Login :</td>
    <td><input type="text" name="login" value="${user.login}"></td>
    </tr>
    <tr>
    <td>Password :</td>
    <td><input type="password" name="password" value="${user.password}"></td>
    </tr>
    <tr>
    <td>Email :</td>
    <td><input type="text" name="email" value="${user.mail}"></td>
    </tr>
    </tr>
    <c:if test="${access != true}">
        <tr>
            <td>Role :</td>
            <td><i>Current role: "${user.role}"</i>
            <input type="hidden" name="role" value="${user.role}"></td>
        </tr>
    </c:if>
        <c:if test="${access == true}">
            <tr>
                <td>Role :</td>
                <td><i>Current role: "${user.role}"</i>
                    </br>
                    <select name="role">
                        <option value="USER">USER</option>
                        <option value="ADMIN">ADMIN</option>
                    </select></td>
            </tr>
        </c:if>
        </br>
        <th>
            <input type="submit" name="edit">
        </th>
        </form>
        </table>
        <style type="text/css">
            table {
                border-collapse: collapse;
            }

            td {
                border: 2px solid #252421;
                padding: 6px;
            }

            input, select {
                height: 30px;
                width: 130px;
                top: 50%;
                left: 50%;
            }


        </style>
    </c:if>
    <c:out value="${result}"/>
    </body>
    </html>
