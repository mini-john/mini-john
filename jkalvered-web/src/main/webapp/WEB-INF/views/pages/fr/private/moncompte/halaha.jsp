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
    });
</script>
<div class="container-fluid">
    <div class="row  mb-3  ">
        <div class=" col-md-4 ">

            <nav class="side-nav ">
                <ul class="nav-menu">
                    <li class="nav-item "><a href="<c:url value="/private/moncompte/index.do" />"><i class="fas fa-tachometer-alt"></i><span class="menu-text">Param&egrave;tre</span></a></li>
                    <li class="nav-item active" ><a href="<c:url value="/private/moncompte/halaha.do" />"><i class="fas fa-user"></i><span class="menu-text">Mes pr&eacute;f&eacute;nces Halakhique</span></a></li>
                    <li class="nav-item "><a href="<c:url value="/private/moncompte/localisation.do" />"><i class="fas fa-file-alt"></i><span class="menu-text">Ma G&eacute;localisation</span></a></li>

                </ul>
            </nav>

        </div>
        <div class="col-sm">je suis au milieu</div>
    </div>
</div>