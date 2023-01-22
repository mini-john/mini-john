<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
          <link rel="icon" type="image/png" href="<c:url value="/static/public/images/favicon.png"/>"/> 
      
        <title>jKalVered : La pureté en simplicité</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="<c:url value="/static/private/fr/css/main.css"/>" />
        <link rel="stylesheet" href="<c:url value="/static/private/fr/css/perso.css"/>" />
        <script src="<c:url value="/static/library/Jquery/jquery.min.js"/>"></script>
        <link href="<c:url value="/static/library/bootstrap5/css/bootstrap.css"/>" rel="stylesheet" type="text/css" media="all" />

    </head> 
    <body class="is-preload">
        <!-- Header -->
        <section id="header">
            <header>
                <span class="image avatar"><img src="<c:url value="/static/private/fr/images/avatar.jpg"/>" alt="" /></span>
                <h1 id="logo"><a href="#"><security:authentication property="principal.personne.nom" /> <security:authentication property="principal.personne.prenom" /></a></h1>
                <p>I got reprogrammed by a rogue AI<br />
                    and now I'm totally cray</p>
            </header>
            <nav id="nav">
                <ul>
                    <li><a id='calendrier' href="<c:url value="/private/index.do"/>" >Mon Calendrier</a></li>
                    <li><a id='parametre'  href="<c:url value="/private/moncompte/index.do" />">Mon Compte</a></li>

                    <li><a href="<c:url value="/logout"/>">D&eacute;connexion</a></li>
                </ul>
            </nav>
<!--            <footer>
                <ul class="icons">
                    <li><a href="#" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
                    <li><a href="#" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
                    <li><a href="#" class="icon brands fa-instagram"><span class="label">Instagram</span></a></li>
                    <li><a href="#" class="icon brands fa-github"><span class="label">Github</span></a></li>
                    <li><a href="#" class="icon solid fa-envelope"><span class="label">Email</span></a></li>
                </ul>
            </footer>-->
        </section>
        <div id="wrapper">
            <div id="main">
                <section id="one">
                    <div class="image main" data-position="center">
                        <img src="<c:url value="/static/private/fr/images/banner.jpg"/>" alt="" />
                    </div>
                    
                </section>
                <tiles:insertAttribute name="body" />
            </div>

            <section id="footer">
                <div class="container">
                    <ul class="copyright">
                        <li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
                    </ul>
                </div>
            </section>
        </div>

        <!-- Scripts -->

        <script src="<c:url value="/static/private/fr/js/jquery.scrolly.min.js"/>"></script>
        <script src="<c:url value="/static/private/fr/js/jquery.scrollex.min.js"/>"></script>
        <script src="<c:url value="/static/private/fr/js/browser.min.js"/>"></script>
        <script src="<c:url value="/static/private/fr/js/breakpoints.min.js"/>"></script> 
        <script src="<c:url value="/static/private/fr/js/util.js"/>"></script>
        <script src="<c:url value="/static/private/fr/js/main.js"/>"></script>
        <script src="<c:url value="/static/library/bootstrap5/js/bootstrap.min.js"/>"></script>
    </body>
</html>
