<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>

<%
    String emailExistsErrorMessage = (String) request.getAttribute("emailExistsErrorMessage");
%>

<%if (emailExistsErrorMessage != null) {%>
<span style="color: red"><%=emailExistsErrorMessage%></span>
<%}%>

<form method="post" action="/register">
    <input type="text" name="name" placeholder="name">
    <input type="text" name="surname" placeholder="surname">
    <input type="email" name="email" placeholder="email">
    <input type="password" name="password" placeholder="password">
    <input type="submit" value="register">
</form>

</body>
</html>
