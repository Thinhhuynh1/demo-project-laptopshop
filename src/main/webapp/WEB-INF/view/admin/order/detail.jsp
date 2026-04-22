<!doctype html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Order Detail</title>
        <link href="/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    </head>
    <body class="sb-nav-fixed">
        <jsp:include page="../layout/header.jsp" />
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <jsp:include page="../layout/sidebar.jsp" />
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Manage Order</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item">
                                <a href="/admin">Dashboard</a>
                            </li>
                            <li class="breadcrumb-item">
                                <a href="/admin/order">Order</a>
                            </li>
                            <li class="breadcrumb-item active">Detail</li>
                        </ol>
                    </div>
                    <div class="mt-5 mx-5">
                        <div class="row">
                            <div class="col-12 mx-auto">
                                <div class="d-flex justify-content-between align-items-center">
                                    <h3>Order detail with id = ${id}</h3>
                                    <a href="/admin/order" class="btn btn-secondary">Back</a>
                                </div>
                                <hr />
                                
                                <div class="table-responsive">
                                    <table class="table table-bordered align-middle">
                                        <thead>
                                            <tr>
                                                <th scope="col">Product</th>
                                                <th scope="col">Name</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">Quantity</th>
                                                <th scope="col">Subtotal</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="detail" items="${order.orderDetails}">
                                                <tr>
                                                    <td style="width: 120px;">
                                                        <img
                                                            src="/images/product/${detail.product.image}"
                                                            class="img-fluid rounded"
                                                            style="width: 80px; height: 80px; object-fit: contain;"
                                                            alt="${detail.product.name}">
                                                    </td>
                                                    <td>
                                                        <a href="/product/${detail.product.id}">
                                                            ${detail.product.name}
                                                        </a>
                                                    </td>
                                                    <td>
                                                        <fmt:formatNumber type="number" value="${detail.price}" /> đ
                                                    </td>
                                                    <td>${detail.quantity}</td>
                                                    <td>
                                                        <fmt:formatNumber type="number" value="${detail.price * detail.quantity}" /> đ
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <jsp:include page="../layout/footer.jsp" />
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/js/scripts.js"></script>
    </body>
</html>