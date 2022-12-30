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

		<h1>Edición de Contactos</h1>
		
		<form action="procesarEdicionContacto2" method="post">

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="id">Id</label> <input type="text"
							value="${contacto.id}" class="form-control" id="id" name="id"
							readonly="readonly">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="nombre">Nombre</label> <input type="text"
							value="${contacto.nombre }" class="form-control" id="nombre"
							name="nombre" placeholder="Ingrese el Nombre">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="descripcion">Descripcion</label>
						<textarea class="form-control" id="descripcion" name="descripcion"
							rows="3" placeholder="Ingrese Descripción"> ${contacto.descripcion } </textarea>
					</div>
				</div>
			</div>

			<a href="${pageContext.request.contextPath }/"
				class="btn btn-warning"> Regresar a inicio </a>
			<button type="submit" class="btn btn-primary">Guardar</button>
		</form>

	</div>

</body>
</html>