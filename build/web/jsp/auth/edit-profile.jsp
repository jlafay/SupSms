<%-- 
    Document   : edit-profile
    Created on : 10 déc. 2014, 18:32:17
    Author     : Ju
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../../css/bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="../../css/supSms.css" type="text/css" />
        <title>SupSms - Edit my profile</title>
    </head>
    <body>
        
        <div class="container">
            <div id="header" class="hero-unit">
                <h1><img src="../../img/logo_supinfo_0063AF.png" alt="Logo of SUPINFO" />SupSms</h1>
                <p>Send SMS by internet !</br>
                "The cheaper way to send SMS"</br>
                Unlimited Text Messages for 10$ per month !</p>
            </div>
            
            <%@ include file="/jsp/includes/menuHeader.jsp" %>
            
            <h2>Edit my profile</h2>
            
            <center>
                <c:if test="${errors}">
                    <p style="color: red">Fields can't be empty.</p>
                </c:if>

                <form method="POST"> 
                    <div>
                        <label for="phoneNumber">Phone Number:</label>
                        <input value="${customer.phoneNumber}" type="text" id="phoneNumber" name="phoneNumber" />
                    </div>
                    <div>
                        <label for="firstName">First Name:</label>
                        <input value="${customer.firstName}" type="text" id="firstName" name="firstName" />
                    </div>
                    <div>
                        <label for="lastName">Last Name:</label>
                        <input value="${customer.lastName}" type="text" id="lastName" name="lastName" />
                    </div>
                    <div>
                        <label for="email">Email:</label>
                        <input value="${customer.emailAddress}" type="email" id="email" name="email" />
                    </div>
                    <div>
                        <label for="creditCard">Credit Card:</label>
                        <input value="${customer.creditCard}" type="text" id="creditCard" name="creditCard" />
                    </div>
                    <div>
                        <input type="submit" value="Save" class="btn btn-primary"/>
                    </div>
                </form>
            </center>
        </div>              
    </body>
    <%@ include file="/jsp/includes/footer.jsp" %>
</html>
