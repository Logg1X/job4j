<%--
  Created by IntelliJ IDEA.
  User: p.toporov
  Date: 13.12.2018
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>status</title>
</head>
<body>
<%=request.getAttribute("result")%>
<form action="<%=request.getContextPath()%>/usersTable" method="get">
<input type="submit" value="<--Home.">
</form>

<%--<%response.sendRedirect(request.getContextPath()+"/WEB-INF/UserTable.jsp");%>--%>
</body>
</html>
