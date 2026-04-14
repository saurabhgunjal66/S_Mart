<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - ZenMart</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .error-message { 
            color: #ff4d4d; background-color: #ffe8e8; padding: 10px; 
            border: 1px solid #ff4d4d; border-radius: 5px;
            text-align: center; margin-bottom: 15px;
        }
        /* Style for the new registration links */
        .registration-options {
            margin-top: 25px;
            font-size: 0.95rem;
            color: #555;
        }
        .registration-options a {
            color: var(--primary-brand-color);
            font-weight: 600;
            text-decoration: underline;
            margin: 0 5px; /* Add space between links */
        }
        .registration-options span {
             margin: 0 5px; /* Add space around the separator */
        }
    </style>
</head>
<body>

    <%@ include file="header.jsp" %>
	
	<h3 style="color: white;text-align: center;"> ${msg} </h3> <%-- For messages after registration --%>

    <main>
        <div class="form-container">
            <h1>Login to ZenMart</h1>
            
            <c:if test="${param.error}">
                <div class="error-message">
                    Invalid username or password.
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/login" method="post">
                <%-- No CSRF token needed as it's disabled in SecurityConfig --%>
            
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required autofocus>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-submit">Login</button>
            </form>

            <%-- UPDATED: Registration options --%>
            <div class="registration-options">
                New here? 
                <a href="${pageContext.request.contextPath}/get-signup-page?role=USER">Register as Customer</a>
                <span>|</span>
                <a href="${pageContext.request.contextPath}/get-signup-page?role=SELLER">Register as Seller</a>
            </div>
            
            <%-- Removed the old footer link --%>
        </div>
    </main>

    <%@ include file="footer.jsp" %>

</body>
</html>
