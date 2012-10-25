package ca.gc.ic.cipo.core.dao.model;

import java.io.Serializable;

/**
 * Abstract class for all persisted entities.  
 *
 * @param <Key> Type of primary key, must be serializable.
 *
 * @author DenisJ1
 */
public abstract class PersistentBaseEntity<TKey extends Serializable> implements PersistentEntity<TKey>{

	private static final long serialVersionUID = 2968993238022248493L;
	
	/** The Primary key or the Identity of the entity. */
	protected TKey id;
	
	@Override
	public TKey getId() {
		return id;
	}

	@Override
	public void setId(TKey id) {
		this.id = id;
	}
	
	protected PersistentBaseEntity() {
		super();
	}
	
	protected PersistentBaseEntity(TKey id) {
		super();
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersistentBaseEntity<TKey> other = (PersistentBaseEntity<TKey>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}		
}
