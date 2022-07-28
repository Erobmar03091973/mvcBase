package modelo;

import java.util.Objects;

public class Editorial {
	private int codEditorial;
	private String nombre;
	private int año;
	
	
	
	public Editorial() {
	}

	public Editorial(int codEditorial, String nombre, int año) {
		super();
		this.codEditorial = codEditorial;
		this.nombre = nombre;
		this.año = año;
	}

	public Editorial(String nombre, int año) {
		this.nombre = nombre;
		this.año = año;
	}

	public int getCodEditorial() {
		return codEditorial;
	}

	public void setCodEditorial(int codEditorial) {
		this.codEditorial = codEditorial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	@Override
	public String toString() {
		return "Editorial [codEditorial=" + codEditorial + ", nombre=" + nombre + ", año=" + año + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codEditorial);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editorial other = (Editorial) obj;
		return codEditorial == other.codEditorial;
	}
	
	
	
	

}
