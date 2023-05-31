package Beans.Informacion;

import Beans.Base.AbstractBeans;

public class Informacion extends AbstractBeans{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3702842759113516327L;
	
	private String descripcion = "";
	private String valor = "";
	public String getDescripcion() {
		return descripcion;
	}
	public String getValor() {
		return valor;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return "Descripcion: " + this.getDescripcion() + " | Valor: " + this.getValor() + " | ";
	}
	
	
}
