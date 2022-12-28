
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

If you found this us
<div class="sign-up-form">
    <h3>Inscription sur le site JKalVered</h3>
    <p>Les calculs de cycle vous sont compliqu&eacute;s, jKalVered est l&agrave; pour vous les facilit&eacute;s. 
        Laissez vous guider pour l'inscription, celle-ci est gratuite. </p>
    <div class="sign-up">

        <div class="alert alert-danger" role="alert">Un compte inactif avec ${exist}</div>
        <h5>Information personnelle</h5>
        <form:form action="./validSignin.do" modelAttribute="personne" method="post" >
            <form:hidden path="id"  />
            <form:hidden path="account.id"  />
             <form:hidden path="sexe"  />
            <div class="sign-u">
                <div class="sign-up1">
                    <h4 class="a">Prenom* :</h4>
                </div>
                <div class="sign-up2">
                    <form:input path="prenom"  type="text" placeholder=" " required=" " disabled="true"/>
                    <form:errors path="prenom" cssclass="error"></form:errors>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <div class="sign-u">
                    <div class="sign-up1">
                        <h4 class="b">Nom* :</h4>
                    </div>
                    <div class="sign-up2">
                    <form:input path="nom"  type="text" placeholder=" " required=" " disabled="true"/>
                    <form:errors path="nom" cssclass="error"></form:errors>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                

                <h6>Information du compte</h6>
                <div class="sign-u">
                    <div class="sign-up1">
                        <h4 class="c">Identifiant* :</h4>
                    </div>
                    <div class="sign-up2">
                    <form:input path="account.login"  type="text" placeholder=" " required=" " disabled="true"/>
                    <form:errors path="account.login" cssclass="error"></form:errors>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <div class="sign-u">
                    <div class="sign-up1">
                        <h4 class="c">Email* :</h4>
                    </div>
                    <div class="sign-up2">
                    <form:input path="account.mail"  type="text" placeholder=" " required=" " disabled="true"/>

                </div>
                <div class="clearfix"> </div>
            </div>


            <input type="submit" value="Valider les informations">
        </form:form>
    </div>

</div>