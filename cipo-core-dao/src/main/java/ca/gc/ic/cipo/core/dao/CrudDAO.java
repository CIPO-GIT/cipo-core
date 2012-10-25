package ca.gc.ic.cipo.core.dao;

import java.util.List;

/**
 * All basic CRUD (create, read, update, delete) data access 
 * operations are isolated in this CrudDAO interface and 
 * shared across all DAO implementations in order to deal with 
 * an entity model.  A single instance implementing this interface 
 * can be used to handle only a single type of entity. Refer to 
 * GeneralDAO implementation for handling multiple type of entities.
 *
 * @author DenisJ1
 */
public interface CrudDAO<TKey, TEntity> {

	/**
	 * Return the class type entity associated to this DAO.
	 * 
	 * @return The class type entity.
	 */
	public Class<TEntity> getEntityClazz();


	/**
	 * Create the given entity and add it to the data store, by first 
	 * assigning a generated identifier. (Or using the current value of 
	 * the identifier property if the assigned generator is used). 
	 * 
	 * @param entity The entity to create.
	 * @return The id of the newly created entity.
	 */
	public TKey create(TEntity entity);

	/**
	 * Update the given entity.
	 * 
	 * @param entity The entity to update.
	 */
	public void update(TEntity entity);

	/**
	 * Delete the given entity.
	 * 
	 * @param entity The entity to delete.
	 * 
	 * @return <code>True</code> on success, otherwise <code>false</code>.
	 */	
	public boolean delete(TEntity entity);

	/**
	 * Find and entity by id.
	 * 
	 * @param entityClass The entity class.
	 * @param key The id of the entity.
	 * 
	 * @return The entity found if it exists, otherwise null.
	 */
	public TEntity find(TKey key);

	/**
	 * Get all entities - performs a join with other associated 
	 * entities if so specified in the mapping. 
	 *  
	 * @return A list of entities if any found, otherwise an empty list.
	 */
	public List<TEntity> findAll();

	
	// TODO - Add documentation
	public List<?> findByExample(TEntity example);
}


