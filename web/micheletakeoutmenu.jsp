<%-- 
    Document   : micheletakeoutmenu
    Created on : Feb 14, 2014, 12:17:36 PM
    Author     : Michele
--%>

<%@page import="java.util.List"%>
<%@page import="model.service.MenuItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
List <MenuItem> menu = (List<MenuItem>) request.getAttribute("menu");
%>
<html>
    <head>
        <link rel="stylesheet" href="takeoutcss.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Place Order</title>
    </head>
    <body class="body">
        <div id="container" class="container">
            <header class="header"></header>
            <div id="menuContent" class="menuContent">
         <h1>Select the quantity of each item</h1>
        <table id="menuTable" class="menuTable">
                <tr>
                    <th>Quantity</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Subtotal</th>
                </tr>
<% 
                
for (MenuItem item: menu){
out.println("<tr><td><input name=\"quantity\" value = >"+item.getMenuID()+"</td>"+"<td>"+item.getMenuDescription()+"</td><td>"+item.getPrice()+"</td></tr>");
}
                 
%> 
      
            </table>
<form method="post" action="">
    <input type="submit" value="Submit Order">
</form>
<footer id="footer" class="footer">
            <span>Follow us for information about upcoming specials</span><br><br>
            <img src="twitter.jpg" alt="" width="100" >
            <img src="facebook.jpg" alt="" width="100"><br>
            <a href="adminmainmenu.jsp">Administrator Page</a>
        </footer>
            </div>
        </div>
    </body>
</html>
