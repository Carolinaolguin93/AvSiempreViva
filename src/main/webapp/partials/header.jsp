<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

  <header class="sticky-top">
    <nav class="navbar navbar-expand-md navbar-light py-2 " id="menu">
      <div class="container">
        <a class="navbar-brand" href="/turismo/index.jsp">
          <img src="/turismo/assets/img/logo-prueba.png" width="120px">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link menu-item" aria-current="page" href="/turismo/index.jsp">Inicio</a>
            </li>
            <li class="nav-item">
              <a class="nav-link menu-item" href="/turismo/index.jsp#mapa">Ver Mapa</a>
            </li>
            <li class="nav-item">
              <a class="nav-link menu-item" href="/turismo/attractions/index.do">Atracciones</a>
            </li>
            <li class="nav-item">
              <a class="nav-link menu-item" href="/turismo/promotions/index.do">Promociones</a>
            </li>
            <li class="nav-item">
              <a class="nav-link menu-item" href="/turismo/users/index.do">Usuarios</a>
            </li>
          </ul>
          <ul class="navbar-nav">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle menu-item" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              <c:out value="${user.username}"></c:out>
            </a>
            <ul class="dropdown-menu dropdown-menu-end"
              aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item disabled">
                <i title="monedas" style="color: #eb76ae; font-size:23px" class="bi-currency-exchange"></i> <c:out value="${user.coins}"></c:out>
              </a></li>
              <li><a class="dropdown-item disabled">
                <i title="tiempo" style="color: #eb76ae; font-size:23px" class="bi bi-clock-fill"></i> <c:out value="${user.time}"></c:out>
              </a></li>
              <li><a href="/turismo/itinerario.do" class="dropdown-item">
                <i title="itinerario"></i> Mis Compras
              </a></li>
              <li><hr class="dropdown-divider"></li>
              <li><a href="/turismo/logout" class="dropdown-item">Salir</a></li>
            </ul>
          </li>
        </ul>

        
        </div>
      </div>
    </nav>
  </header>