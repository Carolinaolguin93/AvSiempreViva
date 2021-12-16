<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal-body">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="name" name="name"
			required value="${promotion.name}">
	</div>
	
	<div>
<label for="type">Selecciona el tipo:</label>
	<select class="form-select" aria-label="Default select example" id="type" name="type" required>
    <option></option>
  <option value="Gastronomia">Gastronomia</option>
  <option value="Aventura">Aventura</option>
  <option value="Visita_Guiada">Paseo *debe contener 3 atracciones</option>
</select>
</div>
<br>
	<div>
	<p>Selecciona las atracciones que incluye:
<c:forEach items="${attractions}" var="attraction">	
<div class="form-check">

<c:choose>
<c:when test="${promotion.idAttractions.contains(attraction.id)}">
  <input class="form-check-input" type="checkbox" name="attractions" value="${attraction.id}" id="flexCheckDefault" checked>
</c:when>
<c:otherwise>
  <input class="form-check-input" type="checkbox" name="attractions" value="${attraction.id}" id="flexCheckDefault">
</c:otherwise>
</c:choose>
<label class="form-check-label" for="flexCheckDefault">

<c:out value="${attraction.name}"></c:out>     		
  </label>
    </div>
  </c:forEach>
      </p>
  </div>
  	<div>
<label for="typePromo">Selecciona el tipo:</label>
	<select class="form-select" aria-label="Default select example" id="typePromo" name="typePromo" required>
    <option></option>
  <option value="Absoluta">Promo absoluta</option>
  <option value="TresPorDos">Promo tres por dos</option>
  <option value="Porcentual">Promo porcentual</option>
</select>
</div>
<br>
	<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
