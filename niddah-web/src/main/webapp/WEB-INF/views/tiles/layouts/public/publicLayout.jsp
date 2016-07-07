<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><tiles:getAsString name="title" /></title>
       
        <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
    </head>

    <body>
        <div class="header">
            <div>
                <a href="index.html" id="logo"><img src="../static/img/logo.gif" alt="logo"/></a>
                <div class="navigation">
                    <ul class="first">
                        <li class="first"><a href="jewelry.html">ON SALE</a></li>
                        <li><a href="accessories.html">BEST SELLERS</a></li>
                        <li><a href="blog.html">THE BLOG</a></li>
                    </ul>
                    <ul>
                        <li><a href="about.html">About us</a></li>
                        <li><a href="shoes.html">My Collection</a></li>
                        <li><a href="#">Login</a></li>
                    </ul>
                </div>
                <form action="" class="search">
                    <input type="text" value="search" onblur="this.value = !this.value ? 'search' : this.value;" onfocus="this.select()" onclick="this.value = '';"/>
                    <input type="submit" id="submit" value=""/>
                </form>
            </div>
            <div id="navigation">
                <ul>
                    <li class="selected"><a href="index.html">Home</a></li>
                    <li><a href="new_arrival.html">New Arrival</a></li>
                    <li><a href="apparel.html">Apparel</a></li>
                    <li><a href="beauty_care.html">Beauty Care</a></li>
                    <li><a href="shoes.html">Shoes</a></li>
                    <li><a href="accessories.html">Accessories</a></li>
                    <li><a href="jewelry.html">Jewelry</a></li>
                </ul>
            </div>
        </div> 

        <section id="site-content">
            <div class="body">
                <tiles:insertAttribute name="body" />
            </div>
        </section>

        <footer id="footer">
           <div class="footer">
			<p>&#169; 2011 Herdesigns. All Rights Reserved.</p>
		</div>
        </footer>
    </body>
</html>
