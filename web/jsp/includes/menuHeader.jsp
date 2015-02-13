<%-- 
    Document   : menuHeader
    Created on : 12 déc. 2014, 18:12:34
    Author     : Ju
--%>

<nav class="navbar navbar-static-top">
    <div class="navbar-inner">
        <div class="container">
            <ul class="nav nav-tabs nav-stacked">
                <c:url value="/home" var="home" />
                <li><a href="${home}">Home</a></li>

                <c:url value="/offer" var="offer" />
                <li><a href="${offer}">Offer</a></li>
                
                <c:if test="${not empty isPremium}">
                    <c:url value="/premium/contacts" var="contacts" />
                    <li><a href="${contacts}">Contacts</a></li>
                    
                    <c:url value="/premium/messages" var="messages" />
                    <li><a href="${messages}">Messages</a></li>
                    
                    <c:url value="/premium/new-message" var="newMessage" />
                    <li><a href="${newMessage}">New Message</a></li>
                </c:if>
                
                <c:choose>
                    <c:when test="${not empty customerId}">
                        <c:url value="/auth/profile" var="profile" />
                        <li><a href="${profile}">My Profile</a></li>
                        
                        <c:url value="/auth/logout" var="logout" />
                        <li><a href="${logout}">${firstName} - Logout</a></li>
                    </c:when>
                    <c:otherwise>               
                        <c:url value="/register" var="register" />
                        <li><a href="${register}">Register</a></li>

                        <c:url value="/login" var="login" />
                        <li><a href="${login}">Login</a></li>
                    </c:otherwise>
                </c:choose>
                <c:if test="${not empty isAdmin}">
                    <c:url value="/admin/customers" var="customers" />
                    <li><a href="${customers}">Admin Panel</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
