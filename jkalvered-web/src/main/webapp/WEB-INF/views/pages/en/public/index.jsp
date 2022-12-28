
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="<c:url value="/static/public/js/responsiveslides.min.js"/>"></script>
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
                    <h3 >La puret&eacute; en toute simpicit&eacute; je suis en anglaiss!!!!</h3>
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
                    <img src="<c:url value="/static/public/images/inscription.jpg"/>" alt="inscription " class="img-responsive" />
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
                    <img src="<c:url value="/static/public/images/visiteguide.png"/>" alt=" " class="img-responsive" />
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
                    <img src="<c:url value="/static/public/images/legale.jpg"/>" alt="approbation " class="img-responsive" />
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

        <div class="blog">
            <div class="blog-left">
                <div class="blog-left-grid">
                    <div class="blog-left-grid-left">
                        <h3><a href="<c:url value="/public/blog/view.do?id=${post.id}"/>">${post.title}</a></h3>
                        <p> autheur <span>${post.author.login}</span> | <fmt:formatDate type = "date" value = "${post.date}" /><!-- | <span>Sint</span>--></p>

                    </div>
                    <div class="blog-left-grid-right">
                        <a href="<c:url value="/public/blog/view.do?id=${post.id}"/>#commentaire" class="hvr-bounce-to-bottom non">${post.commentss.size()} commentaire(s)</a>
                    </div>
                    <div class="clearfix"> </div>
                    <p class="para"> ${post.body}</p>
                    <div class="rd-mre">
                        <a href="<c:url value="/public/blog/view.do?id=${post.id}"/>" class="hvr-bounce-to-bottom quod">Lire plus</a>
                    </div>
                </div>
            </div>
            <div class="blog-right">
                <div class="sap_tabs">	
                    <div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
                        <ul class="resp-tabs-list">
                            <li class="resp-tab-item grid1" aria-controls="tab_item-0" role="tab"><span>Commentaires</span></li>
                            <li class="resp-tab-item grid2" aria-controls="tab_item-1" role="tab"><span>Livre d'or</span></li>
                            <div class="clear"></div>
                        </ul>				  	 
                        <div class="resp-tabs-container">
                            <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
                                <c:forEach items="${comments}" var="comment" varStatus="itr">
                                    <div class="facts">
                                        <div class="tab_list">
                                            <img src="<c:url value="/static/public/images/co.png"/>" alt=" " class="img-responsive" />
                                        </div>
                                        <div class="tab_list1">
                                            <a href="<c:url value="/public/blog/view.do?id=${comment.post.id}"/>">${comment.autheur} - ${comment.post.title}</a>
                                            <p><fmt:formatDate type = "date" value = "${comment.dateComments}" /> <span>${comment.commentaire}</span></p>
                                        </div>
                                        <div class="clearfix"> </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-1">
                                <div class="facts">
                                    <div class="tab_list">
                                        <a href="images/8-.jpg" class="b-link-stripe b-animate-go   swipebox"  title="">
                                            <img src="images/8.jpg" alt=" " class="img-responsive" />
                                        </a>
                                    </div>
                                    <div class="tab_list1">
                                        <a href="#">Livre d'or</a>
                                        <p>June 30,2015<span>Nam libero tempore, cum soluta nobis.</span></p>
                                    </div>
                                    <div class="clearfix"> </div>
                                </div>
                                <div class="facts">
                                    <div class="tab_list">
                                        <a href="images/9-.jpg" class="b-link-stripe b-animate-go   swipebox"  title="">
                                            <img src="images/9.jpg" alt=" " class="img-responsive" />
                                        </a>
                                    </div>
                                    <div class="tab_list1">
                                        <a href="#">excepturi sint occaecati</a>
                                        <p>June 30,2015<span>Nam libero tempore, cum soluta nobis.</span></p>
                                    </div>
                                    <div class="clearfix"> </div>
                                </div>
                                <div class="facts">
                                    <div class="tab_list">
                                        <a href="images/10-.jpg" class="b-link-stripe b-animate-go   swipebox"  title="">
                                            <img src="images/10.jpg" alt=" " class="img-responsive" />
                                        </a>
                                    </div>
                                    <div class="tab_list1">
                                        <a href="#">excepturi sint occaecati</a>
                                        <p>June 30,2015<span>Nam libero tempore, cum soluta nobis.</span></p>
                                    </div>
                                    <div class="clearfix"> </div>
                                </div>
                                <div class="facts">
                                    <div class="tab_list">
                                        <a href="images/7-.jpg" class="b-link-stripe b-animate-go   swipebox"  title="">
                                            <img src="images/7.jpg" alt=" " class="img-responsive" />
                                        </a>
                                    </div>
                                    <div class="tab_list1">
                                        <a href="#">excepturi sint occaecati</a>
                                        <p>June 30,2015<span>Nam libero tempore, cum soluta nobis.</span></p>
                                    </div>
                                    <div class="clearfix"> </div>
                                </div>
                            </div>

                        </div>
                        <script src="<c:url value="/static/public/js/easyResponsiveTabs.js"/>" type="text/javascript"></script>
                        <script type="text/javascript">
        $(document).ready(function () {
            $('#horizontalTab').easyResponsiveTabs({
                type: 'default', //Types: default, vertical, accordion           
                width: 'auto', //auto or any width like 600px
                fit: true   // 100% fit in a container
            });
        });
                        </script>
                        <link rel="stylesheet" href="<c:url value="/static/public/css/swipebox.css"/>"/>
                        <script src="<c:url value="/static/public/js/jquery.swipebox.min.js"/>"></script> 
                        <script type="text/javascript">
        jQuery(function ($) {
            $(".swipebox").swipebox();
        });
                        </script>
                    </div>
                </div>
                <div class="newsletter">
                    <h3>Inscription à la Newsletter</h3>
                    <input id="emailNewsletter" type="text" value="Email Address" onfocus="this.value = '';" onblur="if (this.value == '') {
                                this.value = 'Email Address';
                            }" required="">
                    <input type="submit" value="Envoyer" id="envoyerNewsletter">
                    <div class="alert alert-info" role="alert" id="msgNewsletter" style="visibility: hidden">
                        
                    </div>
                    <script type="text/javascript">
                        $(document).ready(function () {
                            $('#envoyerNewsletter').click(function () {
                                $.post('${pageContext.request.contextPath}/public/newsletter.do', {email: $('#emailNewsletter').val()}, function (reponse) {
                                    console.log("toto")
                                   $('#msgNewsletter').html(reponse.msg)
                                   .css('visibility', 'visible');
                                });
                            });
                        });
                    </script>

                </div>
            </div>
        </div>
    </div>

</div>

