<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Zenmart</title>
    <%-- Link to your main CSS file hi --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <%-- Include your standard header --%>
    <jsp:include page="header.jsp" />

    <main>
        <%-- Hero Section --%>
        <section class="hero-section">
            <h1 class="hero-title">Welcome to Zenmart!</h1>
            <p class="hero-subtitle">Your one-stop shop for amazing products at the best prices.</p>
            <%-- Button links to the shopping page --%>
            <a href="${pageContext.request.contextPath}/get-shop" class="btn btn-hero">Shop Now</a>
        </section>

        <%-- Features Section (Example) --%>
        <section class="features-section">
            <h2 class="section-title">Why Shop With Us?</h2>
            <div class="features-grid">
                <div class="feature-card">
                    <%-- Use context path for images --%>
                    <img src="../images/bestprice.jpg" alt="Best Price Icon" class="feature-icon"/>
                    <h3 class="feature-title">Best Price</h3>
                    <p>We source only the finest products for our customers @ Best Price.</p>
                </div>
                <div class="feature-card">
                    <img src="../images/fastdelivery.png" alt="Delivery Icon" class="feature-icon"/>
                    <h3 class="feature-title">Fast Delivery</h3>
                    <p>Get your orders delivered quickly and reliably.</p>
                </div>
                <div class="feature-card">
                    <img src="../images/s247.jpg" alt="Support Icon" class="feature-icon"/>
                    <h3 class="feature-title">Great Support</h3>
                    <p>Our customer support team is always here to help you.</p>
                </div>
            </div>
        </section>

    </main>

    <%-- Include your standard footer --%>
    <jsp:include page="footer.jsp" />

</body>
</html>

