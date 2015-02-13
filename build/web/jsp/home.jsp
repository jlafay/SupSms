<%-- 
    Document   : home
    Created on : 7 déc. 2014, 17:10:37
    Author     : Ju
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="css/supSms.css" type="text/css" />
        <title>SupSms - Home</title>
    </head>
    <body>
        
        <div class="container">
            <div id="header" class="hero-unit">
                <h1><img src="img/logo_supinfo_0063AF.png" alt="Logo of SUPINFO" />SupSms</h1>
                <p>Send SMS by internet !</br>
                "The cheaper way to send SMS"</br>
                Unlimited Text Messages for 10$ per month !</p>
            </div>
            
            <%@ include file="/jsp/includes/menuHeader.jsp" %>
            <center>
                <h2>Welcome !</h2>
                
                <p>
                    Welcome on the SupSMS services, the better and cheapest way to send SMS<br>
                    and to synchronise your contacts and your messages to your mobile phone.
                </p>
                
                <div>
                    <c:if test="${empty isPremium}">
                        <c:url value="/offer" var="offer" />
                        <a href="${offer}" class="btn btn-primary">See our offer</a>
                    </c:if>
                </div>
                    
                <h2>Statistics :</h2>

                <div>
                    <p>Number of users : ${nbCustomer}</p>
                </div>
                <div>
                    <p>Number of sent SMS : ${nbSentSms}</p>
                </div>
            </center>
        </div>              
    </body>
    <%@ include file="/jsp/includes/footer.jsp" %>
</html>
