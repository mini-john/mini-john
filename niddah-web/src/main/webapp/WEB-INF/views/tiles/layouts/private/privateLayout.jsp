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
        <link rel="stylesheet" href="<c:url value='/static/css/icon-font.min.css'/>" type='text/css' />
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
        <h1>Hello World! template priver</h1>
        <tiles:insertAttribute name="body" />
        <script src="<c:url value="/static/library/bootstrap/js/bootstrap.min.js"/>"></script>
    </body>
</html>
