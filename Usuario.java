package Beans.Usuario;

import Beans.Base.AbstractBeans;

public class Usuario extends AbstractBeans {

	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 2509978491416763529L;

	private String nombre = "";
	private String apellido = "";

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}