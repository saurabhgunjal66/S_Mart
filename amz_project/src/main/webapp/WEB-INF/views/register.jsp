<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - AMZ-ShopMate</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>

    <!-- Include header -->
    <jsp:include page="header.jsp" />

    <main>
        <section class="form-container">
            <h2>Create Your Account</h2>
            

            <!-- Registration image -->
            <div class="login-image">
                <img src="${pageContext.request.contextPath}/assets/images/register.png" alt="Register Illustration">
            </div>

            <form action="/register" method="post">
                <div class="input-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>

                <div class="input-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div class="input-group">
                    <label for="mobile">Mobile Number</label>
                    <input type="tel" id="mobile" name="mobile" pattern="[0-9]{10}" placeholder="Enter 10-digit number" required>
                </div>

                <div class="input-group">
                    <label for="dob">Date of Birth</label>
                    <input type="date" id="dob" name="dob" required>
                </div>

                <div class="input-group">
                    <label for="address">Address</label>
                    <textarea id="address" name="address" rows="3" required></textarea>
                </div>

                <div class="input-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <div class="input-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required>
                </div>

                <button type="submit" class="btn">Sign Up</button>

                <p class="form-footer">
                    Already have an account?
                    <a href="${pageContext.request.contextPath}/get-loginPage">Login</a>
                </p>
            </form>
        </section>
    </main>

    <!-- Include footer -->
    <jsp:include page="footer.jsp" />

</body>
</html>
