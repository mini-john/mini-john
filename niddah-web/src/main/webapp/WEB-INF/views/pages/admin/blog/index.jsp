<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTagLib.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<h1>Gestion du blog </h1>
<div class="row">
    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">&nbsp;
    </div>
    <div class="col-lg-6 col-md-6">

        <a href="<c:url value="/admin/blog/add.do"/>" class="btn btn-primary">Ajouter un article</a>
    </div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>#</th>
                <th>Titre</th>
                <th>Autheur</th>
                <th>Nombre commentaire</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${posts}" var="post" varStatus="itr">
                <tr>
                    <th scope="row">${post.id}</th>
                     <td>${post.title}</td>
                    <td>${post.author.login}</td>
                    <td>&nbsp;</td>
                    <td><a href="<c:url value="/admin/blog/update.do?idPost=${post.id}"/>" class="btn btn-primary">Modifier</a><a href="<c:url value="/admin/blog/delete.do?idPost=${post.id}"/>" style="margin-left: 2% " class="btn btn-primary">Supprimer</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    

    <tag:paginate max="1" offset="${offset}" count="${count}"
                  uri="index.do" next="&raquo;" previous="&laquo;" />
</div>
