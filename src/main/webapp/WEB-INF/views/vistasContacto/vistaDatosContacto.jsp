<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Vista Datos Contacto</title>
	</head>
	<body>
		<h1>Información de Contacto</h1>
		ID: ${contacto.id}<br>
		Nombre: ${contacto.nombre}<br>
		Descripción: ${contacto.descripcion}<br>

		
		<!-- 
		<br><br>		
		<a href="${pageContext.request.contextPath}/index">Volver al inicio</a>	
		 -->		
		
		<br><br>
		<input type="button" 
		value="Volver a Inicio" 
		onclick="window.location.href='${pageContext.request.contextPath}/index'; return false;"/>	
	</body>                             
</html>