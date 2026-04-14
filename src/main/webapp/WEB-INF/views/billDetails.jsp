<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ZenMart -Bill Details</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
	<link rel="stylesheet" href="../css/print.css" media="print">
</head>
<body>
	<jsp:include page="header.jsp" />
    <h2 style="text-align: center; color: white;">Shopping Bill Details</h2>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
            <tr>
                <th>S. No.</th>
                <th>Product Name</th>
                <th>Price (Rs.)</th>
                <th>Quantity</th>
                <th>Subtotal (Rs.)</th>
            </tr>
        </thead>
        <tbody>
            <%-- Initialize the grandTotal variable to 0 --%>
            <c:set var="grandTotal" value="0"/>

            <%-- Loop through each cart item --%>
            <c:forEach var="item" items="${cartItems}" varStatus="status">
                <tr>
                    <td><c:out value="${status.index + 1}"/></td>
                    <td><c:out value="${item.product.name}"/></td>
                    <td><c:out value="${item.product.price}"/></td>
                    <td><c:out value="${item.quantity}"/></td>
                    <td>
                        <%-- Calculate and display the subtotal for this item --%>
                        <c:set var="subtotal" value="${item.product.price * item.quantity}"/>
                        <c:out value="${subtotal}"/>
                        
                        <%-- Add the subtotal to the running grand total --%>
                        <c:set var="grandTotal" value="${grandTotal + subtotal}"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="4" style="text-align: right;"><strong>Total Amount (Rs.):</strong></td>
                <td><strong><c:out value="${grandTotal}"/></strong></td>
            </tr>
        </tfoot>
    </table>

	<%-- Container for the action buttons --%>
	    <div class="bill-actions">
	        <%-- Link to go back to shopping --%>
	        <a href="/get-shop" class="btn btn-secondary">Continue Shopping</a>
	        
	        <%-- Button to trigger browser printing --%>
	        <button onclick="window.print();" class="btn btn-print">Print Bill</button>
	        
	        <%-- Link/Button to trigger emailing the bill (points to a controller endpoint) --%>
	        <%-- You'll need to create a @GetMapping("/email-bill") in a controller --%>
	        <a href="/email-bill" class="btn btn-email">Email Bill</a>
	    </div>
	<jsp:include page="footer.jsp" />
</body>
</html>