<%-- 
    Document   : menudisplay
    Created on : Feb 12, 2014, 1:25:52 PM
    Author     : Michele
--%>

<%@page import="model.service.MenuItem"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
         <link rel="stylesheet" href="style.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
    </head>
    <body>
       <div id="container" class="container">
            <div id="header" class="header">
                <ol id="menuBar" class="menuBar">
                    <li><a href="home.jsp" class="menuItems">Home</a></li>
                    <li><a href="about.jsp" class="menuItems">About Us</a></li>
                    <li><a href="menus.jsp" class="menuItems">Menus</a></li>
                    <li><a href="contact.jsp" class="menuItems">Contact Us</a></li>
                </ol> 
            </div><!--End of header-->
            <div id="content" class="content">
                <p></p>
            <table id="menuTable" class="menuTable" width="500px">
                <tr>
                    <th>Quantity</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Subtotal</th>
                </tr>
<% 
List <MenuItem> menu = (List<MenuItem>) request.getAttribute("menu");                
for (MenuItem item: menu){
out.println("<tr><td><input></td>"+"<td>"+item.getMenuDescription()+"</td><td>"+item.getPrice()+"</td></tr>");
}
                 
%> 
      
            </table>
            </div>
 <div id="footer" class="footer"></div><!--End of footer-->
       </div><!--End of container-->
    </body>
</html>
