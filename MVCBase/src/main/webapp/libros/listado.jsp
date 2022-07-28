<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Libro" %>
<%@page import="java.util.List" %>
<jsp:include page="../plantillas/cabecera.jsp"></jsp:include>

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
			<th>T�tulo</th>
			<th>Cod. Editorial</th>
			<th>A�o</th>
			<th>N�m P�ginas</th>
			<th>Precio</th>
			<th>Cantidad</th>
			<th>PrecioCD</th>
		</tr>
		<%
		for(Libro l : lista) {
			%>
			<tr>
				<td><%=l.getIsbn() %></td>
				<td><%=l.getTitulo() %></td>
				<td><%=l.getCodEditorial() %></td>
				<td><%=l.getA�o() %></td>
				<td><%=l.getNumPags() %></td>
				<td><%=l.getPrecio() %></td>
				<td><%=l.getCantidad() %></td>
				<td><%=l.getPrecioCD() %></td>
			</tr>
			<%
		}
		%>
		</table>
		<%
	}
%>

<jsp:include page="../plantillas/pie.jsp"></jsp:include>