package Service.Usuario;

import Service.Base.*;

import java.util.Collection;


import Beans.Usuario.*;

public class UsuarioServiceImpl extends BaseService<Usuario> implements IUsuarioService {

	@Override
	public Usuario findByName(String name) {
		Collection<Usuario> lista = this.getAll().values();
		
		for (Usuario usuario : lista) {
			if(usuario.getNombre().equals(name)) {
				return usuario;
			}
		}
		
		return null;
	}



}