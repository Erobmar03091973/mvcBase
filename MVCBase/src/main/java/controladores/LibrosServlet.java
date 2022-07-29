package controladores;

import java.io.IOException;
import java.util.List;

import dao.EditorialDAO;
import dao.EditorialDAOJDBC;
import dao.LibroDAO;
import dao.LibroDAOJDBC;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Editorial;
import modelo.Libro;

/**
 * Servlet implementation class LibrosServlet
 */

public class LibrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if (opcion ==null || opcion.equals("listado")) {
			mostrarListado(request,response);
		} else if (opcion.equals("nuevo")) {
			mostrarFormulario(request,response);
		} else if (opcion.equals("eliminar")) {
			eliminar(request,response);
		} else if (opcion.equals("editar")) {
			editar(request,response);
		}
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		LibroDAO dao = new LibroDAOJDBC();
		Libro l = dao.getLibro(isbn);
		request.setAttribute("libro", l);
		EditorialDAO daoEd = new EditorialDAOJDBC();
		List<Editorial> lista = daoEd.getListaEditoriales();
		request.setAttribute("listaEditoriales", lista);
		request.getRequestDispatcher("/libros/editar.jsp").forward(request, response);
	
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		LibroDAO dao = new LibroDAOJDBC();
		dao.eliminarLibro(isbn);
		mostrarListado(request, response);
	}

	private void mostrarFormulario(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
		EditorialDAO dao = new EditorialDAOJDBC();
		List<Editorial> lista = dao.getListaEditoriales();
		request.setAttribute("listaEditoriales", lista);
		request.getRequestDispatcher("/libros/nuevo.jsp").forward(request, response);
	}

	private void mostrarListado(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		LibroDAO dao = new LibroDAOJDBC();
		List<Libro> lista = dao.getListaLibros();
		request.setAttribute("listaLibros", lista);
		request.getRequestDispatcher("/libros/listado.jsp").forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if (opcion !=null && opcion.equals("insertar")) {
			insertarLibro(request,response);
		} else if (opcion!=null && opcion.equals("editar")) {
			actualizar(request,response);
		}
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		int codEditorial =Integer.parseInt(request.getParameter("codEditorial"));
		int año = Integer.parseInt(request.getParameter("anio"));
		int numPags = Integer.parseInt(request.getParameter("numPags"));
		float precio = Float.parseFloat(request.getParameter("precio"));
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		float precioCD = Float.parseFloat(request.getParameter("precioCD"));
		
		Libro l = new Libro(isbn, titulo, codEditorial, año, numPags, 
				            precio, cantidad, precioCD);
		
		LibroDAO dao = new LibroDAOJDBC();
		dao.editarLibro(l);
		mostrarListado(request, response);
		
	}

	private void insertarLibro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		int codEditorial =Integer.parseInt(request.getParameter("codEditorial"));
		int año = Integer.parseInt(request.getParameter("anio"));
		int numPags = Integer.parseInt(request.getParameter("numPags"));
		float precio = Float.parseFloat(request.getParameter("precio"));
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		float precioCD = Float.parseFloat(request.getParameter("precioCD"));
		
		Libro l = new Libro(isbn, titulo, codEditorial, año, numPags, 
				            precio, cantidad, precioCD);
		
		LibroDAO dao = new LibroDAOJDBC();
		dao.insertarLibro(l);
		mostrarListado(request, response);
		
	}

}
