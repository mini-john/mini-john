
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
    <ul id="flexiselDemo1">			
        <li>
            <div class="banner-bottom-grid">
                <h3 >La puret&eacute; en toute simpicit&eacute;</h3>
                <p>JKalVered est votre outil pour vous aider à respecter votre puret&eacute; familiale (taharat hamishpacha). 
                    Vous n'avez qu'&agrave; renseigner des informations sur votre flux, l'appllication se chargera du reste.
                    Avec son large choix de configurations et de param&eacute;trages jKalVered est l&agrave; pour vous faciliter le calcul de vos cycles.
                    Avec son design moderne, son int&eacute;ractivit&eacute; avec vous, vous ne quitterez plus cet outil.

                </p>
                <div class="more" >
                    <a style="margin: 0 0 0 0;" href="<c:url value="/public/signin.do"/>" class="hvr-bounce-to-bottom sint">Inscription</a>
                </div>
            </div>
        </li>
    </ul>
</div>
<script type="text/javascript">
    $(window).on('load', function () {
        $("#flexiselDemo1").flexisel({
            visibleItems: 1,
            animationSpeed: 1000,
            autoPlay: false,
            autoPlaySpeed: 3000,
            pauseOnHover: true,
            enableResponsiveBreakpoints: true,
            responsiveBreakpoints: {
                portrait: {
                    changePoint: 480,
                    visibleItems: 1
                },
                landscape: {
                    changePoint: 640,
                    visibleItems: 1
                },
                tablet: {
                    changePoint: 768,
                    visibleItems: 1
                }
            }
        });

    });
</script>
<script type="text/javascript" src="<c:url value="/static/js/jquery.flexisel.js"/>"></script>    



<div class="banner-bottom">
    <ul id="flexiselDemo2">	
        <li>
            <div class="banner-bottom-grid">
                <img src="<c:url value="/static/images/livreor.png"/>" alt=" " class="img-responsive" />
                <h3>Livre d'or</h3>
                <p>JKalVered est votre outil, elle laisse la place &agrave; votre opinion. Laissez vos commentaires ou lisez les commentaires sur cet outil.</p>
                <div class="more">
                    <a href="single.html" class="hvr-bounce-to-bottom sint">Livre d'or</a>
                </div>
            </div>
        </li>
        <li>
            <div class="banner-bottom-grid">
                <img src="<c:url value="/static/images/inscription.jpg"/>" alt="inscription " class="img-responsive" />
                <h3>Inscription</h3>
                <p>JKalVered est la pour vous aider dans votre puret&eacute; familiale.
                    Avec son design moderne, jKalVered vous facilitera la t&acirc;che de calcul de votre caldendrier. 
                    N'h&eacute;sitez plus, inscrivez vous d&egrave;s maintenant.</p>

                <div class="more">
                    <a href="<c:url value="/public/signin.do"/>">Inscrivez-vous </a>
                </div>
            </div>
        </li>
        <li>
            <div class="banner-bottom-grid">
                <img src="<c:url value="/static/images/legale.jpg"/>" alt="approbation " class="img-responsive" />
                <h3>Approbations</h3>
                <p>JkalVered se veut &ecirc;tre un outil en ad&eacute;quation avec les Halahots strictes aussi bien pour les S&eacute;faradims
                    que les Achk&eacute;nazims. Des autorit&eacute;s rabbiniques ont valid&eacute;s l'application</p>
                <div class="more">
                    <a href="single.html" class="hvr-bounce-to-bottom sint">Approbations</a>
                </div>
            </div>
        </li>

    </ul>
    <script type="text/javascript">
    $(window).on('load', function () {
        $("#flexiselDemo2").flexisel({
            visibleItems: 3,
            animationSpeed: 1000,
            autoPlay: false,
            autoPlaySpeed: 3000,
            pauseOnHover: true,
            enableResponsiveBreakpoints: true,
            responsiveBreakpoints: {
                portrait: {
                    changePoint: 480,
                    visibleItems: 1
                },
                landscape: {
                    changePoint: 640,
                    visibleItems: 2
                },
                tablet: {
                    changePoint: 768,
                    visibleItems: 3
                }
            }
        });

    });
    </script>
    <script type="text/javascript" src="<c:url value="/static/js/jquery.flexisel.js"/>"></script>
</div>