<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <h3 class="ghj" style="text-align: center">Inscription sur le site JKalVered</h3>
    <div class="row">
        <div class="col-md-2">&nbsp;</div>
        <div class="col-md-8">
            <div class="alert alert-danger ">
                <img src="<c:url value="/static/public/images/erreur.png"/>" style="width:25px; height: 25px"/>
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
                                <a href="<c:url value="/public/signin.do"/>" style="display:inline; padding-top: 9px;" class="hvr-bounce-to-bottom sint">R&eacute;inscription </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-2">&nbsp;</div>
    </div>

