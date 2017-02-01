<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:importAttribute name="javascripts"/>
<tiles:importAttribute name="stylesheets"/>
<tiles:importAttribute name="javascriptsEnds"/>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><tiles:getAsString name="title" /></title>
        <link href="<c:url value="/static/css/niddah.css"/>"  rel="stylesheet"></link>
        <link href="<c:url value="/static/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/static/css/style.css"/>" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
        <link href="https://fonts.googleapis.com/css?family=Indie+Flower" rel="stylesheet">
        <script src="<c:url value="/static/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/static/js/scripts.js"/>"></script>

    </head>

    <body >
        <div class="container-fluid">

            <tiles:insertAttribute name="header" />
            <tiles:insertAttribute name="menu" />

           




            <section>

                <tiles:insertAttribute name="body" />
            </section>

            <footer id="footer">
                <tiles:insertAttribute name="footer" />
            </footer>

        </div>
    </body>
</html>