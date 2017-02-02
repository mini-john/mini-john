
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- register -->
<div class="sign-up-form">
    <h3>Register Here</h3>
    <p>Having hands on experience in creating innovative designs,I do offer design 
        solutions which harness</p>
    <div class="sign-up">
        <h5>Personal Information</h5>
        <div class="sign-u">
            <div class="sign-up1">
                <h4 class="a">First Name* :</h4>
            </div>
            <div class="sign-up2">
                <form>
                    <input type="text" placeholder=" " required=" "/>
                </form>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="sign-u">
            <div class="sign-up1">
                <h4 class="b">Last Name* :</h4>
            </div>
            <div class="sign-up2">
                <form>
                    <input type="text" placeholder=" " required=" "/>
                </form>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="sign-u">
            <div class="sign-up1">
                <h4 class="c">Email Address* :</h4>
            </div>
            <div class="sign-up2">
                <form>
                    <input type="text" placeholder=" " required=" "/>
                </form>
            </div>
            <div class="clearfix"> </div>
        </div>
        <h6>Login Information</h6>
        <div class="sign-u">
            <div class="sign-up1">
                <h4 class="d">Password* :</h4>
            </div>
            <div class="sign-up2">
                <form>
                    <input type="password" placeholder=" " required=" "/>
                </form>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="sign-u">
            <div class="sign-up1">
                <h4>Confirm Password* :</h4>
            </div>
            <div class="sign-up2">
                <form>
                    <input type="password" placeholder=" " required=" "/>
                </form>
            </div>
            <div class="clearfix"> </div>
        </div>
        <form>
            <input type="submit" value="Submit">
        </form>
    </div>
</div>
<!-- //register -->