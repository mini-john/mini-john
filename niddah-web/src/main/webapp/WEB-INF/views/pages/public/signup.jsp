
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<script type="text/javascript">
    $(function () {
        $('.active').removeClass('active');
    });
</script>
<div class="login">
    <div class="login-grids">
        <div class="col-md-6 log">
            <h3>Login</h3>
            <p>Welcome, please enter the following to continue.</p>
            <p>If you have previously Login with us, <a href="#">click here</a></p>
            <form>
                <h5>User Name:</h5>	
                <input type="text" value="">
                <h5>Password:</h5>
                <input type="password" value="">					
                <input type="submit" value="Login">

            </form>
            <a href="#">Forgot Password ?</a>
        </div>
        <div class="col-md-6 login-right">
            <h3>New Registration</h3>
            <p>By creating an account with our store, you will be able to move through the checkout process faster, store multiple shipping addresses, view and track your orders in your account and more.</p>
            <a href="<c:url value="/public/signin.do"/>">Inscription </a>
        </div>
        <div class="clearfix"></div>