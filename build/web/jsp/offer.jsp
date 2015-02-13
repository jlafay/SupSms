<%-- 
    Document   : offer
    Created on : 7 déc. 2014, 16:59:33
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
        <title>SupSms - Offer</title>
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
            
            <h2>Our Offer</h2>
            <center>
                <p>
                    Discover our offer,<br>
                    For only 10£ you will be able to send and recieve messages from everywhere around the world and to get it on all your devices.<br>
                    This allow you to be connected with your contacts every time !
                </p>

                <c:choose>
                    <c:when test="${not empty customerId}">
                        <c:if test="${empty isPremium}">
                            <div>
                                <c:url value="/auth/become-premium" var="becomePremium" />
                                <a href="${becomePremium}" class="btn btn-primary ">Subscribe</a>
                            </div>
                        </c:if>   
                    </c:when>
                    <c:otherwise>    
                        <div>
                            <c:url value="/register" var="register" />
                            <a href="${register}" class="btn btn-primary">To subscribe to our offer you need to register</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </center>
        </div>
    </body>
    <%@ include file="/jsp/includes/footer.jsp" %>
</html>

