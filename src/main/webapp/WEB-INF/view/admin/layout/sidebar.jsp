<%@page contentType="text/html" pageEncoding="UTF-8"%><%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
  <div class="sb-sidenav-menu">
    <div class="nav">
      <div class="sb-sidenav-menu-heading">Features</div>
      <a class="nav-link" href="<c:url value='/admin' />">
        <div class="sb-nav-link-icon">
          <i class="fas fa-tachometer-alt"></i>
        </div>
        Dashboard
      </a>

      <a class="nav-link" href="<c:url value='/admin/user' />"
        ><div class="sb-nav-link-icon">
          <i class="fas fa-tachometer-alt"></i>
        </div>
        User</a
      >
      <a class="nav-link" href="<c:url value='/admin/product' />"
        ><div class="sb-nav-link-icon">
          <i class="fas fa-tachometer-alt"></i>
        </div>
        Product
      </a>

      <a class="nav-link" href="<c:url value='/admin/order' />">
        <div class="sb-nav-link-icon">
          <i class="fas fa-tachometer-alt"></i>
        </div>
        Order
      </a>
    </div>
  </div>
  <div class="sb-sidenav-footer">
    <div class="small">Logged in as:</div>
    Thinh Huynh
  </div>
</nav>
