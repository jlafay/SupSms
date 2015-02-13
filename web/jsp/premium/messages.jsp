<%-- 
    Document   : messages
    Created on : 18 déc. 2014, 08:34:18
    Author     : Ju
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="../css/supSms.css" type="text/css" />
        <title>SupSms - Messages</title>
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
            
            <h2>My messages</h2>
            
            <div>
                <c:url value="/premium/new-message" var="newMessage" />
                <a href="${newMessage}" class="btn btn-primary">New Message</a>
            </div>
            <div>
                <table class="table table-bordered table-striped">
                    <tr>
                        <th><span class="coloration">Contact</span></th>
                        <th><span class="coloration">Date of the last message</span></th>
                        <th><span class="coloration">Number of sent sms</span></th>
                        <th><span class="coloration">Administration</span></th>
                    </tr>
                    
                    <c:set var="i" value="0"/>
                    <c:forEach items="${conversations}" var="c">
                        
                        <tr>
                            <c:forEach items="${c.customers}" var="cu">
                                <td><c:out value="${cu.phoneNumber}" /></td>
                            </c:forEach>
                            <td><c:out value="${c.dateUpdated}" /></td>
                            <td><c:out value="${numberOfSentMessage[i]}" /></td>
                            <c:url value="/premium/conversation/read?id=${c.id}" var="conversation" />
                            <td><a href="${conversation}">show</a></td>
                            <c:url value="/premium/conversation/delete?id=${c.id}" var="deleteConversation" />
                            <td><a href="${deleteConversation}">delete</a></td>
                        </tr>	
                        <c:set var="i" value="${i + 1}" scope="page"/>
                    </c:forEach>
                </table>
            </div>
            
        </div>              
    </body>
    <%@ include file="/jsp/includes/footer.jsp" %>
</html>
