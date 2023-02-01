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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<script>
    jQuery(document).ready(function () {
        jQuery("#parametre").addClass('active');

        $("#locateMe").click(function () {
            getLocalisation();
            $("#latitude").val(localisation.latitude);
            $("#longitude").val(localisation.longitude);
            if (localisation.altitude) {
                $("#elevation").val(localisation.altitude);
            } else {
                $("#elevation").val(0.0);
            }
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
            <div class="success-msg">
                <i class="fa fa-check"></i>
                Votre localisation a été enregistré avec succès
            </div>
            <form:form method="POST" action="savelocalisation.do" 
                       modelAttribute="localisation">
                <div class="rendered-form">
                    <input type="hidden" name="id"  id="id"/>
                    <div class="formbuilder-text form-group field-latitude">
                        <form:label path="latitude" for="latitude" class="formbuilder-text-label">Latitude</form:label>
                        <form:input   path="latitude" type="text" class="form-control" name="latitude" id="latitude"/>
                        <form:errors path="latitude" cssClass="error" element="div" />
                    </div>
                    <div class="formbuilder-text form-group field-longitude">
                        <form:label path="longitude" for="longitude" class="formbuilder-text-label">Longitude</form:label>
                        <form:input path="longitude" type="text" class="form-control" name="longitude" id="longitude"/>
                        <form:errors path="longitude" cssClass="error" element="div" />
                    </div>
                    <div class="formbuilder-text form-group field-elevation">
                        <form:label path="elevation" for="elevation" class="formbuilder-text-label">Elevation</form:label>
                        <form:input path="elevation" type="text" class="form-control" name="elevation"  id="elevation"/>
                        <form:errors path="elevation" cssClass="error" element="div" />
                    </div>
                    <div class="formbuilder-text form-group field-ville">
                        <form:label path="locationName" for="locationName" class="formbuilder-text-label">Ville</form:label>
                        <form:input  path="locationName" type="text" class="form-control" name="locationName"  id="ville"/>
                        <form:errors path="locationName" cssClass="error" element="div" />
                    </div>
                    <div class="formbuilder-button form-group field-locateMe">
                        <button type="button" class="btn btn-success" name="locateMe"   id="locateMe" >Me Localiser</button>
                    </div>
                    <div class="formbuilder-text form-group field-ville">
                        <form:label path="timeZone" for="timeZone" class="formbuilder-text-label">Mon Fuseau Horaire </form:label>
                        <form:select   path="timeZone" id="timeZone" items="${allTimeZone}" name="timeZone"></form:select>
                        <form:errors path="timeZone" cssClass="error" element="div" />
                    </div>
                    <form:button type="submit" class="btn btn-primary">
                        Enregistrer
                    </form:button>


                </div>
            </form:form >
        </div>


    </div>
</div>

<script src="<c:url value="/fr/private/geolocalisation.js" />" ></script>
