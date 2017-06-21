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
            <li><a href="#"><small class="posts"> </small><span>View posts</span></a></li>
            <li><a href="#"><small class="link"> </small><span>permalink</span></a></li>
        </ul>
    </div>
    <c:if test="${post.commentss.size()>0}">
        <div class="comment-grid-top">
            <h3>Commentaires</h3>
            <c:forEach items="${post.commentss}" var="comment" varStatus="itr">

                <div class="comments-top-top">
                    <div class="top-comment-left">
                        <a href="#"><img class="img-responsive" src="<c:url value="/static/public/images/co.png"/>" alt=""></a>
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
    </c:if>
    <div class="artical-commentbox">
        <h3>Laisser un commentaire</h3>
        <div class="table-form">
            <form:form modelAttribute="comment" action="./addComment.do">
                <form:hidden path="post.id" value="${post.id}"/>
                <form:input type="text" path="autheur" class="textbox" value="Name" onfocus="this.value = '';" onblur="if (this.value == '') {
                            this.value = 'Name';
                            }"/>
                <form:input type="text" path="email" class="textbox" value="Email" onfocus="this.value = '';" onblur="if (this.value == '') {
                            this.value = 'Email';
                            }"/>

                <form:textarea path="commentaire" value="Message:" onfocus="this.value = '';" onblur="if (this.value == '') {
                               this.value = 'Message';
                               }"/>	
                <input type="submit" value="Send">
            </form:form>
        </div>
    </div>	
</div>
