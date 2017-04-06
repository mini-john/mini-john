<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTagLib.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<div class="container">
    <div class="well">
        <strong>List of Persons</strong>
    </div>
    <table class="table table-stripped">
        <tr>
            <th>S.No</th>
            <th>Name</th>
            <th>Age</th>
        </tr>
        <c:forEach items="${posts}" var="post" varStatus="itr">
            <tr>
                <td>${offset + itr.index +1 }</td>
                <td>${post.name }</td>
                <td>${post.age }</td>
            </tr>
        </c:forEach>
    </table>
    <tag:paginate max="15" offset="${offset}" count="${count}"
                  uri="/public/blog/index.do" next="&raquo;" previous="&laquo;" />
</div>
