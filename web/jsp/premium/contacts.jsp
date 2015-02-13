<%-- 
    Document   : contacts
    Created on : 10 déc. 2014, 13:11:49
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
        <title>SupSms - Contacts</title>
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
            
            <h2>My contacts</h2>
            
            <div>
                <c:url value="/premium/contact/add" var="addContact" />
                <a href="${addContact}" class="btn btn-primary">Add a contact</a>
            </div><br>
            
            <table class="table table-bordered table-striped">
                <tr>
                    <th><span class="coloration">First Name</span></th>
                    <th><span class="coloration">Last Name</span></th>
                    <th><span class="coloration">Phone Number</span></th>
                    <th><span class="coloration">Administration</span></th>
                </tr>
                
                <c:forEach items="${contactList}" var="c">
                    <tr>
                        <td><c:out value="${c.firstName}" /></td>
                        <td><c:out value="${c.lastName}" /></td>
                        <td><c:out value="${c.phoneNumber}" /></td>
                        <c:url value="/premium/contact/update?id=${c.id}" var="updateContact" />
                        <td><a href="${updateContact}">update</a></td>
                        <c:url value="/premium/contact/delete?id=${c.id}" var="deleteContact" />
                        <td><a href="${deleteContact}">delete</a></td>
                    </tr>	
                </c:forEach>
            </table>  
                
        </div>              
    </body>
    <%@ include file="/jsp/includes/footer.jsp" %>
</html>
