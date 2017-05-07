
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<h1>Gestion du blog </h1>
<div class="row">
    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">&nbsp;
    </div>
    <div class="col-lg-6 col-md-6">

        <a href="<c:url value="/admin/blog/add.do"/>" class="btn btn-primary">Ajouter un article</a>
    </div>
</div>
