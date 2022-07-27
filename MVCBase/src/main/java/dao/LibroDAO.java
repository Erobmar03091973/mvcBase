package dao;

import java.util.List;
import modelo.Libro;

public interface LibroDAO {

	public List<Libro> getListaLibros();
	public Libro getLibro(String isbn);
	public int insertarLibro(Libro libro);
	public int editarLibro(Libro libro);
	public int eliminarLibro(String isbn);
	
}
