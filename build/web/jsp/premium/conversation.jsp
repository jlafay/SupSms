<%-- 
    Document   : conversation
    Created on : 18 déc. 2014, 16:40:40
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
        <title>SupSms - Conversation</title>
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

            <div>
                <c:url value="/premium/new-message" var="newMessage" />
                <a href="${newMessage}" class="btn btn-primary">New Message</a>
            </div>
            <div>
                <table class="table table-bordered table-striped">
                    <tr>
                        <th><span class="coloration">Sender</span></th>
                        <th><span class="coloration">Content</span></th>
                        <th><span class="coloration">Date</span></th>
                    </tr>

                    <c:forEach items="${convers.smss}" var="s">
                        
                        <tr> 
                            <td><c:out value="${s.sender.phoneNumber}" /></td>
                            <td><c:out value="${s.content}" /></td>
                            <td><c:out value="${s.sentDate}" /></td>
                        </tr>	
                        
                    </c:forEach>
                </table>
            </div>
            
        </div>              
    </body>
    <%@ include file="/jsp/includes/footer.jsp" %>
</html>
