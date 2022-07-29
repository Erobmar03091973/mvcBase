<%@page import="modelo.Libro"%>
<%@page import="modelo.Editorial"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<jsp:include page="../plantillas/cabecera.jsp"></jsp:include>

<h2>Edita un libro</h2>

<%
	List<Editorial> listaEditoriales = (List<Editorial>)
	              request.getAttribute("listaEditoriales");

    Libro l = (Libro) request.getAttribute("libro");
    System.out.println(l);
%>

<form action="libros" method="post">
	<input type="hidden" name="opcion" value="editar" />
	
	<div>
		<label for="isbn">ISBN: </label>
		<input type="text" name="isbn" id="isbn" value="<%=l.getIsbn() %>" readonly />
	</div>
	
	<div>
		<label for="titulo">Título:</label>
		<input type="text" name="titulo" id="titulo" value="<%=l.getTitulo() %>" />
	</div>
	<div>
		<label for="codEditorial">Editorial:</label>
		<select name="codEditorial" id="codEditorial">
			<% for (Editorial e: listaEditoriales) { 
				if (e.getCodEditorial()==l.getCodEditorial()){
					out.println("<option value='"+e.getCodEditorial()+"' selected>"
					            +e.getNombre()+ "</option>" );
				} else {
					out.println("<option value='"+e.getCodEditorial()+"'>"
				            +e.getNombre()+ "</option>" );
				}
			}
			%>
			
			
		</select>
	</div>
	<div>
		<label for="año">Año:</label>
		<input type="number" value="<%=l.getAño() %>" min="1900" name="anio" id="año" />
	</div>
	<div>
		<label for="numPags">Núm. Páginas:</label>
		<input type="text" name="numPags" id="numPags" value="<%=l.getNumPags()%>"/>
	</div>
	<div>
		<label for="precio">Precio:</label>
		<input type="text" name="precio" id="precio" value="<%=l.getPrecio() %>"/>
	</div>
	<div>
		<label for="cantidad">Cantidad: </label>
		<input type="number" value="<%=l.getCantidad() %>" min="1" name="cantidad" id="cantidad"/>
	</div>
	<div>
		<label for="precioCD">Precio CD:</label>
		<input type="text" name="precioCD" id="precioCD"  value="<%=l.getPrecioCD()%>"/>
	</div>
	<input type="submit" value="Insertar"/>
</form>



<jsp:include page="../plantillas/pie.jsp"></jsp:include>