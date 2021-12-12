<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
</head>
<body class="body-index">

	<jsp:include page="partials/header.jsp"></jsp:include>

<div class="row justify-content-center bg-video m-0 p-0">
    <video autoplay loop playsinline muted class="col-lg-9 col-md-12">
      <source src="assets/img/video-parque.mp4" type="video/mp4">
    </video>
  </div>
  <div class="container-fluid texto-principal">
    <section class="pt-5 text-center w-75 m-auto">
      <h1 class="fs-2">Vení a divertirte y disfrutar de un dia sorprendente e inolvidable junto a tu <b>familia y
          amigos.</b></h1>
      <p class="fs-4">
        Si te gusta Explorar, sos un apasionado de lo Extremo, y un amante de la Gastronomia, <br> <b><em>Av Siempre
            Viva es
            tu lugar!</em></b>.
      </p>
    </section>
  </div>

  <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
        aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
        aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
        aria-label="Slide 3" id="mapa"></button>
    </div>
    <div class="carousel-inner m-carrousel">
      <div class="carousel-item active">
       <a href="/turismo/attractions/index.do">
        <img src="assets/img/montañarusa.png" class="d-block w-75 mx-auto carrusel">
        </a>
      </div>
      <div class="carousel-item">
       <a href="/turismo/attractions/index.do">
        <img src="assets/img/Gastronomia.png" class="d-block w-75 mx-auto carrusel">
        </a>
      </div>
      <div class="carousel-item">
      <a href="/turismo/attractions/index.do">
        <img src="assets/img/Trekking.png" class="d-block w-75 mx-auto carrusel">
        </a>
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
      data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
      data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>

  <div class="clearfix w-75 bg-mapa" >
    <section class="text-center col-12">
      <h1 class="pt-3 fs-2 mb-4 "><b>Mapa Del Parque.</b></h1>
    </section>
    <img src="assets/img/mapa-parque.jpg" width:100% class="col-xl-11 col-xxl-10  col-lg-12 float-lg-end mb-3 mapa-parque"
      alt="...">
    <ol class="pb-4 fs-6">
      <span class="text-decoration-underline fw-bold">Aventura:</span>
      <li>El Monorriel</li>
      <li>La Tierra De Tomy Y Daly</li>
      <li>Penitenciaria De Springfield</li>
      <span class="text-decoration-underline fw-bold">Paseo:</span>
      <li>La Fabrica De Cajas</li>
      <li>Museo Jeremias Springfield</li>
      <li>La Planta Nuclear </li>
      <li>Monte Springfield</li>
      <span class="text-decoration-underline fw-bold">Gastronomia:</span>
      <li>Kwik-E-Mart</li>
      <li>Taberna De Moe</li>
      <li>Krusty Burguer</li>
      <hr>
      <li>Baños</li>
      <li>Estacionamiento</li>
      <li>Sector gastronómico</li>
      <li>Guardarropa</li>
    </ol>
  </div>

  <section class="d-flex flex-column justify-content-center align-items-center text-center">
    <h1 class="p-3 fs-2"><b>Preguntas Frecuentes.</b></h1>
  </section>

  </div class="acordeon bg-acordion">
  <div class="accordion accordion-flush" id="accordionFlushExample">
    <div class="accordion-item">
      <h2 class="accordion-header" id="flush-headingOne">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
          data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
          ¿Se puede ingresar con comida y bebidas?
        </button>
      </h2>
      <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne"
        data-bs-parent="#accordionFlushExample">
        <div class="accordion-body">Está permitido el ingreso con alimentos y bebidas, pero no es posible ingresar con botellas de vidrio, vajilla de loza o latas. Cuidamos a grandes y chicos de cualquier tipo de rotura que puedan sufrir esos materiales, pudiendo ocasionar un riesgo en un visitante.
        </div>
      </div>
    </div>
    <div class="accordion-item">
      <h2 class="accordion-header" id="flush-headingTwo">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
          data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
          ¿Que pasa si llueve?
        </button>
      </h2>
      <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo"
        data-bs-parent="#accordionFlushExample">
        <div class="accordion-body">Todos los Pasaportes cuentan con seguro de lluvia, si llueve 1 hora antes del horario de cierre de los juegos dentro de tu turno volves gratis una vez mas dentro de los 30 días.


        </div>
      </div>
    </div>
    <div class="accordion-item">
      <h2 class="accordion-header" id="flush-headingThree">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
          data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
          ¿Tienen Estacionamiento?
        </button>
      </h2>
      <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree"
        data-bs-parent="#accordionFlushExample">
        <div class="accordion-body">Hay una importante playa de estacionamiento antes de ingresar al parque, cuidada por personal idóneo.


      </div>
    </div>
  </div>

	<jsp:include page="partials/footer.jsp"></jsp:include>

</body>
</html>
