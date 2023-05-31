package Service.Usuario;


import Beans.Usuario.*;
import Service.Base.IBaseService;


public interface IUsuarioService extends IBaseService<Usuario> {
	public Usuario findByName(String name);
}