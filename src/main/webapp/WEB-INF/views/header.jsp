<%-- 1. Add the Spring Security taglib --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header class="header">
    <div class="header-content">
        <%-- Logo and Title Link --%>
        <a href="/" class="logo-link">
            <img src="../images/zenmartlogo.png" alt="ZenMart Logo" class="header-logo"/>
            <h1 class="header-title">Zenmart</h1>
        </a>

        <%-- Navigation Links --%>
        <nav class="header-nav">
            <%-- === Common Links (Always Visible) === --%>
            <a href="/">Home</a>

            <%-- === Links Visible to Anonymous and Customers (USER) === --%>
            <%-- Show "Shop" unless the user is a SELLER or ADMIN --%>
            <sec:authorize access="!hasAnyRole('SELLER', 'ADMIN')">
                 <a href="/get-shop">Shop</a>
            </sec:authorize>

            <%-- === SELLER & ADMIN Links === --%>
            <sec:authorize access="hasAnyRole('SELLER', 'ADMIN')">
                <a href="/add-product">Add Product</a>
                <%-- FIX: Moved these links here so Sellers can see them too --%>
                <a href="/get-all-products">View All Products</a>
                <a href="/get-all-customer">View All Customers</a>
            </sec:authorize>

            <%-- === ADMIN ONLY Links === --%>
            <%-- If you have links ONLY for ADMIN, put them here --%>
            <%-- Example: <sec:authorize access="hasRole('ADMIN')"> <a href="/admin/dashboard">Admin Dashboard</a> </sec:authorize> --%>


            <%-- === Authenticated User Links (Visible ONLY to logged-in users) === --%>
            <sec:authorize access="isAuthenticated()">
                <%-- Logout Form --%>
                <form action="/logout" method="post" style="display: inline;">
                     <button type="submit" class="btn-link-style">Logout</button>
                </form>
            </sec:authorize>

            <%-- === Anonymous User Links (Visible ONLY if NOT logged in) === --%>
            <sec:authorize access="!isAuthenticated()">
                <%-- Link to the standard login page --%>
                <a href="/login" class="btn btn-secondary">Login</a>
                <%-- Registration links are now on the login page --%>
            </sec:authorize>
        </nav>
    </div>
</header>

