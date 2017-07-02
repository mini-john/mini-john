<%-- 
    Document   : adminLayout
    Created on : 9 avr. 2017, 18:11:42
    Author     : Boccara Jonathan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>jKalvered</title>
        <!-- BOOTSTRAP STYLES-->
        <link href="<c:url value="/static/library/bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css" media="all" />
        <link href="<c:url value="/static/library/Jquery/jqueryconfirm/jquery-confirm.min.css"/>" rel="stylesheet" type="text/css" media="all" />
        <!-- FONTAWESOME STYLES-->
        <link href="<c:url value='/static/admin/css/font-awesome.css'/>" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
        <link href="<c:url value='/static/admin/css/custom.css'/>" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
        <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
        <!-- JQUERY SCRIPTS -->
        <script src="<c:url value="/static/library/Jquery/jquery.min.js"/>"></script>
        <!-- BOOTSTRAP SCRIPTS -->
        <script src="<c:url value="/static/library/bootstrap/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/static/library/Jquery/jqueryconfirm/jquery-confirm.min.js"/>"></script>

    </head>
    <body>
        <h1>Hello World!</h1>
        <div id="wrapper">
            <div class="navbar navbar-inverse navbar-fixed-top">
                <div class="adjust-nav">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="<c:url value='/admin/index.do'/>">
                            <img src="<c:url value='/static/admin/img/logo.png'/>" style="width:250px; height:50px"/>
                        </a>

                    </div>

                    <span class="logout-spn" >
                        <a href="<c:url value='/logout'/>" style="color:#fff;">LOGOUT</a>  

                    </span>
                </div>
            </div>
            <nav class="navbar-default navbar-side" role="navigation">
                <div class="sidebar-collapse">
                    <ul class="nav" id="main-menu">



                        <li id="linkacceuil" class="">
                            <a href="<c:url value='/admin/index.do'/>" ><i class="fa fa-desktop "></i>Tableau de bord</a>
                        </li>




                    </ul>
                </div>

            </nav>
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div id="page-inner">
                    <tiles:insertAttribute name="body" />
                </div>
            </div>
        </div>
        <div class="footer">


            <div class="row">
                <div class="col-lg-12" >
                    &copy;  2017 jKalVered.com | Design by: <a href="http://binarytheme.com" style="color:#fff;" target="_blank">www.binarytheme.com</a>
                </div>
            </div>
        </div>
        <!-- /. WRAPPER  -->
        <!-- CUSTOM SCRIPTS -->
        <script src="<c:url value='/static/admin/js/custom.js'/>"></script>
    </body>
</html>
