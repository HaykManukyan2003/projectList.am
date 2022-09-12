<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

<%
    String loginFailedMessage = (String) request.getAttribute("loginFailedMessage");
%>

<%if (loginFailedMessage != null) {%>
<span style="color: red"><%=loginFailedMessage%></span>
<%}%>

<form method="post" action="/login">
    <input type="email" name="email" placeholder="email">
    <input type="password" name="password" placeholder="password">
    <input type="submit" value="login">
</form>

</body>
</html>
