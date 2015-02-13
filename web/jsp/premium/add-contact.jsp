<%-- 
    Document   : addContact
    Created on : 10 déc. 2014, 14:43:00
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
        <title>SupSms - Add a contact</title>
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
            
            <h2>Add a contact</h2>
            
            <center>       
                <c:if test="${errors}">
                    <p style="color: red">There are errors in the form or the phone number doesn't exist.</p>
                </c:if>

                <form method="POST"> 
                    <div>
                        <label for="phoneNumber">Phone Number</label>
                        <input type="text" id="phoneNumber" name="phoneNumber" />
                    </div>
                    <div>
                        <label for="firstName">First Name</label>
                        <input type="text" id="firstName" name="firstName" />
                    </div>
                    <div>
                        <label for="lastName">Last Name</label>
                        <input type="text" id="lastName" name="lastName" />
                    </div>
                    <div>
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" />
                    </div>
                    <div>
                        <label for="postalAddress">Postal Address</label>
                        <input type="text" id="postalAddress" name="postalAddress" />
                    </div>
                    <div>
                        <input type="submit" value="Add" class="btn btn-primary"/>
                    </div>
                </form>
            </center>
        </div>              
    </body>
    <%@ include file="/jsp/includes/footer.jsp" %>
</html>
