package Service.Base;

import java.util.*;

import Beans.Base.AbstractBeans;

public abstract class BaseService<T extends AbstractBeans> implements IBaseService<T> {
	private final Map<Integer, T> lista;

	public BaseService() {
		lista = new HashMap<>();
	}

	protected Map<Integer, T> getAll() {
		return lista;
	}

	@Override
	public void save(T newObject) {
		lista.put(newObject.getId(), newObject);
	}

	@Override
	public T getById(Integer id) {
		return lista.get(id);
	}
}