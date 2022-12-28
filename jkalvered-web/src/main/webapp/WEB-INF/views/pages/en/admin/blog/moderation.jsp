<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTagLib.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<h1>Gestion des commentaires </h1>
<div class="row"><div class="col-md-12">
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
                    <th>Email</th>
                    <th>Autheur</th>
                    <th>Date</th>
                    <th>Commentaire</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${comments}" var="comment" varStatus="itr">
                    <tr>
                        <th scope="row">${comment.id}</th>
                        <td>${comment.email}</td>
                        <td>${comment.autheur}</td>
                        <td><fmt:formatDate type = "date" value = "${comment.dateComments}" /></td>
                        <td>${comment.commentaire}</td>
                        <td><a href="<c:url value="/admin/blog/deleteComment.do?id=${comment.id}"/>" class="btn btn-primary delete" data-title="Suppression de le commentaire de ${comment.autheur}">Supprimer</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>



        <tag:paginate max="5" offset="${offset}" count="${count}"
                      uri="moderation.do?id=${comments.get(0).post.id}" next="&raquo;" previous="&laquo;" />
    </div>
</div>
    <script type="text/javascript">
    $('a.delete').confirm({
        content: 'Confirmer la suppression du commentaire',
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