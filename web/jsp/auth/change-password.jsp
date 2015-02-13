<%-- 
    Document   : change-password
    Created on : 10 déc. 2014, 18:50:22
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
        <title>SupSms - Change my password</title>
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
            
            <h2>Change my password</h2>
            
            <center>
                <c:if test="${errors}">
                    <p style="color: red">There are errors in the form.</p>
                </c:if>

                <form method="POST">          
                    <div>
                        <label for="oldPassword">Old Password:</label>
                        <input type="password" id="oldPassword" name="oldPassword" />
                    </div>
                    <div>
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" />
                    </div>
                    <div>
                        <label for="password2">Verify Password:</label>
                        <input type="password" id="password2" name="password2" />
                    </div>
                    <div>
                        <input type="submit" value="Change" class="btn btn-primary"/>
                    </div>
                </form>
            </center>
        </div>                
    </body>
    <%@ include file="/jsp/includes/footer.jsp" %>
</html>
