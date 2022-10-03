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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Indie+Flower" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Suez+One" rel="stylesheet">
<link href="<c:url value="/static/public/css/style.css"/>" rel="stylesheet" type="text/css" media="all" />
<link href="<c:url value="/static/public/css/niddah.css"/>" rel="stylesheet" type="text/css" media="all" />
<link href="<c:url value="/static/library/bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css" media="all" />

<div class="single-page-artical">
    <div class="artical-content">
        <h3>${post.title}</h3>


        <p class="para"> ${post.body}</p>

    </div>
    <div class="artical-links">
        <ul>
            <li><small> </small><span><fmt:formatDate type = "date" value = "${post.date}" /></span></li>
            <li><a href="#"><small class="admin"> </small><span>${post.author.login}</span></a></li>
            <li><a href="#"><small class="no"> </small><span>${post.commentss.size()} commentaires</span></a></li>
            <!--            <li><a href="#"><small class="posts"> </small><span>View posts</span></a></li>
                        <li><a href="#"><small class="link"> </small><span>permalink</span></a></li>-->
        </ul>
    </div>
    <c:if test="${post.commentss.size()>0}">
        <div class="comment-grid-top">
            <h3>Commentaires</h3>
            <c:forEach items="${post.commentss}" var="comment" varStatus="itr">

                <div class="comments-top-top">
                    <div class="top-comment-left">
                        <img class="img-responsive" src="<c:url value="/static/public/images/co.png"/>" alt="">
                    </div>
                    <div class="top-comment-right">
                        <ul>
                            <li><span class="left-at">${comment.autheur}</span></li>
                            <li><span class="right-at"><fmt:formatDate type = "both" 
                                            dateStyle = "short" timeStyle = "short" value = "${comment.dateComments}" /></span></li>
                        </ul>
                        <p>${comment.commentaire}</p>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </c:forEach>
        </div>
    </c:if><div class="row">
      <a href="<c:url value="index.do"/>" class="btn btn-primary">Acceuil du blog</a>
    </div>
</div>
