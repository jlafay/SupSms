<%-- 
    Document   : profile
    Created on : 10 déc. 2014, 18:19:08
    Author     : Ju
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="../css/supSms.css" type="text/css" />
        <title>SupSms - Profile</title>
    </head>
    <body>
        <div class="container">
            <div id="header" class="hero-unit">
                <h1><img src="../img/logo_supinfo_0063AF.png" alt="Logo of SUPINFO" />SupSms</h1>
                <p>Send SMS by internet !</br>
                "The cheaper way to send SMS"</br>
                Unlimited Text Messages for 10$ per month !</p>
            </div>
            
            <%@ include file="/jsp/includes/menuHeader.jsp" %>
            
            <h2>My Profile</h2>
            
            <center>
                <p>
                    First Name: ${customer.firstName}<br>
                    Last Name: ${customer.lastName}<br>
                    Email: ${customer.emailAddress}<br>
                    Phone Number: ${customer.phoneNumber}<br>
                    Credit Card: ${customer.creditCard}<br>
                </p>
                <div>
                    <c:url value="/auth/profile/update" var="editProfile" />
                    <a href="${editProfile}" class="btn btn-primary">Edit my profile</a>

                    <c:url value="/auth/password/update" var="changePassword" />
                    <a href="${changePassword}" class="btn btn-primary">Change my password</a>
                </div>
            </center>
        </div>                
    </body>
    <%@ include file="/jsp/includes/footer.jsp" %>
</html>