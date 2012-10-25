package ca.gc.ic.cipo.core.dao;

import java.util.List;
import java.util.Map;

/**
 * Provides generic data access operations for data persistence.  
 * All operations are isolated in this GenericDAO interface and shared 
 * across all DAO implementations in order to deal with an entity model.  
 * A single instance implementing this interface can be used to handle 
 * only a single type of entity. Refer to GeneralDAO implementation for 
 * handling multiple type of entities.
 *
 * @author DenisJ1
 */

// TODO - Should methods throw a specific/generic exception on error ???
public interface GenericDAO<TKey, TEntity> extends CrudDAO<TKey, TEntity> {
    
	/**
	 * Return the persistent instance of the given entity class 
	 * with the given key identifier, assuming that the instance 
	 * exists. Throw an unrecoverable exception if there is no 
	 * matching database row.
	 * 
 	 * @param entityClass The entity class.
	 * @param key The key identifier of the entity.
	 * @return The persistent entity. 
	 */
    public TEntity load(TKey key);       
    	
    
	/**
	 * Return the persistent instance of the given entity class with 
	 * the given key identifier, assuming that the instance exists. 
	 * Throw an unrecoverable exception if there is no matching database row.
	 * 
 	 * @param entityClass The entity class.
	 * @param key The key identifier of the entity.
	 */
	public void load(TEntity entity, TKey key);

    
    /**
	 * If the id of the entity is null or zero, add it to the data store and
	 * assign it an id; otherwise, update the corresponding entity in the
	 * data store with the properties of this entity. 
	 *  
	 * @param entity The entity to save.
	 * @return The key-identifier associated to the entity.
	 */
    public TKey save(TEntity entity);

    
	/**
	 * Make a transient instance persistent and add it to the data store. 
	 * Throws an error if the entity already exists.
	 * 
	 * @param entity The entity to persist. 
	 */
	public void persist(TEntity entity);   
    
	
	/**
	 * Copy the state of the given object onto the persistent 
	 * object with the same identifier. If there is no persistent 
	 * instance currently associated with the session, it will be 
	 * loaded. Return the persistent instance. If the given instance 
	 * is unsaved, save a copy of and return it as a newly persistent 
	 * instance. 
	 * 
	 * @param entity The entity to persist.
	 * @return The persistent entity. 
	 */
	public TEntity merge(TEntity entity);
	
 	/**
	 * Delete the given entity id.
	 * 
	 * @param key Id of the entity.
	 * 
	 * @return <code>True</code> on success, otherwise <code>false</code>.
	 */
	public boolean deleteById(TKey key);

	/**
	 * Delete the given entities from the data store.
	 * 
	 * @param entities The list of entities to delete.
	 * 
	 * @return <code>True</code> if all entities are found in the data store 
	 *         and deleted, <code>false</code> if any was not found or deleted.
	 */
	public boolean deleteEntities(TEntity... entities);
	
	/**
	 * Get a list of entities base on the given named query with 
	 * its associated parameters. The returned list might be null.
	 * 
	 * @param name The named query.
	 * @param parameters The list of parameters if any.
	 * 
	 * @return The list of entities.
	 */
	public List<?> findByNamedQuery(String name, Map<String, Object> parameters);	
	
	/**
	 * Get a list of entities base on the given native query with 
	 * its associated parameters. The returned list might be null.
	 * 
	 * @param ql The query using Native Query Language (QL).
	 * @param parameters The list of parameters if any.
	 * 
	 * @return The list of entities.
	 */
	public List<?> findByNativeQuery(String ql, Map<String, Object> parameters);
	

	/**
	 * Get a list of entities base on the given SQL query with 
	 * its associated parameters. The returned list might be null.
	 * 
	 * @param sql The SQL query.
	 * @param parameters The list of parameters if any.
	 * 
	 * @return The list of entities.
	 */
	public List<?> findBySQLQuery(String sql, Map<String, Object> parameters);
  
    /**
	 * Execute a native query (insert, update or delete) with  its 
	 * associated parameters. 
	 * 
	 * @param ql The query using the Native Query Language (QL).
	 * @param parameters The list of parameters if any.
	 * 
	 * @return <code>True</code> if the execution of the query was successful
	 * 		and one or more rows have been modified in the data store, 
	 *      <code>false</code> otherwise.
	 */
	public boolean executeNativeUpdate(String ql, Map<String, Object> parameters);
	
	/**
	 * Execute an SQL query (insert, update or delete) with its associated 
	 * parameters. 
	 * 
	 * @param sql The SQL query.
	 * @param parameters The list of parameters if any.
	 * 
	 * @return <code>True</code> if the execution of the query was successful
	 * 		and one or more rows have been modified in the data store, 
	 *      <code>false</code> otherwise.
	 */
	public boolean executeSQLUpdate(String sql, Map<String, Object> parameters);
	
}
