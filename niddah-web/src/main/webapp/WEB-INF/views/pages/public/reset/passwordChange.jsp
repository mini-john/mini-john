
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="alert alert-success">
    <h3 class="ghj" style="text-align: center">Changement de mot de passe</h3>
    <p>Votre changement de mot de passe est maintenant termin&eacute;e, rendez vous sur la page de connexion. </p>
    <div class="banner-bottom">
        <div class="banner-bottom-grid">
             <div class="nbs-flexisel-item">
                <a href="<c:url value="/public/signup.do"/>" style="display:inline; padding-top: 9px;"  class="hvr-bounce-to-bottom sint">Connexion </a>
            </div>
        </div>
    </div>
</div>