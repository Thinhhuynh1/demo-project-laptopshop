<!doctype html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Product - Thinh Huynh</title>
    <link href="/css/styles.css" rel="stylesheet" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
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
            <h1 class="mt-4">Manage Orders</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <a href="/admin">Dashboard</a>
              </li>
              <li class="breadcrumb-item active">Order</li>
            </ol>
          </div>
          <div class="container mt-5">
            <div class="row">
              <div class="col-md-6 col-12 mx-auto">
                <form:form method="post" action="/admin/order/update" modelAttribute="updateOrder" class="row" enctype="multipart/form-data">
                  <h1>Update a order</h1>
                  <hr />
                  <form:hidden path="id" />
                  <div class="md-3 col-12 col-md-6">
                    <label class="form-label">Receiver: ${updateOrder.receiverName}</label>
                  </div>
                  <div class="md-3 col-12 col-md-6">
                    <label class="form-label">Price: 
                      <fmt:formatNumber type="number" value="${updateOrder.totalPrice}" /> đ
                    </label>
                  </div>
                  
                  <div class="md-3 col-12 col-md-6">
                    <label for="" class="form-label">User:</label>
                    <input type="text" value="${updateOrder.user.fullName}" disabled>
                  </div>

                  <div class="mb-3 col-12 col-md-6">
                    <label class="form-label">Status</label>
                    <form:select class="form-select" path="status">
                      <form:option value="PENDING">PENDING</form:option>
                      <form:option value="SHIPPING">SHIPPING</form:option>
                      <form:option value="COMPLETE">COMPLETE</form:option>
                      <form:option value="CANCEL">CANCEL</form:option>
                    </form:select>
                  </div>

                <button type="submit" class="btn btn-warning">Update</button>
              </form:form>
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
