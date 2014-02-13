<%-- 
    Document   : menus
    Created on : Feb 11, 2014, 9:05:00 AM
    Author     : Michele
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Michele's Menus</title>
    </head>
    <body class="body">
        <div id="container" class="container">
            <div id="header" class="header">
            <ol id="menuBar" class="menuBar">
                    <li><a href="home.jsp" class="menuItems" >Home</a></li>
                    <li><a href="about.jsp" class="menuItems">About Us</a></li>
                    <li><a href="menus.jsp" class="menuItems" >Menus</a></li>
                    <li><a href="contact.jsp" class="menuItems">Contact Us</a></li>
                </ol>       
            </div><!--End of header-->
                <div id="content" class="content">
                    
                    <form >
                    <select name="menunav" onChange='top.location.href=this.form.menunav.options[this.form.menunav.selectedIndex].value'>
                        <option selected>Select Menu</option>
                        <option value="menuControl?action=dinner">Dinner</option>
                        <option value="menuControl?action=happyhour">Happy Hour</option>
                        <option value="menuControl?action=brunch">Sunday Brunch</option>
                    </select>
                    </form>
                    
                    
                </div><!--End of Content-->
                <div id="footer" class="footer"></div><!--End of footer-->
            </div><!--End of Container-->
    </body>
</html>
