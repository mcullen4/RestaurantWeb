<%-- 
    Document   : micheletakeouthome
    Created on : Feb 14, 2014, 9:40:12 AM
    Author     : Michele
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="takeoutcss.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Michele's Take Out</title>
    </head>
    <body class="body">
        <div id="container" class="container">
            <header class="header"></header>
        <div id="menuBar" class="menuBar">
            <ul id="menuBarItems" class="menuBarItems">
                <li class="menuBarListItems"><a href="micheletakeouthome.jsp">Home</a></li>
                <li class="menuBarListItems"><a href="micheletakeoutaboutus.jsp">About Us</a></li>
                <li class="menuBarListItems"><a href="micheletakeoutlocations.jsp">Locations</a></li>
                <li class="menuBarListItems"><a href="micheletakeoutcontact.jsp">Contact Us</a></li>
            </ul><br>

            <h2>Place Your Order</h2>
            <form method="post" action="menuControl">
            <input type="submit" value="Order Now" name="Order Now">
            </form>
        </div><!--End of menu bar div-->
        <div id="content" class="content"><img src="takeout-counter.jpg" alt="" width="695">
            
        
        </div>
        <footer id="footer" class="footer">
            <span>Follow us for information about upcoming specials</span><br><br>
            <img src="twitter.jpg" alt="" width="100" >
            <img src="facebook.jpg" alt="" width="100"><br>
            <a href="adminmainmenu.jsp">Administrator Page</a>
        </footer>
    </div><!--End of container-->
    </body>
</html>
