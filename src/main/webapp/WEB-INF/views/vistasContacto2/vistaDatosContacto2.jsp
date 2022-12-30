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

		<h1>Datos Contacto registrado</h1>
		<form action="${pageContext.request.contextPath }/contacto2/ReporteContactos" method="post">

			<div class="row">
				
				ID: ${contacto.id}<br>
				Nombre: ${contacto.nombre}<br>
				Descripción: ${contacto.descripcion}<br>

			</div>

			<button type="submit" class="btn btn-primary">Regresar</button>
		</form>

	</div>

</body>
</html>

