

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="row">
    <div class="col-lg-12">
        <h2>Tableau d'administration</h2>   
    </div>
</div>              
<!-- /. ROW  -->
<hr />
<div class="row">
    <div class="col-lg-12 ">
        <div class="alert alert-info">
            <strong>Welcome ${account.personne.nom} ${account.personne.prenom} ! </strong>
        </div>

    </div>
</div>
<div class="row text-center pad-top">
    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
        <div class="div-square">
            <a href="<c:url value="./blog/index.do"/>" >
                <i class="fa fa-envelope-o fa-5x"></i>
                <h4>Gestion du blog</h4>
            </a>
        </div>

    </div>
    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
        <div class="div-square">
           <a href="<c:url value="./utilisateur/index.do"/>" >
                <i class="fa fa-users fa-5x"></i>
                <h4>Gestion des utilisateurs</h4>
            </a>
        </div>
    </div>
                 <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
        <div class="div-square">
           <a href="<c:url value="./sessions/index.do"/>" >
                <i class="fa fa-users fa-5x"></i>
                <h4>Gestion des Sessions</h4>
            </a>
        </div>
    </div>
</div>
<script>
// You can also use "$(window).load(function() {"
    $(function () {
        $('#linkacceuil').addClass('active-link');
    });
</script>