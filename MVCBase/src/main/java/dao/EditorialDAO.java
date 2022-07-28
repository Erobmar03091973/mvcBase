package dao;

import java.util.List;

import modelo.Editorial;

public interface EditorialDAO {

	public List<Editorial> getListaEditoriales();
	public Editorial getEditorial(int codEditorial);
	public int insertarEditorial(Editorial ed);
	public int editarEditorial(Editorial ed);
	public int eliminarEditorial(int codEditorial);
	
}
