<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		
		<!-- Eliminar estas 4 líneas para PRODUCCIÓN -->
		<!-- Con ellas evitamos que el CACHÉ IMPIDA la actualización de estilos -->
		  <meta http-equiv="Expires" content="0">
		  <meta http-equiv="Last-Modified" content="0">
		  <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
		  <meta http-equiv="Pragma" content="no-cache">
		
				
		<title>Vista Listado Contactos</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/ccs/TablaCSS1.css">
	</head>
	<body>
		<h1>Vista Listado de Contactos</h1>
		<table>
			<tr>
				<th>ID</th>
				<th>Nombre</th>
				<th>Descripción</th>

			</tr>

			<c:forEach var="u" items="${listadoContactos}">
				<tr>
					<td>${u.id}</td>
					<td>${u.nombre}</td>
					<td>${u.descripcion}</td>

				</tr>
			</c:forEach>
			
		</table>
		
		<br><br>
		<input type="button" 
		value="Volver a Inicio" 
		onclick="window.location.href='${pageContext.request.contextPath}/index'; return false;"/>		
	</body>
</html>