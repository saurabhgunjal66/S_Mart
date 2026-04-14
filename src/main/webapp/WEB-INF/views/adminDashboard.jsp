<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="p-4">
<div class="container">
    <h1>Admin Dashboard</h1>
    <div class="row mt-4">
        <div class="col-md-4">
            <div class="card p-3">
                <h5>Total Customers</h5>
                <h2>${totalCustomers}</h2>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card p-3">
                <h5>Total Products</h5>
                <h2>${totalProducts}</h2>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card p-3">
                <h5>Total Orders</h5>
                <h2>${totalOrders}</h2>
            </div>
        </div>
    </div>
</div>
</body>
</html>
