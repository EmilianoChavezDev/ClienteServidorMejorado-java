package Beans.Contacto;

import Beans.Usuario.Usuario;

import java.util.HashMap;
import java.util.Map;

import Beans.Informacion.*;

public class Contacto extends Usuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5871143088630748587L;
	
	private static Integer idGenerator = 0;
	
	private Map<String, Informacion> informaciones = new HashMap<String, Informacion>();
	
	/*public Contacto(){
		setId(getIdGenerator());
	}*/
	
	public Map<String, Informacion> getAllInfos(){
		return informaciones;
	}
	
	@Override
	public String toString() {
		return this.getNombre() + " " + this.getApellido();
	}
	public String infoToString() {
		String strInformaciones="";
		for (Map.Entry<String, Informacion> info : informaciones.entrySet()) {
			String descripcion = info.getValue().getDescripcion();
			String valor = info.getValue().getValor();
			strInformaciones+=descripcion+":"+valor+"//";
			
		}
		return strInformaciones;
		
	}
	
	public static Integer getIdGenerator() {
		return ++idGenerator;
	}
	public static void setIdGenerator(Integer idN) {
		if(idGenerator < idN) {
			idGenerator = idN;
		}
	}
}
