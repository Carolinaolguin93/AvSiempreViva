<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/partials/header.jsp"></jsp:include>

	<main class="container">

		<c:if test="${type != null && type == ''}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al crear el tipo de atraccion.</p>
			</div>
		</c:if>

		<form action="/turismo/types/create.do" method="post">
			<jsp:include page="/views/types/form.jsp"></jsp:include>
		</form>
	</main>
		<jsp:include page="/partials/footer.jsp"></jsp:include>
	
</body>
</html>
