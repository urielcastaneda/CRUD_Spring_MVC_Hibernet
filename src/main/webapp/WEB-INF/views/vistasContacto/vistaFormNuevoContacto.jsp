<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Taglib para las etiquetas de spring con prefijo form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registrar Contacto</title>
	</head>
	<body>
		<h1>Registrar nuevo Contacto</h1>
		<!-- https://www.javatpoint.com/spring-mvc-form-tag-library -->
		<form:form action="procesarNuevoContacto" modelAttribute="contacto" method="POST">

			<!-- ID: <form:input path="id"/><br>       (no aplica por ser el formulario de Alta) -->
			
			<!-- Se Incluyen los mensaje de ERROR de validación inyectados con Hibernate en el modelo -->
			Nombre: <form:input path="nombre"/> <form:errors path="nombre" style="color:red" /><br>
			descripcion: <form:input path="descripcion"/> <form:errors path="descripcion" style="color:red" /><br>
	
				
			<!-- Al enviar, las MVC tags llaman a los setters -->
			<input type="submit" value="Guardar">
		</form:form>
		
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