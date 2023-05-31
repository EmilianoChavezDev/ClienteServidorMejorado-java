package Service.Contacto;

import java.util.List;
import java.util.Map;

import Beans.Contacto.*;
import Beans.Informacion.Informacion;
import Beans.Usuario.Usuario;
import Service.Base.IBaseService;

public interface IContactoService extends IBaseService<Contacto>{
	public Map<Integer, Contacto> findByName(String name);
	public void actualizar(Contacto contacto);
	public void agregar(Contacto contacto);
	public void eliminar(Contacto contacto);
	public Map<Integer, Contacto> getContactos();
}
