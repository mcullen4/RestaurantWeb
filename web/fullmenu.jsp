<%-- 
    Document   : fullmenu
    Created on : Feb 11, 2014, 9:31:22 AM
    Author     : Michele
--%>

<%@page import="model.service.MenuItem"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
List <MenuItem> menu = (List<MenuItem>) request.getAttribute("menu");
%>
<html>
    <head>
         <link rel="stylesheet" href="style.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="body">
        <div id="container" class="container">
            <div id="header" class="header">
                <ol id="menuBar" class="menuBar">
                    <li><a href="home.jsp" class="menuItems">Home</a></li>
                    <li><a href="about.jsp" class="menuItems">About Us</a></li>
                    <li><a href="menus.jsp" class="menuItems">Menus</a></li>
                    <li><a href="contact.jsp" class="menuItems">Contact Us</a></li>
                </ol>    
            </div><!--End of header-->
            
                
                <h1>Menu Items</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
            </tr></table>
           
<% 
for (MenuItem item: menu){
out.println("<p>" + item.getMenuID() + "</p>");
                 
   %>                
                



        </div>    <!--End of Content-->
            <div id="footer" class="footer"></div><!--End of footer-->
        <!--End of Container-->
    </body>
</html>
