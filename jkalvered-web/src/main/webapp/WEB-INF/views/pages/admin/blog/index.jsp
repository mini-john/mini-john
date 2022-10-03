<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTagLib.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<h1>Gestion du blog </h1>
<div class="row">
    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">&nbsp;
    </div>
    <div class="col-lg-6 col-md-6">

        <a href="<c:url value="/admin/blog/add.do"/>" class="btn btn-primary">Ajouter un article</a>
    </div>
    <div class="col-md-12">
        <c:if test="${SUCCESS_MESSAGE!= null}">
            <div class="alert alert-success" role="alert">
                <h3>Message</h3>
                <p>${SUCCESS_MESSAGE}</p>
            </div>
        </c:if>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Titre</th>
                    <th>Autheur</th>
                    <th>Date</th>
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
                        <td><fmt:formatDate type = "date" value = "${post.date}" /></td>
                        <td>${post.commentss.size()} <a href="<c:url value="moderation.do?id=${post.id}"/>" class="btn btn-primary">Mod&eacute;ration  </a></td>
                        <td><a href="<c:url value="/admin/blog/update.do?id=${post.id}"/>" class="btn btn-primary">Modifier</a><a href="<c:url value="/admin/blog/delete.do?id=${post.id}"/>" style="margin-left: 2% "
                                                                                                                                  class="btn btn-primary delete" data-title="Suppression de l'article ${post.title}">Supprimer</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>



        <tag:paginate max="1" offset="${offset}" count="${count}" division="5"
                      uri="index.do" next="&raquo;" previous="&laquo;" />
    </div>
</div>
<script type="text/javascript">
    $('a.delete').confirm({
        content: 'Confirmer la suppression de l\'article',
        buttons: {
            ok: function () {
                location.href = this.$target.attr('href');
            },
            annuler: function () {
                //close
            }
        }
    });
</script>