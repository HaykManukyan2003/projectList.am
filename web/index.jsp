<%@ page import="model.User" %>
<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>homepage</title>
</head>
<body>

<%
    User user = (User) session.getAttribute("user");
    String itemSuccessfullyAdded = (String) request.getAttribute("itemSuccessfullyAdded");
    List<Item> itemList = (List<Item>) request.getAttribute("itemList");
%>

<%if (user != null) {%>
<h2> Welcome, <%=user.getName()%>
</h2>

<div style="margin-left: 600px"><a href="/logout">Logout</a></div>

<div style="width: 400px; display: flex; justify-content: space-between">
    <%if (itemSuccessfullyAdded != null) {%>
    <span style="color: red"><%=itemSuccessfullyAdded%></span>
    <%}%>
    <div><a href="/item/add">Add an Item</a></div>
    <div><a href="/myItems/show?userId=<%=user.getId()%>">My Listed Items</a></div>
</div>
<br>

<%} else {%>

<div style="width: 110px; display: flex; justify-content: space-between; margin-left: 600px">
    <div><a href="/login">Login</a></div>
    <div><a href="/register">Register</a></div>
</div>

<%}%>

<div style="display: flex; justify-content: space-between; width: 400px">
    <div><a href="/items/show?categoryId=0">Homepage</a></div>
    <div><a href="/items/show?categoryId=1">Cars</a></div>
    <div><a href="/items/show?categoryId=2">Apartments</a></div>
    <div><a href="/items/show?categoryId=3">Commerce</a></div>
    <div><a href="/items/show?categoryId=4">Furniture</a></div>
</div>

<%if (itemList != null) {%>
<%for (Item item : itemList) {%>
<figure>
    <img src="/itemImage?itemImageParameter=<%=item.getPictureUrl()%>" width="300px">
    <figcaption>
        <div>title:<%=item.getTitle()%>
        </div>
        <div>category:<%=item.getCategory().getName()%>
        </div>
        <div>price:<%=item.getPrice()%>
        </div>
        <div>owner:<%=item.getUserId().getName() + " " + item.getUserId().getSurname()%>
        </div>
    </figcaption>
</figure>
<%}%>
<%}%>


</body>
</html>
