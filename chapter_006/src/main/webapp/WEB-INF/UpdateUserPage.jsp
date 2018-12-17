<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>

<c:if test="${user != null}">
    <form action="${pageContext.servletContext.contextPath}/edit" method="post">
        <input name="id" type="hidden" value="${user.id}"/>
        Name : <input type="text" name="name" value="${user.name}">
        Login : <input type="text" name="login" value="${user.login}">
        Email : <input type="text" name="email" value="${user.mail}">
        <input type="submit" name="edit">
    </form>
</c:if>
<c:out value="${result}"/>
</body>
</html>
