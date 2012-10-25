package ca.gc.ic.cipo.core.dao.model;

import java.io.Serializable;

/**
 * Interface that marks entity class that can be persisted.  
 * Each entity must defined their Primary Key (Id).
 *
 * @param <Key> Type of primary key, must be serializable.
 *
 * @author DenisJ1
 */
public interface PersistentEntity<TKey extends Serializable> extends Entity {
	
	/**
     * Get primary key.
     *
     * @return primary key
     */
    TKey getId();

    /**
     * Set primary key.
     *
     * @param id primary key
     */
    void setId(TKey id);    
}
