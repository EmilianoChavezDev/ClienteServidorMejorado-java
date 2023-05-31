package Service.Base;

import Beans.Base.AbstractBeans;

public interface IBaseService <T extends AbstractBeans> {
	public void save(T newBean);
	public T getById(Integer id);
}