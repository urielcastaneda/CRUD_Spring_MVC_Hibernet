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

		<h1>Registrar nuevo contacto</h1>
		<form action="procesarNuevoContacto2"   method="post">


			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="nombre">Nombre</label> <input type="text"
							class="form-control" id="nombre" name="nombre"
							placeholder="Ingrese el Nombre">

					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<div class="form-group">
						<label for="descripcion">Descripción</label>
						<textarea class="form-control" id="descripcion" name="descripcion"
							rows="3" placeholder="Ingrese la descripción"> </textarea>
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