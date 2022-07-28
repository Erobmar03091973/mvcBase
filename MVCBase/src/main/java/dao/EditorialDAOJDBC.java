package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Editorial;
import modelo.Libro;
import utilidades.ConexionBD;

public class EditorialDAOJDBC implements EditorialDAO {
	
	private ConexionBD conexion;
	
	

	public EditorialDAOJDBC() {
		conexion = new ConexionBD();
	}

	@Override
	public List<Editorial> getListaEditoriales() {
		List<Editorial> lista = new ArrayList<>();
		Connection con = conexion.getConexion();
		Statement consulta =null;
		ResultSet rs = null;
		
		try {
			consulta = con.createStatement();
			rs = consulta.executeQuery("select * from editoriales");
			while(rs.next()) {
				
				
				int codEditorial = rs.getInt("codEditorial");
				String nombre = rs.getString("nombre");
				int año = rs.getInt("anio");
				
				Editorial ed = new Editorial(codEditorial,nombre,año);	
				
				lista.add(ed);
			}
		
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				consulta.close();
				conexion.cerrarConexion();
			} catch (SQLException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public Editorial getEditorial(int codEditorial) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta =null;
		ResultSet rs = null;
		Editorial ed = null;
		
		try {
			consulta = con.prepareStatement(
				"select * from editoriales where codeditorial = ?");
			consulta.setInt(1, codEditorial);
			rs = consulta.executeQuery();
			if(rs.next()) {
				
				String nombre = rs.getString("nombre");
				int año = rs.getInt("anio");
				
				ed = new Editorial(codEditorial,nombre, año);	

			}
		
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				consulta.close();
				conexion.cerrarConexion();
			} catch (SQLException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
		}
		return ed;

	}

	@Override
	public int insertarEditorial(Editorial ed) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta =null;
		int num=0;
		try {
			consulta = con.prepareStatement(
					"insert into editoriales(nombre,anio)"
					+ " values(?,?)");
			
			/*"insert into editoriales (nombre,año)"
			+ "values ('"+ed.getNombre()+"', "+ed.getAño()+")";*/
			
			consulta.setString(1, ed.getNombre());
			consulta.setInt(2, ed.getAño());
			
			num = consulta.executeUpdate();
			System.out.println("Editorial insertada correctamente");

		
		} catch (SQLException e) {
			System.out.println("Error insertando editorial");
			e.printStackTrace();
		} finally {
			try {
				consulta.close();
				conexion.cerrarConexion();
			} catch (SQLException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
		}
		return num;
	}

	@Override
	public int editarEditorial(Editorial ed ) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta =null;
		int num=0;
		try {
			consulta = con.prepareStatement(
					"update editoriales "
					+ "set nombre = ?,"
					+ "    anio = ?"
					+ "where codEditorial = ?");
			
			
			consulta.setString(1, ed.getNombre());
			consulta.setInt(2, ed.getAño());
			consulta.setInt(3, ed.getCodEditorial());
			
			
			num = consulta.executeUpdate();
			System.out.println("Editorial actualizada correctamente");

		
		} catch (SQLException e) {
			System.out.println("Error editando editorial"+ed);
			e.printStackTrace();
		} finally {
			try {
				consulta.close();
				conexion.cerrarConexion();
			} catch (SQLException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
		}
		return num;
	}

	@Override
	public int eliminarEditorial(int codEditorial) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta =null;
		int num=0;
		try {
			consulta = con.prepareStatement(
					"delete from editoriales where codEditorial= ?");
			
			consulta.setInt(1, codEditorial);
			
			num = consulta.executeUpdate();
			System.out.println("Editorial borrada correctamente");

		
		} catch (SQLException e) {
			System.out.println("Error borrando la editorial "+codEditorial);
			e.printStackTrace();
		} finally {
			try {
				consulta.close();
				conexion.cerrarConexion();
			} catch (SQLException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
		}
		return num;
	}

}
