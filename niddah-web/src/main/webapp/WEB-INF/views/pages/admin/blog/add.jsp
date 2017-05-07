
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
    <div class="col-lg col-md">
        <h3>Ajout d'un article pour la blog</h3>
        <form:form action="./add.do" modelAttribute="post" method="post" >
            <div class="panel panel-primary">
                <div class="panel-heading">
                    Titre : <form:input path="title"  type="text" placeholder=" " cssClass="form-control"  />
                    <form:errors path="title" class="alert alert-danger"  element="div"></form:errors>
                    </div>
                    <div class="panel-body">
                        Texte  <form:textarea path="body"  type="text" placeholder=" " cssClass="form-control"  />
                    <form:errors path="body" class="alert alert-danger"  element="div"></form:errors>  </div>
                    <div class="panel-footer">
                        <input type="submit" value="Envoyer">
                    </div>
                </div>
        </form:form>
    </div>
</div>
<script src="<c:url value='/static/tools/tinymce/js/tinymce/tinymce.min.js'/>"></script>
<script>tinymce.init({selector: 'textarea',language: 'fr_FR'});</script>