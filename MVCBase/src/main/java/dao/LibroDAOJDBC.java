package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Libro;
import utilidades.ConexionBD;

public class LibroDAOJDBC implements LibroDAO {
	
	private ConexionBD conexion;
	
	

	public LibroDAOJDBC() {
		conexion = new ConexionBD();
	}

	@Override
	public List<Libro> getListaLibros() {
		List<Libro> lista = new ArrayList<>();
		Connection con = conexion.getConexion();
		Statement consulta =null;
		ResultSet rs = null;
		
		try {
			consulta = con.createStatement();
			rs = consulta.executeQuery("select * from libros");
			while(rs.next()) {
				
				String isbn = rs.getString("isbn");
				String titulo = rs.getString("titulo");
				int codEditorial = rs.getInt("codEditorial");
				int año = rs.getInt("anio");
				int numPags = rs.getInt("num_pags");
				float precio = rs.getFloat("precio");
				int cantidad = rs.getInt("cantidad");
				float precioCD = rs.getFloat("precioCD");
				
				Libro libro = new Libro(isbn, titulo, codEditorial, 
						año, numPags, precio, cantidad, precioCD);	
				
				lista.add(libro);
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
	public Libro getLibro(String isbn) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta =null;
		ResultSet rs = null;
		Libro libro = null;
		
		try {
			consulta = con.prepareStatement(
					"select * from libros where isbn = ?");
			consulta.setString(1, isbn);
			rs = consulta.executeQuery();
			if(rs.next()) {
				
				String titulo = rs.getString("titulo");
				int codEditorial = rs.getInt("codEditorial");
				int año = rs.getInt("anio");
				int numPags = rs.getInt("num_pags");
				float precio = rs.getFloat("precio");
				int cantidad = rs.getInt("cantidad");
				float precioCD = rs.getFloat("precioCD");
				
				libro = new Libro(isbn, titulo, codEditorial, 
						año, numPags, precio, cantidad, precioCD);	

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
		return libro;

	}

	@Override
	public int insertarLibro(Libro libro) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta =null;
		int num=0;
		try {
			consulta = con.prepareStatement(
					"insert into libros values(?,?,?,?,?,?,?,?)");
			
			consulta.setString(1, libro.getIsbn());
			consulta.setString(2, libro.getTitulo());
			consulta.setInt(3, libro.getCodEditorial());
			consulta.setInt(4, libro.getAño());
			consulta.setInt(5, libro.getNumPags());
			consulta.setFloat(6, libro.getPrecio());
			consulta.setInt(7, libro.getCantidad());
			consulta.setFloat(8, libro.getPrecioCD());
			
			num = consulta.executeUpdate();
			System.out.println("Libro insertado correctamente");

		
		} catch (SQLException e) {
			System.out.println("Error insertando libro");
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
	public int editarLibro(Libro libro) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta =null;
		int num=0;
		try {
			consulta = con.prepareStatement(
					"UPDATE libros\r\n"
					+ "SET\r\n"
					+ "titulo = ?,\r\n"
					+ "codEditorial = ?,\r\n"
					+ "anio = ?,\r\n"
					+ "num_pags = ?,\r\n"
					+ "precio = ?,\r\n"
					+ "cantidad = ?,\r\n"
					+ "precioCD = ?\r\n"
					+ "WHERE isbn = ?;");
			
			
			consulta.setString(1, libro.getTitulo());
			consulta.setInt(2, libro.getCodEditorial());
			consulta.setInt(3, libro.getAño());
			consulta.setInt(4, libro.getNumPags());
			consulta.setFloat(5, libro.getPrecio());
			consulta.setInt(6, libro.getCantidad());
			consulta.setFloat(7, libro.getPrecioCD());
			consulta.setString(8, libro.getIsbn());
			
			num = consulta.executeUpdate();
			System.out.println("Libro actualizado correctamente");

		
		} catch (SQLException e) {
			System.out.println("Error editando libro");
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
	public int eliminarLibro(String isbn) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta =null;
		int num=0;
		try {
			consulta = con.prepareStatement(
					"delete from libros where isbn= ?");
			
			consulta.setString(1, isbn);
			
			num = consulta.executeUpdate();
			System.out.println("Libro borrado correctamente");

		
		} catch (SQLException e) {
			System.out.println("Error borrando libro");
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
