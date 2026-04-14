<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 1. Updated JSTL URIs to the modern 'jakarta' version --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Shop All Products - Zenmart</title>
    <%-- 2. Removed all internal CSS (<style> block) --%>
    <%-- 3. Added link to the external CSS file. Make sure your file is at 'src/main/resources/static/css/style.css' --%>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <main>
        <div class="shop-header">
            <c:if test="${not empty successMessage}">
                <h3 class="success-message">${successMessage}</h3>
            </c:if>
            <h2 class="page-title">Explore Our Collection</h2>
        </div>

        <%-- 4. Removed the incorrect <form> that used to wrap the entire grid --%>
        <div class="product-grid">
            
            <c:forEach var="product" items="${productList}">
                <div class="product-card">
                    
                    <c:if test="${not empty product.imageUrl}">
                        <img src="${product.imageUrl}" alt="${product.name}" class="product-img">
                    </c:if>
                    
                    <%-- This info div will grow to push the button to the bottom --%>
                    <div class="product-info">
                        <p class="product-category">${product.category}</p>
                        <h3 class="product-name">${product.name}</h3>
                        <p class="product-price">
                            <fmt:setLocale value="en_IN"/>
                            <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₹"/>
                        </p>
                    </div>

                    <%-- 5. Each product has its own form for "Add to Cart" --%>
                    <div class="product-action">
                        <form action="/add-to-cart" method="post">
                            <%-- 6. CRITICAL: Added CSRF token, which is required by Spring Security --%>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="hidden" name="quantity" value="1">
                            <button type="submit" class="btn btn-add-to-cart">Add to Cart</button>
                        </form>
                    </div>
                </div>
            </c:forEach>

        </div>
        
        <%-- 7. As requested: A single "Shop Now" button at the bottom of the page --%>
        <div class="checkout-container">
            <a href="/checkout" class="btn btn-checkout">
                Proceed to Checkout
            </a>
        </div>
    </main>

    <jsp:include page="footer.jsp" />

</body>
</html>