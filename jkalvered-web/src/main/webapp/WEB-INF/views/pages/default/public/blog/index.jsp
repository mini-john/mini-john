<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTagLib.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<div class="banner-bottom">
    <div class="container-fluid">

        <div class="pagenatin">


            <tag:paginate max="5"  division ="5" offset="${offset}" count="${count}"
                          uri="index.do" next="&raquo;" previous="&laquo;" />
        </div>
        <c:forEach items="${posts}" var="post" varStatus="itr">
            <div class="blog">
                <div class="blog-left">
                    <div class="blog-left-grid">
                        <div class="blog-left-grid-left">
                            <h3><a href="<c:url value="/public/blog/view.do?id=${post.id}"/>">${post.title}</a></h3>
                            <p> autheur <span>${post.author.login}</span> | <fmt:formatDate type = "date" value = "${post.date}" /><!-- | <span>Sint</span>--></p>

                        </div>
                        <div class="blog-left-grid-right">
                            <a href="#" class="hvr-bounce-to-bottom non">${post.commentss.size()} commentaire(s)</a>
                        </div>
                        <div class="clearfix"> </div>
                        <p class="para"> ${post.body}</p>
                        <div class="rd-mre">
                            <a href="<c:url value="/public/blog/view.do?id=${post.id}"/>" class="hvr-bounce-to-bottom quod">Lire plus</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>
