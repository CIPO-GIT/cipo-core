package ca.gc.ic.cipo.core.dao;

import java.util.List;
import java.util.Map;

/**
 * Basic CRUD data access operations are isolated in this 
 * UniversalDAO interface.  It is shared across all DAO 
 * implementations in order to deal with an entity model.  
 * A single instance implementing this interface can be used to 
 * handle multiple types of entities.  Refer to GenericDAO 
 * implementation for handling a single entity model.
 *
 * @see GenericDAO
 * @author DenisJ1
 */
  
// TODO - Verify if methods should throw a specific/generic exception on error ???
public interface UniversalDAO {
   
	/**
	 * Persist the given transient instance and add it to the data 
	 * store, first assigning a generated identifier. (Or using 
	 * the current value of the identifier property if the assigned 
	 * generator is used.) 
	 * 
	 * @param entity The entity to persist.
	 * @return The id of the newly saved entity.
	 */
    public <TKey, TEntity> TKey create(TEntity entity);
 
    
	/**
	 * Return the persistent instance of the given entity class with 
	 * the given key identifier, assuming that the instance exists. 
	 * Throw an unrecoverable exception if there is no matching database row.
	 * 
 	 * @param entityClass The entity class.
	 * @param key The key identifier of the entity.
	 * @return The persistent entity. 
	 */
    public <TKey, TEntity> TEntity load(Class<TEntity> entityClass, TKey key);       
    

	/**
	 * Return the persistent instance of the given entity class with 
	 * the given key identifier, assuming that the instance exists. 
	 * Throw an unrecoverable exception if there is no matching database row.
	 * 
 	 * @param entityClass The entity class.
	 * @param key The key identifier of the entity.
	 * @return The persistent entity. 
	 */
	public <TKey, TEntity> void load(TEntity entity, TKey key);
	
    /**
	 * If the id of the entity is null or zero, add it to the data store and
	 * assign it an id; otherwise, update the corresponding entity in the
	 * data store with the properties of this entity. 
	 *  
	 * @param entity The entity to save.
	 * @return The key-identifier associated to the entity.
	 */
    public <TKey, TEntity> TKey save(TEntity entity);

    
	/**
	 * Make a transient instance persistent and add it to the data store. 
	 * Throws an error if the entity already exists.
	 * 
	 * @param entity The entity to persist. 
	 */
	public <TEntity> void persist(TEntity entity);   
    
	
	/**
	 * Copy the state of the given object onto the persistent object 
	 * with the same identifier. If there is no persistent instance 
	 * currently associated with the session, it will be loaded. 
	 * Return the persistent instance. If the given instance is 
	 * unsaved, save a copy of and return it as a newly persistent instance. 
	 * 
	 * @param entity The entity to persist.
	 * @return The persistent entity. 
	 */
	public <TEntity> TEntity merge(TEntity entity);
	
    /**
	 * Update the given entity.
	 * 
	 * @param entity The entity to update.
	 */
	public <TEntity> void update(TEntity entity);


	/**
	 * Delete the given entity id.
	 * 
	 * @param key Id of the entity.
	 * 
	 * @return <code>True</code> on success, otherwise <code>false</code>.
	 */
	public <TKey, TEntity> boolean deleteById(Class<TEntity> entityClass, TKey key);


	/**
	 * Delete the given entity.
	 * 
	 * @param entity The entity to delete.
	 * 
	 * @return <code>True</code> on success, otherwise <code>false</code>.
	 */	
	public <TEntity> boolean delete(TEntity entity);

	/**
	 * Delete the given entities from the data store.
	 * 
 	 * @param entities The list of entities to delete.
 	 * 
	 * @return <code>True</code> if all entities are found in the data store 
	 *         and deleted, <code>false</code> if any was not found or deleted.
	 */
	public <TEntity> boolean deleteEntities(TEntity... entities);
	
	/**
     * Find and entity by id.
     * 
     * @param entityClass The entity class.
     * @param key The id of the entity.
     * 
     * @return The entity found if it exists, otherwise null.
     */
    public <TKey, TEntity> TEntity find(Class<TEntity> entityClass, TKey key);
    
    /**
     * Get all entities - performs a join with other associated 
     * entities if so specified in the mapping. 
     *  
     * @return A list of entities if any found, otherwise an empty list.
     */
    public <TEntity> List<TEntity> findAll(Class<TEntity> entityClass);
       
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

    // TODO - Add documentation
	public <TEntity> List<?> findByExample(Class<TEntity> entityClass, TEntity example);

	
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
