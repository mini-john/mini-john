
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- register -->
<div class="sign-up-form">
    <h3>Changement de mot de passe sur le site JKalVered</h3>
    <p>Plus q'une &eacute;tape pour le changement de votre mot de passe de votre compte sur JKalVered. Merci de choisir un mot de passe.</p>
    <div class="sign-up">

        <c:if test="${not empty message}"><div class="message green">${message}</div></c:if>
                        <h5>Choix du Mot de passe pour ${personne.nom}&nbsp;${personne.prenom}</h5>

        <form:form action="./changePassword.do" modelAttribute="personne" method="post" >
            <form:hidden path="id"/>
            <div class="sign-u">
                <div class="sign-up1">
                    <h4 class="a">Mot de Passe* :</h4>
                </div>
                <div class="sign-up2">
                   <form:password path="account.password" required="required"/>
                    <form:errors path="account.password" class="alert alert-danger"  element="div"></form:errors>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <div class="sign-u">
                    <div class="sign-up1">
                        <h4 class="b">Confirmation du mot de passe* :</h4>
                    </div>
                    <div class="sign-up2">
                    <form:password path="account.confirmation" required="required"/>
                    <form:errors path="account.confirmation" class="alert alert-danger"  element="div"></form:errors>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <input type="submit" value="Envoyer">
        </form:form>
    </div>
</div>
