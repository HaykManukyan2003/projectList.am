<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Items</title>
</head>
<body>

<%
    List<Item> itemList = (List<Item>) request.getAttribute("itemList");
%>

<%for (Item item : itemList) {%>
<figure>
    <img src="/itemImage?itemImageParameter=<%=item.getPictureUrl()%>" width="300px">
    <figcaption>
        <div>title: <%=item.getTitle()%>
        </div>
        <div>category: <%=item.getCategory().getName()%>
        </div>
        <div>price: <%=item.getPrice()%>
        </div>
        <div>owner: <%=item.getUserId().getName() + " " + item.getUserId().getSurname()%>
        </div>
        <a href="/item/remove?itemId=<%=item.getId()%>">remove item</a>
    </figcaption>
</figure>
<%}%>

</body>
</html>
