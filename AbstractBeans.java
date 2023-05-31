package Beans.Base;

import java.io.Serializable;

public abstract class AbstractBeans implements Serializable  {

	/**
	 * Serialization id
	 */
	private static final long serialVersionUID = -5435355496462943610L;
	
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}