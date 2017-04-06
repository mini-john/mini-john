<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="distracted">
    <h3 class="ghj" style="text-align: center">Changement de mot de passe sur le site JKalVered</h3>
    <div class="alert alert-danger ">
        <img src="<c:url value="/static/images/erreur.png"/>" style="width:25px; height: 25px"/>
        Une erreur est survenue en vérifiant votre addresse.

        Les raisons possibles sont :
        <div style="padding: 1%;">


            <ul>
                <li>Le lien est p&eacute;rim&eacute;.</li>
                <li>Le compte n'existe pas.</li>
            </ul>
            <div class="clearfix"> </div>

        </div>
        <div class="clearfix"> </div>
        <div class="banner-bottom">
            <div class="banner-bottom-grid">
                <div class="more">
                    <div class="nbs-flexisel-item">
                        <a href="<c:url value="/public/index.do"/>" style="display:inline; padding-top: 9px;" class="hvr-bounce-to-bottom sint">Retour &agrave; l'acceuil </a>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>