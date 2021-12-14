<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="name" name="name"
			required value="${promotion.name}">
	</div>
	<div class="mb-3">
		<label for="type"
			class='col-form-label'>tipo:</label>
		<input class="form-control" type="text" id="type" name="type"
			required value="${promotion.type}"></input>
	</div>
	<div class="mb-3">
		<label for="attraction"
			class='col-form-label'>Atracción 1:</label>
		<select class="form-select" aria-label="Default select example" id="attraction1" name="attraction1" required>
		<option selected>Seleccionar Atracción 1</option>
		<c:forEach items="${attractions}" var="attraction">
 			<option value="<c:out value="${attraction.id}"></c:out>"><c:out value="${attraction.name}"></c:out></option>
 		</c:forEach>
		</select>
	</div>
	<div class="mb-3">
		<label for="attraction"
			class='col-form-label'>Atracción 2:</label>
		<select class="form-select" aria-label="Default select example" id="attraction2" name="attraction2" required>
		<option selected>Seleccionar Atracción 2</option>
		<c:forEach items="${attractions}" var="attraction">
 			<option value="<c:out value="${attraction.id}"></c:out>"><c:out value="${attraction.name}"></c:out></option>
 		</c:forEach>
		</select>
	</div>
	<div class="mb-3">
		<label for="attraction"
			class='col-form-label'>Atracción 3:</label>
		<select class="form-select" aria-label="Default select example" id="attraction3" name="attraction3" required>
		<option selected>Seleccionar Atracción 3</option>
		<c:forEach items="${attractions}" var="attraction">
 			<option value="<c:out value="${attraction.id}"></c:out>"><c:out value="${attraction.name}"></c:out></option>
 		</c:forEach>
		</select>
	</div>

</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
