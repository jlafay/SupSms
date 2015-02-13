<%-- 
    Document   : new-message
    Created on : 18 déc. 2014, 08:37:12
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
        <title>SupSms - New Message</title>
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
            
            <h2>New message</h2>
            
            <center>
                <c:if test="${errors}">
                    <p style="color: red">The content field can't be empty and the phone number need to exist in the database.</p>
                </c:if>

                <form method="POST">
                    <label for="phoneNumber">Enter phone number or select a contact</label>
                    <select name="contactSelected">
                        <option value=""></option>
                        <c:forEach items="${contactList}" var="c">
                            <option value="${c.phoneNumber}">${c.firstName}</option>	
                        </c:forEach>
                    </select> 

                    <input type="text" id="phoneNumber" name="phoneNumber" value="${c.phoneNumber}"/>

                    <div>
                        <label for="content">Message</label>
                        <textarea type="text" id="content" name="content" ></textarea>
                    </div> 
                    <div>
                        <input type="submit" value="Send" class="btn btn-primary"/>
                    </div>
                </form>
            </center>
        </div>              
    </body>
    <%@ include file="/jsp/includes/footer.jsp" %>
</html>
