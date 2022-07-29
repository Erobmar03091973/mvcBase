<%@page import="modelo.Editorial"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<jsp:include page="../plantillas/cabecera.jsp"></jsp:include>

<h2>Inserta el nuevo libro</h2>

<%
	List<Editorial> listaEditoriales = (List<Editorial>)
	              request.getAttribute("listaEditoriales");
%>

<form action="libros" method="post">
	<input type="hidden" name="opcion" value="insertar" />
	
	<div>
		<label for="isbn">ISBN: </label>
		<input type="text" name="isbn" id="isbn"  />
	</div>
	
	<div>
		<label for="titulo">Título:</label>
		<input type="text" name="titulo" id="titulo" />
	</div>
	<div>
		<label for="codEditorial">Editorial:</label>
		<select name="codEditorial" id="codEditorial">
			<% for (Editorial e: listaEditoriales) { %>
			<option value="<%=e.getCodEditorial()%>"><%=e.getNombre() %></option>
			<%} %>
			
		</select>
	</div>
	<div>
		<label for="año">Año:</label>
		<input type="number" value="2020" min="1900" name="anio" id="año" />
	</div>
	<div>
		<label for="numPags">Núm. Páginas:</label>
		<input type="text" name="numPags" id="numPags" />
	</div>
	<div>
		<label for="precio">Precio:</label>
		<input type="text" name="precio" id="precio"/>
	</div>
	<div>
		<label for="cantidad">Cantidad: </label>
		<input type="number" value="1" min="1" name="cantidad" id="cantidad"/>
	</div>
	<div>
		<label for="precioCD">Precio CD:</label>
		<input type="text" name="precioCD" id="precioCD" />
	</div>
	<input type="submit" value="Insertar"/>
</form>



<jsp:include page="../plantillas/pie.jsp"></jsp:include>