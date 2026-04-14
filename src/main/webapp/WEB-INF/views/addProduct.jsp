<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>
    <title>Add New Product</title>
    <%-- General styles for header/footer --%>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

    <!-- Include Header -->
    <jsp:include page="header.jsp" />

    <main>
        <div class="form-container">
            <h2>Add a New Product</h2>

            <%-- This form will send a POST request to your server --%>
            <form action="/add-product" method="POST">

                <div class="form-group">
                    <label for="name">Product Name</label>
                    <input type="text" id="name" name="name" required>
                </div>

                <div class="form-group">
                    <label for="price">Price (₹)</label>
                    <input type="number" id="price" name="price" min="0" step="0.01" required>
                </div>

                <div class="form-group">
                    <label for="quantity">Quantity in Stock</label>
                    <input type="number" id="quantity" name="quantity" min="0" required>
                </div>

                <div class="form-group">
                    <label for="category">Category</label>
                    <input type="text" id="category" name="category" required>
                </div>

                <div class="form-group">
                    <label for="imageUrl">Image URL</label>
                    <input type="url" id="imageUrl" name="imageUrl" placeholder="https://example.com/image.jpg">
                </div>

                <button type="submit" class="btn-submit">Add Product</button>

            </form>
        </div>
    </main>

    <!-- Include Footer -->
    <jsp:include page="footer.jsp" />

</body>
</html>
