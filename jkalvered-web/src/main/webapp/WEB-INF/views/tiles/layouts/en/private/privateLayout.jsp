<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>jKalVered : La pureté en simplicité</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Gretong Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- Bootstrap Core CSS -->
        <link href="<c:url value="/static/library/bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css" media="all" />
        <!-- Custom CSS -->
        <link href="<c:url value='/static/private/css/style.css'/>" rel='stylesheet' type='text/css' />
        <!-- Graph CSS -->
        <link href="<c:url value='/static/private/css/font-awesome.css' />"rel="stylesheet"> 
        <!-- jQuery -->
        <link href='http://fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <!-- lined-icons -->
        <link rel="stylesheet" href="<c:url value='/static/private/css/icon-font.min.css'/>" type='text/css' />
        <script src="<c:url value='/static/private/js/amcharts.js'/>"></script>	
        <script src="<c:url value='/static/private/js/serial.js'/>"></script>	
        <script src="<c:url value='/static/private/js/light.js'/>"></script>	
        <!-- //lined-icons -->
        <script src="<c:url value="/static/library/Jquery/jquery.min.js"/>"></script>
        <!--pie-chart--->
        <script src="<c:url value='/static/private/js/pie-chart.js'/>" type="text/javascript"></script>
        <script type="text/javascript">

            $(document).ready(function () {
                $('#demo-pie-1').pieChart({
                    barColor: '#3bb2d0',
                    trackColor: '#eee',
                    lineCap: 'round',
                    lineWidth: 8,
                    onStep: function (from, to, percent) {
                        $(this.element).find('.pie-value').text(Math.round(percent) + '%');
                    }
                });

                $('#demo-pie-2').pieChart({
                    barColor: '#fbb03b',
                    trackColor: '#eee',
                    lineCap: 'butt',
                    lineWidth: 8,
                    onStep: function (from, to, percent) {
                        $(this.element).find('.pie-value').text(Math.round(percent) + '%');
                    }
                });

                $('#demo-pie-3').pieChart({
                    barColor: '#ed6498',
                    trackColor: '#eee',
                    lineCap: 'square',
                    lineWidth: 8,
                    onStep: function (from, to, percent) {
                        $(this.element).find('.pie-value').text(Math.round(percent) + '%');
                    }
                });


            });

        </script>
    </head> 
    <body>
        je suis anglais ddd
        
        <div class="page-container">
            <div class="left-content">
                <div class="inner-content">
                    <!-- header-starts -->
                    <div class="header-section">
                        <!-- top_bg -->
                        <div class="top_bg">

                            <div class="header_top">
                                <div class="top_right">
                                    <ul>
                                        <li><a href="contact.html">help</a></li>|
                                        <li><a href="contact.html">Contact</a></li>|
                                        <li><a href="checkout.html">Delivery information</a></li>
                                    </ul>
                                </div>
                                <div class="top_left">
                                    <h2><span></span> Call us : 032 2352 782</h2>
                                </div>
                                <div class="clearfix"> </div>
                            </div>

                        </div>
                        <div class="clearfix"></div>
                        <!-- /top_bg -->
                    </div>
                    <div class="header_bg">

                        <div class="header">
                            <div class="head-t">
                                <div class="logo">
                                    <a href="index.html"><img src="images/logo.png" class="img-responsive" alt=""> </a>
                                </div>
                                <!-- start header_right -->
                                <div class="header_right">
                                    <div class="rgt-bottom">
                                        <div class="log">
                                            <div class="login">
                                                <div id="loginContainer"><a id="loginButton" class=""><span>Login</span></a>
                                                    <div id="loginBox" style="display: none;">                
                                                        <form id="loginForm">
                                                            <fieldset id="body">
                                                                <fieldset>
                                                                    <label for="email">Email Address</label>
                                                                    <input type="text" name="email" id="email">
                                                                </fieldset>
                                                                <fieldset>
                                                                    <label for="password">Password</label>
                                                                    <input type="password" name="password" id="password">
                                                                </fieldset>
                                                                <input type="submit" id="login" value="Sign in">
                                                                <label for="checkbox"><input type="checkbox" id="checkbox"> <i>Remember me</i></label>
                                                            </fieldset>
                                                            <span><a href="#">Forgot your password?</a></span>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="reg">
                                            <a href="register.html">REGISTER</a>
                                        </div>
                                        <div class="cart box_1">
                                            <a href="checkout.html">
                                                <h3> <span class="simpleCart_total">$0.00</span> (<span id="simpleCart_quantity" class="simpleCart_quantity">0</span> items)<img src="images/bag.png" alt=""></h3>
                                            </a>	
                                            <p><a href="javascript:;" class="simpleCart_empty">(empty card)</a></p>
                                            <div class="clearfix"> </div>
                                        </div>
                                        <div class="create_btn">
                                            <a href="checkout.html">CHECKOUT</a>
                                        </div>
                                        <div class="clearfix"> </div>
                                    </div>
                                    <div class="search">
                                        <form>
                                            <input type="text" value="" placeholder="search...">
                                            <input type="submit" value="">
                                        </form>
                                    </div>
                                    <div class="clearfix"> </div>
                                </div>
                                <div class="clearfix"> </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <!--/sidebar-menu-->
            <div class="sidebar-menu">
                <header class="logo1">
                    <a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a> 
                </header>
                <div style="border-top:1px ridge rgba(255, 255, 255, 0.15)"></div>
                <div class="menu">
                    <ul id="menu" >
                        <li><a href="index.html"><i class="fa fa-tachometer"></i> <span>Acceuil</span></a></li>
                        <li id="menu-academico" ><a href="#"><i class="fa fa-table"></i> <span> New Arrivals</span> <span class="fa fa-angle-right" style="float: right"></span></a>
                            <ul id="menu-academico-sub" >
                                <li id="menu-academico-avaliacoes" ><a href="shoes.html">Shoes</a></li>
                                <li id="menu-academico-avaliacoes" ><a href="products.html">Watches</a></li>
                                <li id="menu-academico-boletim" ><a href="sunglasses.html">Sunglasses</a></li>
                            </ul>
                        </li>
                        <li id="menu-academico" ><a href="sunglasses.html"><i class="fa fa-file-text-o"></i> <span>Sunglasses</span></a></li>
                        <li><a href="sweater.html"><i class="lnr lnr-pencil"></i> <span>Sweater</span></a></li>
                        <li id="menu-academico" ><a href="catalog.html"><i class="fa fa-file-text-o"></i> <span>Catalog</span></a></li>
                        <li id="menu-academico" ><a href="shoes.html"><i class="lnr lnr-book"></i> <span>Shoes</span></a></li>
                        <li><a href="bags.html"><i class="lnr lnr-envelope"></i> <span>Bags</span></a></li>
                        <li><a href="products.html"><i class="lnr lnr-chart-bars"></i> <span>Watches</span></a></li>
                        <li id="menu-academico" ><a href="#"><i class="lnr lnr-layers"></i> <span>Tabs & Calender</span> <span class="fa fa-angle-right" style="float: right"></span></a>
                            <ul id="menu-academico-sub" >
                                <li id="menu-academico-avaliacoes" ><a href="tabs.html">Tabs</a></li>
                                <li id="menu-academico-boletim" ><a href="calender.html">Calender</a></li>

                            </ul>
                        </li>
                        <li><a href="#"><i class="lnr lnr-chart-bars"></i> <span>Forms</span> <span class="fa fa-angle-right" style="float: right"></span></a>
                            <ul>
                                <li><a href="input.html"> Input</a></li>
                                <li><a href="validation.html">Validation</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="clearfix"></div>		
        </div>
        <script>
            var toggle = true;

            $(".sidebar-icon").click(function () {
                if (toggle)
                {
                    $(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
                    $("#menu span").css({"position": "absolute"});
                } else
                {
                    $(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
                    setTimeout(function () {
                        $("#menu span").css({"position": "relative"});
                    }, 400);
                }

                toggle = !toggle;
            });
        </script>
    </div>
    <h1>Hello World! template priver</h1>
    <!--//content-inner-->

    <tiles:insertAttribute name="body" />
    <script src="<c:url value="/static/library/bootstrap/js/bootstrap.min.js"/>"></script>
</body>
</html>
