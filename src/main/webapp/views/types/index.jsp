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

		<div class="p-4 mb-3 rounded" style="background-color: #3a3a3a!important;">
			<h1 class="fuente-ls text-center">Tipos de atracciones</h1>
		</div>

		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/turismo/types/create.do" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nuevo Tipo
				</a>
			</div>
		</c:if>
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${types}" var="type">
					<tr>
						<td><strong><c:out value="${type}"></c:out></strong></td>
						<td><c:if test="${user.admin && (!tmp_user.admin || tmp_user.id == user.id)}">
								<a href="/turismo/types/edit.do?id=${type}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="/turismo/types/delete.do?id=${type}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>

</body>
</html>