<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Products</title>
<link rel="stylesheet" href="../css/style.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 40px;
        }
        .product-img {
            width: 100px;
            height: 100px;
            object-fit: contain;
        }
        h2 {
            text-align: center;
            color: #0d6efd;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="product-grid">
	    <c:choose>
	        <c:when test="${not empty productList}">
	            <c:forEach var="product" items="${productList}">
	                <div class="product-card">
	                    <div class="product-image-container">
	                        <c:if test="${not empty product.imageUrl}">
	                            <img src="${product.imageUrl}" alt="${product.name}" class="product-img" />
	                        </c:if>
	                        <c:if test="${empty product.imageUrl}">
	                            <div class="no-image">No Image</div>
	                        </c:if>
	                    </div>
	                    <div class="product-info">
	                        <h3>${product.name}</h3>
	                        <p><strong>ID:</strong> ${product.id}</p>
	                        <p><strong>Price:</strong> ₹${product.price}</p>
	                        <p><strong>Quantity:</strong> ${product.quantity}</p>
	                        <p><strong>Category:</strong> ${product.category}</p>
	                    </div>
	                </div>
	            </c:forEach>
	        </c:when>
	        <c:otherwise>
	            <div class="no-products-message">No products found!</div>
	        </c:otherwise>
	    </c:choose>
	</div>

<jsp:include page="footer.jsp" />
</body>
</html>
