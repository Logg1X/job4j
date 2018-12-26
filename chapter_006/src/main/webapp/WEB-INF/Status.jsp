<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>status</title>
</head>
<body>
<c:out value="${result}"/>
<form action="${pageContext.servletContext.contextPath}/listUsr" method="get">
    <input type="submit" value="<--Home.">
</form>
</body>
</html>
