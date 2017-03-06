
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript">
    $(function () {
        $('.active').removeClass('active');
    });
</script>
<div class="login">
    <div class="login-grids">
        <div class="col-md-6 log">
            <h3>Vous avez d&eacute;j&agrave; un compte ?</h3>
            
            <form>
                <h5>Identifiant:</h5>	
                <input type="text" value="">
                <h5>Mot de passe : </h5>
                <input type="password" value="">					
                <input type="submit" value="Connexion">

            </form>
            <a href="#">Vous avez oubli&eacute; votre mot de passe ?</a>
        </div>
        <div class="col-md-6 login-right">
            <h3>Nouveau sur jKalVered ?</h3>
            <p>L'inscription est simple est sans engagement. Essayer jKalVered c'est l'adopt&eacute;</p>
            <a href="<c:url value="/public/signin.do"/>">Cr&eacute;z votre compte </a>
        </div>
        <div class="clearfix"></div>