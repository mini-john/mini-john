<%-- 
    Document   : index
    Created on : 12 janv. 2023, 22:22:35
    Author     : jonat
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<script>
    jQuery(document).ready(function () {
        jQuery("#parametre").addClass('active');
        $.each(${allTimeZone}, function (key, value) {
            $('#mySelect')
                    .append($("<option></option>")
                            .attr("value", key)
                            .text(value));
        });
        $("#locateMe").click(function () {
             getLocalisation();
            $("#latitude").val(localisation.latitude);
            $("#longitude").val(localisation.longitude);
            $("#elevation").val(localisation.altitude);
             $("#ville").val(localisation.ville);
        });
    });
    
</script>
<div class="container-fluid">
    <div class="row  mb-3  ">
        <div class=" col-md-4 ">

            <nav class="side-nav ">
                <ul class="nav-menu">
                    <li class="nav-item"><a href="<c:url value="/private/moncompte/index.do" />"><i class="fas fa-tachometer-alt"></i><span class="menu-text">Param&egrave;tre</span></a></li>
                    <li class="nav-item"><a href="<c:url value="/private/moncompte/halaha.do" />"><i class="fas fa-user"></i><span class="menu-text">Mes pr&eacute;f&eacute;nces Halakhique</span></a></li>
                    <li class="nav-item active"><a href="<c:url value="/private/moncompte/localisation.do" />"><i class="fas fa-file-alt"></i><span class="menu-text">Ma G&eacute;localisation</span></a></li>

                </ul>
            </nav>

        </div>
        <div class="col-md-6" id="demo" >
            <h2>
                Ma Localisation
            </h2>
            <p>La d&eacute;termination des jours de s&eacute;parations, des cycles, des jours d&rsquo;attente et de purification d&eacute;pendent de votre localisation. Vous pouvez la d&eacute;finir ici.</p>
            <p>

            </p>
            <form role="form">
                <div class="rendered-form">
                    <input type="hidden" name="id"  id="id"/>
                    <div class="formbuilder-text form-group field-latitude">
                        <label for="latitude" class="formbuilder-text-label">Latitude</label>
                        <input type="text" class="form-control" name="latitude" id="latitude">
                    </div>
                    <div class="formbuilder-text form-group field-longitude">
                        <label for="longitude" class="formbuilder-text-label">Longitude</label>
                        <input type="text" class="form-control" name="longitude" id="longitude">
                    </div>
                    <div class="formbuilder-text form-group field-elevation">
                        <label for="elevation" class="formbuilder-text-label">Elevation</label>
                        <input type="text" class="form-control" name="elevation"  id="elevation">
                    </div>
                    <div class="formbuilder-text form-group field-ville">
                        <label for="ville" class="formbuilder-text-label">Ville</label>
                        <input type="text" class="form-control" name="ville"  id="ville">
                    </div>
                    <div class="formbuilder-button form-group field-locateMe">
                        <button type="button" class="btn btn-success" name="locateMe"   id="locateMe" >Me Localiser</button>
                    </div>
                     <div class="formbuilder-text form-group field-ville">
                        <label for="timezone" class="formbuilder-text-label">Mon Fuseau Horaire</label>
                         <select id="mySelect" name="timezone"></select>
                    </div>
                    
                   
                </div>
            </form>
        </div>


    </div>
</div>

<script src="<c:url value="/fr/private/geolocalisation.js" />" ></script>
