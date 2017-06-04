<%-- 
    Document   : view
    Created on : 2 avr. 2017, 18:52:26
    Author     : Boccara Jonathan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div class="banner-bottom">
    <div class="container-fluid">
        <div class="blog">
            <div class="blog-left">
                <div class="blog-left-grid">
                    <div class="blog-left-grid-left">
                        <h3>${post.title}</h3>
                        <p> <!--by <span>Charlie</span> |--> <fmt:formatDate type = "date" value = "${post.date}" /><!-- | <span>Sint</span>--></p>
                    </div>
                    <div class="blog-left-grid-right">
                        <!--<a href="#" class="hvr-bounce-to-bottom non">20 Comments</a>-->
                    </div>
                    <div class="clearfix"> </div>
                    <p class="para"> ${post.body}</p>
                   
                </div>
            </div>
        </div>
    </div>
</div>