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

		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
					<c:if test="${errors != null}">
						<ul>
							<c:forEach items="${errors}" var="entry">
								<li><c:out value="${entry.getValue()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
				</p>
			</div>
		</c:if>

		<div class="bg-light p-4 mb-3 rounded">
			<h1>Estas son las Promociones de la Tierra Media</h1>
		</div>
		
		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/turismo/promotions/create.do" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nueva Promocion
				</a>
			</div>
		</c:if>		
		
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Promociones</th>
					<th>Costo</th>
					<th>Duraci&oacute;n</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${promotions}" var="promotion">
					<tr>
						<td><strong><c:out value="${promotion.name}"></c:out></strong>
						<c:forEach items="${promotion.attractions}" var="attractions">
							<p>Incluye la atraccion: <c:out value="${attractions.name}">.</c:out></p></td>
						</c:forEach>
						<td><c:out value="${promotion.cost}"></c:out></td>
						<td><c:out value="${promotion.duration}"></c:out></td>
						<td><c:if test="${user.admin}">
								<a href="/turismo/promotions/edit.do?id=${promotion.id}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="/turismo/promotions/delete.do?id=${promotion.id}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if> <c:choose>

								<c:when
									test="${user.canAfford(promotion) && user.canAttend(promotion) && attraction.canHost() && (!itinerario.contains(promotion))}">
									<a href="/turismo/promotions/buy.do?id=${promotion.id}"
										class="btn btn-success rounded" role="button">Comprar</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="btn btn-secondary rounded disabled"
										role="button">No se puede comprar</a>
								</c:otherwise>
							</c:choose></td> 
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>

</body>
</html>