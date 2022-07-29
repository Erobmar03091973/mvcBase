<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Libro" %>
<%@page import="java.util.List" %>
<jsp:include page="../plantillas/cabecera.jsp"></jsp:include>

<a href="?opcion=nuevo">Insertar libro</a>

<h2>Listado de Libros</h2>

<% List<Libro> lista = (List<Libro>) request.getAttribute("listaLibros");
	if (lista==null || lista.size()==0) {
%>
	<h3>No se han encontrado libros</h3>
	
<%
	} else {
		%>
		<table class="estilo-tabla">
		<tr>
			<th>ISBN</th>
			<th>Título</th>
			<th>Cod. Editorial</th>
			<th>Año</th>
			<th>Núm Páginas</th>
			<th>Precio</th>
			<th>Cantidad</th>
			<th>PrecioCD</th>
			<th>Eliminar</th>
			<th>Editar</th>
		</tr>
		<%
		for(Libro l : lista) {
			%>
			<tr>
				<td><%=l.getIsbn() %></td>
				<td><%=l.getTitulo() %></td>
				<td><%=l.getCodEditorial() %></td>
				<td><%=l.getAño() %></td>
				<td><%=l.getNumPags() %></td>
				<td><%=l.getPrecio() %></td>
				<td><%=l.getCantidad() %></td>
				<td><%=l.getPrecioCD() %></td>
				<td><a href="?opcion=eliminar&isbn=<%=l.getIsbn() %>">X</a></td>
				<td><a href="?opcion=editar&isbn=<%=l.getIsbn() %>">Editar</a></td>
			</tr>
			<%
		}
		%>
		</table>
		<%
	}
%>

<jsp:include page="../plantillas/pie.jsp"></jsp:include>