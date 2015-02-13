<%-- 
    Document   : update-contact
    Created on : 10 déc. 2014, 17:39:29
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
        <title>SupSms - Edit a contact</title>
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
            
            <h2>Edit a contact</h2>
            
            <center>
                <c:if test="${errors}">
                    <p style="color: red">Fields can't be empty and the phone number need to exist in the database.</p>
                </c:if>

                <form method="POST"> 
                    <div>
                        <label for="phoneNumber">Phone Number</label>
                        <input value="${contact.phoneNumber}" type="text" id="phoneNumber" name="phoneNumber" />
                    </div>
                    <div>
                        <label for="firstName">First Name</label>
                        <input value="${contact.firstName}" type="text" id="firstName" name="firstName" />
                    </div>
                    <div>
                        <label for="lastName">Last Name</label>
                        <input value="${contact.lastName}" type="text" id="lastName" name="lastName" />
                    </div>
                    <div>
                        <label for="email">Email</label>
                        <input value="${contact.emailAddress}" type="email" id="email" name="email" />
                    </div>
                    <div>
                        <label for="postalAddress">Postal Address</label>
                        <input value="${contact.postalAddress}" type="text" id="postalAddress" name="postalAddress" />
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
