
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="header-bottom-top">
    <ul>
        <li><a href="#" class="g"> </a></li>
        <li><a href="#" class="p"> </a></li>
        <li><a href="https://plus.google.com/u/0/100048785541154475196" class="facebook" target="_blank"> </a></li>
        <li><a href="#" class="twitter"> </a></li>
    </ul>
</div>
<div class="clearfix"> </div>
<!-- banner -->
<div class="banner">
    <!-- Slider-starts-Here -->
    <script src="<c:url value="/static/js/responsiveslides.min.js"/>"></script>
    <script>
        // You can also use "$(window).load(function() {"
        $(function () {
            $('#linkacceuil').addClass('active');
        });
        $(function () {
            // Slideshow 4
            $("#slider3").responsiveSlides({
                auto: true,
                pager: false,
                nav: true,
                speed: 500,
                namespace: "callbacks",
                before: function () {
                    $('.events').append("<li>before event fired.</li>");
                },
                after: function () {
                    $('.events').append("<li>after event fired.</li>");
                }
            });

        });

    </script>
    <!--//End-slider-script -->

    <div  id="top" class="callbacks_container wow fadeInUp" data-wow-delay="0.5s">
        <ul class="rslides" id="slider3">
            <li>
                <div class="banner-inf">
                    <h3>L<!--a beaut&eacute; de l-->a puret&eacute; familiale dans la Torah</h3>
                    <p style="font-family: 'Suez One', serif;font-size:1.25em ">אמרה תורה תהא טמאה שבעה ימים כדי שתהא חביבה על בעלה כשעת כניסתה לחופה:  נדה  לא</p>
                </div>
            </li>

        </ul>
    </div>
</div>
<!-- //banner -->
<!-- banner-bottom -->
<div class="banner-bottom">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="banner-bottom-grid">
                    <h3 >La puret&eacute; en toute simpicit&eacute;</h3>
                    <p>JKalVered est votre outil pour vous aider à respecter votre puret&eacute; familiale (taharat hamishpacha). 
                        Vous n'avez qu'&agrave; renseigner des informations sur votre flux, l'appllication se chargera du reste.
                        Avec son large choix de configurations et de param&eacute;trages jKalVered est l&agrave; pour vous faciliter le calcul de vos cycles.
                        Avec son design moderne, son int&eacute;ractivit&eacute; avec vous, vous ne quitterez plus cet outil.

                    </p>
                    <div class="more" >
                        <div class="nbs-flexisel-item">
                            <a  href="<c:url value="/public/signin.do"/>" class="hvr-bounce-to-bottom sint">Inscription</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="banner-bottom">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4">
                <div class="banner-bottom-grid">
                    <img src="<c:url value="/static/images/inscription.jpg"/>" alt="inscription " class="img-responsive" />
                    <h3>Inscription</h3>
                    <p>JKalVered est la pour vous aider dans votre puret&eacute; familiale.
                        Avec son design moderne, jKalVered vous facilitera la t&acirc;che de calcul de votre caldendrier. 
                        N'h&eacute;sitez plus, inscrivez vous d&egrave;s maintenant.</p>

                    <div class="more">
                        <div class="nbs-flexisel-item">
                            <a href="<c:url value="/public/signin.do"/>" style="margin:0 0 0 0;" class="hvr-bounce-to-bottom sint">Inscrivez-vous </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class=" banner-bottom-grid">
                    <img src="<c:url value="/static/images/visiteguide.png"/>" alt=" " class="img-responsive" />
                    <h3>Visite guid&eacute;e</h3>
                    <p>Vous avez un petit doute sur jKalVered, faites une visite de l'application. Vous serez &eacute;tonn&eacute;s !</p>
                    <div class="more">
                        <div class="nbs-flexisel-item">
                            <a href="single.html" class="hvr-bounce-to-bottom sint">Visite guidée</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="banner-bottom-grid">
                    <img src="<c:url value="/static/images/legale.jpg"/>" alt="approbation " class="img-responsive" />
                    <h3>Approbations</h3>
                    <p>JkalVered se veut &ecirc;tre un outil en ad&eacute;quation avec les Halahots strictes aussi bien pour les S&eacute;faradims
                        que les Achk&eacute;nazims. Des autorit&eacute;s rabbiniques ont valid&eacute;s l'application</p>
                    <div class="more">
                        <div class="nbs-flexisel-item">
                            <a href="single.html" class="hvr-bounce-to-bottom sint">Approbations</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



