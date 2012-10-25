package ca.gc.ic.cipo.core.dao.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import ca.gc.ic.cipo.core.dao.GenericDAO;


/**
 * A Generic Hibernate DAO implementation of the interface GenericDAO that 
 * groups basic CRUD (create, read, update, delete) operations. 
 * 
 * @author DenisJ1
 */
@SuppressWarnings("unchecked")
public class GenericDAOImpl<TKey, TEntity> extends HibernateBaseDAO implements GenericDAO<TKey, TEntity> { 
	
	/** Class of the entity type. */
	private Class<TEntity> entityClass = ((Class<TEntity>) ((ParameterizedType) getClass().
			getGenericSuperclass()).getActualTypeArguments()[1]);
	
	/**
	 * Set the class entity associated to this DAO.
	 * 
	 * @param clazz The class entity.
	 */
	public void setEntityClazz(Class<TEntity> clazz) {
		this.entityClass = clazz;
	}
	
	/**
	 * Return the class type entity associated to this DAO.
	 * 
	 * @return The class type entity.
	 */
	public Class<TEntity> getEntityClazz() {
		return entityClass;
	}
	
	@Override
	public TKey create(TEntity entity) {
		return super._save(entity);
	}

	@Override
	public TEntity load(TKey key) {
		return super._load(entityClass, key);		
	}

	@Override
	public void load(TEntity entity, TKey key) {
		super._load(entity, key);
	}
	
	@Override
	public TKey save(TEntity entity) {
		return super._save(entity); 
	}

	@Override
	public void persist(TEntity entity) {
		super._persist(entity);		
	}

	@Override
	public TEntity merge(TEntity entity) {
		return super._merge(entity);
	}

	@Override
	public void update(TEntity entity) {
		super._update(entity); 		
	}

	@Override
	public boolean deleteById(TKey key) {
		return super._deleteById(entityClass, key);
	}

	@Override
	public boolean delete(TEntity entity) {		
		return super._delete(entity);
	}

	@Override
	public boolean deleteEntities(TEntity... entities) {
		return super._deleteEntities(entities);
	}

	@Override
	public TEntity find(TKey key) {
		return super._find(entityClass, key);
	}

	@Override
	public List<TEntity> findAll() {
		return super._findAll(entityClass);
	}
	
	public List<?> findByExample(TEntity example) {
		return super._findByExample(entityClass, example);
	}
	
	@Override
	public List<?> findByNamedQuery(String name, Map<String, Object> parameters) {
		return super._findByNamedQuery(name, parameters);
	}

	@Override
	public List<?> findByNativeQuery(String ql, Map<String, Object> parameters) {
		return super._findByNativeQuery(ql, parameters);
	}

	@Override
	public List<?> findBySQLQuery(String sql, Map<String, Object> parameters) {
		return super._findBySQLQuery(sql, parameters);
	}
	
	@Override
	public boolean executeNativeUpdate(String ql, Map<String, Object> parameters) {
		return super._executeNativeUpdate(ql, parameters);
	}

	@Override
	public boolean executeSQLUpdate(String sql, Map<String, Object> parameters) {
		return super._executeSQLUpdate(sql, parameters);
	}
}
