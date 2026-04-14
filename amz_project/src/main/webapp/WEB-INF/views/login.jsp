<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - AMZ-ShopMate</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>

    <!-- Include header -->
    <jsp:include page="header.jsp" />

    <main>
        <section class="form-container">
            <h2>Login to AMZ-ShopMate</h2>

            <!-- Add image above the form -->
            <div class="login-image">
                <img src="${pageContext.request.contextPath}/assets/images/login.png" alt="Login Illustration">
            </div>

            <form action="login" method="post">
                <div class="input-group">
                    <label for="username">Username / Email</label>
                    <input type="text" id="username" name="username" required>
                </div>

                <div class="input-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <button type="submit" class="btn">Login</button>

                <p class="form-footer">
                    Don't have an account?
                    <a href="/get-signUpPage">Sign Up</a>
                </p>
            </form>
        </section>
    </main>

    <!-- Include footer -->
    <jsp:include page="footer.jsp" />

</body>
</html>
