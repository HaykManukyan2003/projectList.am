<%@ page import="manager.CategoryManager" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Item</title>
</head>
<body>

<%
    CategoryManager categoryManager = new CategoryManager();
    List<Category> categoryList = categoryManager.getCategories();
    HttpSession currentSession = request.getSession();
    User user = (User) currentSession.getAttribute("user");
%>

<form method="post" action="/item/add" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="title">
    <input type="number" name="price" placeholder="price">
    <select name="categoryId">
        <% for (Category category : categoryList) {%>
        <option value="<%=category.getId()%>"><%=category.getName()%>
        </option>
        <%}%>
    </select>
    <input type="file" name="pictureUrl">
    <input type="hidden" name="userId" value="<%=user.getId()%>">
    <input type="submit" value="post item">
</form>

</body>
</html>
