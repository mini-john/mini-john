<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTagLib.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<h1>Gestion des Utilisateurs</h1>
<div class="row">
    <div class="col-md-12">
        <c:if test="${SUCCESS_MESSAGE!= null}">
            <div class="alert alert-success" role="alert">
                <h3>Message</h3>
                <p>${SUCCESS_MESSAGE}</p>
            </div>
        </c:if>

        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">&nbsp;
        </div>
        <div class="col-lg-6 col-md-6">

            <a href="<c:url value="/admin/utilisateur/add.do"/>" class="btn btn-primary">Ajouter un utilisateur</a>
        </div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Email</th>
                    <th>login</th>
                    <th>nom</th>
                    <th>Etat du compte</th>
                    <th>Compte blocque</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${accounts}" var="account" varStatus="itr">
                    <tr>
                        <th scope="row">${account.id}</th>
                        <td>${account.mail}</td>
                        <td>${account.login}</td>
                        <td>${account.personne.nom}&nbsp;${account.personne.prenom} </td>
                        <td>${account.etatAccount}</td>
                        <td>${account.accountBlock}</td>
                        <td>${account.role}</td>
                        <td>
                            <c:if test="${account.accountBlock}">le commpte est bloque</c:if>
                        
                        </td>
                    </tr>

                </c:forEach>
            </tbody>
        </table>
        <tag:paginate max="5" division="5" offset="${offset}" count="${count}"
                      uri="index.do" next="&raquo;" previous="&laquo;" />
    </div>
</div>