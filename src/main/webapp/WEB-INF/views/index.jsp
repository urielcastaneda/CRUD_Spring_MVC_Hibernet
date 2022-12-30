<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		
		 
		<title>CRUD Spring MVC</title>
		 
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/ccs/StyleCSS1.css">

	</head>
	<body>
		<h1>Prueba Técnica de programación</h1>
		<br>
		<a href="contacto/listadoContactos">Consulta general </a>
		<br>
		<a href="contacto/consultarPorID">Buscar contacto por ID</a>
		<br>
		<a href="contacto/consultarPorNombre">Buscar contacto por Nombre</a>
		<br>
		<a href="contacto/nuevoContacto">Dar de alta contacto</a>	
		<br><br>
		<a href="contacto2/ReporteContactos">Consulta (estilo bootstrap) + CRUD</a>		
		<br><br>		
		
		<br><br>
		<!-- ${pageContext.request.contextPath} contiene la ruta de la carpeta WebContent, en la cual se alojan los RECURSOS -->
		<img alt="ejemplo JAVA WEB Spring" src="${pageContext.request.contextPath}/resources/img/cintaComponentes.png" style="width:752px;height:200px;"> 
										    
	</body>
</html>
