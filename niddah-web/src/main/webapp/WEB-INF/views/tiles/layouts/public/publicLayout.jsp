<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

    <head>
        <link rel="icon" type="image/png" href="<c:url value="/static/public/images/favicon.png"/>"/> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Quickly Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
            function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- //for-mobile-apps -->
         <link href="<c:url value="/static/library/Jquery/jquery-ui/jquery-ui.css"/>" rel="stylesheet" type="text/css" media="all" />
        <link href="<c:url value="/static/public/css/style.css"/>" rel="stylesheet" type="text/css" media="all" />
        <link href="<c:url value="/static/public/css/niddah.css"/>" rel="stylesheet" type="text/css" media="all" />
        <link href="<c:url value="/static/library/bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css" media="all" />

        <title>JKalVered - La puret&eacute; en toute simplicit&eacute;</title>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
        <link href="https://fonts.googleapis.com/css?family=Indie+Flower" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Suez+One" rel="stylesheet">
        <!-- js -->
        <script src="<c:url value="/static/library/Jquery/jquery.min.js"/>"></script>
          <script src="<c:url value="/static/library/Jquery/jquery-ui/jquery-ui.js"/>"></script>
        <!-- //js -->
        <!-- start-smoth-scrolling -->
<!--        <script type="text/javascript" src="<c:url value="/static/public/js/move-top.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/static/public/s/jquery.flexisel.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/static/public/js/easing.js"/>"></script>-->
        <script type="text/javascript">
            jQuery(document).ready(function ($) {
                $(".scroll").click(function (event) {
                    event.preventDefault();
                    $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
                });
            });
        </script>

    </head>

    <body >
        <!-- banner-body -->
        <div class="banner-body">
            <div class="container">
                <!-- header -->
                <div class="header">
                    <div class="header-nav">
                        <nav class="navbar navbar-default">
                            <!-- Brand and toggle get grouped for better mobile display -->
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <a class="navbar-brand" href="index.do"><span>j</span>KalVered </a>

                            </div>

                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse nav-wil" id="bs-example-navbar-collapse-1">
                                <ul class="nav navbar-nav">
                                    <li id="linkacceuil" class="hvr-bounce-to-bottom"><a href="<c:url value="/public/index.do"/>">Acceuil</a></li>
                                    <li class="hvr-bounce-to-bottom"><a href="Portfolio.html">Pr&eacute;sentation</a></li>
                                    <li class="hvr-bounce-to-bottom"><a href="<c:url value="/public/blog/index.do"/>">Blog</a></li>
                                    <li class="hvr-bounce-to-bottom"><a href="Portfolio.html">Visite Guid&eacute;e</a></li>
                                    <li class="hvr-bounce-to-bottom"><a href="contact.html">Qui sommes nous ?</a></li>
                                </ul>
                                <div class="sign-in">
                                    <ul>
                                        <li><a href="<c:url value="/public/signin.do"/>">Inscription </a>/</li>
                                        <li><a href="<c:url value="/public/signup.do"/>">Connexion</a></li>
                                    </ul>
                                </div>
                            </div><!-- /.navbar-collapse -->
                            <p style="margin-top:5px;font-family: 'Indie Flower', cursive; font-size:20px;">
                                La puret&eacute; en simplicit&eacute;
                            </p>
                        </nav>
                    </div>

                    <!--                     search-scripts 
                                        <script src="js/classie.js"></script>
                                        <script src="js/uisearch.js"></script>
                                        <script>
                                new UISearch(document.getElementById('sb-search'));
                                        </script>
                                         //search-scripts -->
                </div>
                <!-- //header -->
                <div class="header-bottom">
                    
                   
                  




                    <tiles:insertAttribute name="body" />
                    <script type="text/javascript" src="<c:url value="/static/public/js/jquery.flexisel.js"/>"></script>
                </div>
            </div>
        </div>
        <!-- footer -->
        <div class="footer">
            <div class="container">
                <div class="footer-grids">
                    <div class="col-md-3 footer-grid">
                        <h3>L'auteur</h3>
                        <div class="footer-grd-left">
                            <img src="<c:url value="/static/public/images/18.jpg"/>" class="img-responsive" alt=" " />
                        </div>
                        <div class="footer-grd-left">
                            <p>Jonathan Boccara<br/> Avreh au collel de la <a href="http://www.cejnice.com" target="_blank">Yechivat Torat Haim</a> de Nice,
                                il passe son temps disponible &agrave; d&eacute;velopper l'outil jKalVered</p>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="col-md-3 footer-grid">
                        <h3></h3>
                        <ul>
                            <li><a href="#">Credits</a></li>
                            <li><a href="#">Contact</a></li>

                            <li><a href="#">Remerciements</a></li> 
                            <li><a href="#">Plan du site</a></li>

                        </ul>
                    </div>




                    <div class="clearfix"> </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-bottom">
        <div class="container">
            <p>© 2017 jkalVered. Tous droits rerserves| Template par <a href="http://w3layouts.com/"> W3layouts</a></p>
        </div>
    </div>
    <!-- //footer -->
    <!-- for bootstrap working -->
    <script src="<c:url value="/static/library/bootstrap/js/bootstrap.min.js"/>"></script>
    <!-- //for bootstrap working -->
    <script type="text/javascript">
   
</script>
</body>
</html>