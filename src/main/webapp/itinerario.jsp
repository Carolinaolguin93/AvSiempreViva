<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import = "model.User" %>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/partials/header.jsp"></jsp:include>

	<main class="container">

		<div class="bg-light p-4 mb-3 rounded">
			<h1>Mis compras</h1>
		</div>

		<c:if test="${itinerario.isEmpty()}">
								<p> NO HAY COMPRAS REALIZADAS</p>
		
						</c:if>	
		<c:if test="${!itinerario.isEmpty()}">
		
		<table class="table table-stripped table-hover nowrap" style="width:100%">
			<thead>
				<tr>
					<th>Actividades</th>
					<th>Costo</th>
					<th>Duración</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${itinerario}" var="atr">
					<tr>
						<td><strong><c:out value="${atr.name}"></c:out></strong></td>
						<td><c:out value="${atr.cost}"></c:out></td>
						<td><c:out value="${atr.duration}"></c:out></td>
					</tr>
			</c:forEach>		
			</tbody>
		</table>
	</c:if>	

	</main>

</body>
</html>