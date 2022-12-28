
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="sign-up-form">
    <h3>Oubli de mot de passe de votre compte sur jKalVered</h3>
    <p>Vous avez oubli&eacute; votre mot de passe. Pas de souci la proc&eacute;dure est tr&e&egrave;s simple,
        remplissez le formulaire ci-dessous un email vous sera envoy&eacute; pour saisir un nouveau mot de passe. </p>
    <div class="sign-up">

        <c:if test="${not empty message}"><div class="message green">${message}</div></c:if>
            <h5>Information personnelle</h5>
        <form:form action="./verify.do" modelAttribute="account" method="post" >

            <div class="sign-u">
                <div class="sign-up1">
                    <h4 class="a">Email* :</h4>
                </div>
                <div class="sign-up2">
                    <form:input path="mail"  type="text" placeholder=" "  />
                    <form:errors path="mail" class="alert alert-danger"  element="div"></form:errors>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <input type="submit" value="Envoyer">
        </form:form>
    </div>
</div>