package Service.Contacto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Beans.Contacto.Contacto;
import Service.Base.BaseService;

public class ContactoServiceImpl extends BaseService<Contacto> implements IContactoService {

	@Override
	public Map<Integer, Contacto> findByName(String name) {
		Map<Integer, Contacto> lista = new HashMap<>();
		for (Map.Entry<Integer, Contacto> entry : this.getAll().entrySet()) {
			Integer key = entry.getKey();
			Contacto val = entry.getValue();

			if (val.getNombre().contains(name)) {
				lista.put(key, val);
			}

		}

		return lista;
	}

	@Override
	public void actualizar(Contacto contacto) {
		this.getAll().put(contacto.getId(), contacto);
	}

	@Override
	public void agregar(Contacto contacto) {
		this.getAll().put(contacto.getId(), contacto);

	}

	@Override
	public void eliminar(Contacto contacto) {
		this.getAll().remove(contacto.getId());

	}

	@Override
	public Map<Integer, Contacto> getContactos() {
		// TODO Auto-generated method stub
		return this.getAll();
	}

	public void setContactos(Map<Integer, Contacto> M) {
		if (M != null) {
			for (Map.Entry<Integer, Contacto> entry : M.entrySet()) {
				this.getAll().put(entry.getKey(), entry.getValue());

			}
		}
	}

}
