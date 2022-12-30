<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>
</head>
<body>


	<div class="container mt-3">

		<h1>Listado de Contactos</h1>
		<a href="nuevoContacto2" class="btn btn-primary"> Nuevo Contacto </a>
		<div class="row">

			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Nombre</th>
						<th scope="col">Descripción</th>
						<th scope="col">Editar</th>
						<th scope="col">Borrar</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cont" items="${listadoContactos}">
						<tr>
						    <td class="table-plus">${cont.id}</td>   						
							<td>${cont.nombre}</td>
							<td>${cont.descripcion}</td>
							<td><a href="editarContacto/${cont.id}" class="btn btn-warning">
									Editar </a></td>
							<td><a href="borrarContacto/${cont.id}" 
								   class="btn btn-danger"
								   onclick="return confirm('Estás seguro que deseas eliminar el registro?');"
								   > Borrar </a></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<a href="${pageContext.request.contextPath}/index" class="btn btn-primary"> Volver al inicio </a>		
	</div>
</body>
</html>