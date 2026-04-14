<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 1. Updated JSTL URI --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%-- 2. Dynamic Title based on role parameter --%>
    <title>Register as ${param.role == 'SELLER' ? 'Seller' : 'Customer'} - ZenMart</title>
    <%-- 3. Corrected CSS link using contextPath --%>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

    <%@ include file="header.jsp" %>

    <main>
        <div class="form-container">
            <%-- 4. Dynamic Heading based on role parameter --%>
            <h1>Register as a ${param.role == 'SELLER' ? 'Seller' : 'Customer'}</h1>

            <%-- 5. Updated form action to point to /register --%>
            <form action="/register" method="post">
                <%-- CSRF Token (Uncomment if you re-enable CSRF) --%>
                <%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>

                <%-- 6. ADDED: Hidden field to capture and submit the role --%>
                <%-- Defaults to ROLE_USER if param.role is not 'SELLER' --%>
                <input type="hidden" name="role" value="${(param.role == 'SELLER') ? 'ROLE_SELLER' : 'ROLE_USER'}" />

                <%-- Existing form fields --%>
                <div class="form-group">
                    <label for="name">Full Name:</label>
                    <input type="text" id="name" name="name" required autofocus>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="mobile">Mobile:</label>
                    <input type="text" id="mobile" name="mobile" required>
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" required>
                </div>

                <button type="submit" class="btn btn-submit">Create Account</button>
            </form>

            <div class="form-footer-link">
                <%-- 7. Updated login link using contextPath --%>
                Already have an account? <a href="/login">Login here</a>
            </div>
        </div>
    </main>

    <%@ include file="footer.jsp" %>

</body>
</html>

