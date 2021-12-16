<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="mb-3">
	<label for="type" class="col-form-label">Nombre</label> <input
		type="text" class="form-control" id="type" name="type"
		required>
</div>

<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>