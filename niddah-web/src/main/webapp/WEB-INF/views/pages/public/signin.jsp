
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<script src='https://www.google.com/recaptcha/api.js'></script>
<!-- register -->
<div class="sign-up-form">
    <h3>Inscription sur le site JKalVered</h3>
    <p>Les calculs de cycle vous sont compliqu&eacute;s, jKalVered est l&agrave; pour vous les facilit&eacute;s. 
        Laissez vous guider pour l'inscription, celle-ci est gratuite. </p>
    <div class="sign-up">

        <c:if test="${not empty message}"><div class="message green">${message}</div></c:if>
            <h5>Information personnelle</h5>
        <form:form action="./signin.do" modelAttribute="personne" method="post" >

            <div class="sign-u">
                <div class="sign-up1">
                    <h4 class="a">Prenom* :</h4>
                </div>
                <div class="sign-up2">
                    <form:input path="prenom"  type="text" placeholder=" "  />
                    <form:errors path="prenom" class="alert alert-danger"  element="div"></form:errors>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <div class="sign-u">
                    <div class="sign-up1">
                        <h4 class="b">Nom* :</h4>
                    </div>
                    <div class="sign-up2">
                    <form:input path="nom"  type="text" placeholder=" " required="required"/>
                    <form:errors path="nom" class="alert alert-danger"  element="div"></form:errors>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <div class="sign-u">
                    <div class="sign-up1">
                        <h4 class="b">Genre* :</h4>
                    </div>
                    <div class="sign-up2" style="margin-top: 25px;">
                    <form:radiobuttons path="sexe" />

                    <p style="color:#333;text-align: left;font-size: 100%"> <form:errors path="sexe" class="alert alert-danger"  element="div"></form:errors></p>
                    </div>
                    <div class="clearfix"> </div>
                </div>

                <h6>Information du compte </h6>
                <div class="sign-u">
                    <div class="sign-up1">
                        <h4 class="c">Identifiant* :</h4>
                    </div>
                    <div class="sign-up2">
                    <form:input path="account.login"  type="text" placeholder=" " required="required" title="Charactères alphanueric seulement" data-placement="right"/>
                    <form:errors path="account.login" class="alert alert-danger"  element="div"></form:errors>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <div class="sign-u">
                    <div class="sign-up1">
                        <h4 class="c">Email* :</h4>
                    </div>
                    <div class="sign-up2">
                    <form:input path="account.mail"  type="text" placeholder=" " required="required"/>
                    <form:errors path="account.mail" class="alert alert-danger"  element="div"></form:errors>

                    </div>
                    <div class="clearfix"> </div>
                </div>
            <c:if test="${device.isNormal()}">
                <div class="sign-u">
                    <div class="sign-up1">
                        <h4 class="c">Captcha :</h4>
                    </div>
                    <div class="sign-up2">

                        <div class="g-recaptcha" data-sitekey="${captchaService.getReCaptchaSite()}" data-callback="onReCaptchaSuccess" data-expired-callback="onReCaptchaExpired"></div>
                        <form:errors path="captcha" class="alert alert-danger"  element="div"></form:errors>
                            <span id="captchaError" class="alert alert-danger" style="display:none"></span>

                        </div>
                        <div class="clearfix"> </div>
                    </div>
            </c:if>
            <input type="submit" value="Inscription">
        </form:form>
    </div>

</div>
<!-- //register -->

<script>
    var onReCaptchaSuccess = function (response) {
        $("#captchaError").html("").hide();
    };

    var onReCaptchaExpired = function (response) {
        $("#captchaError").html("reCaptcha has expired.  Please solve a new reCaptcha").show();
        grecaptcha.reset();
    };
    $(function () {
        $("#personne :input").tooltip({

            // place tooltip on the right edge
            //  position: "right",

            // a little tweaking of the position
//              offset: [-2, 10],

            // use the built-in fadeIn/fadeOut effect
            effect: "fade"

                    // custom opacity setting
                    // opacity: 0.7


        });
    });

</script>
<style>
    #rc-imageselect {transform:scale(0.77);-webkit-transform:scale(0.77);transform-origin:0 0;-webkit-transform-origin:0 0;}
</style>