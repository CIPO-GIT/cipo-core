package ca.gc.ic.cipo.core.dao.hibernate;

import java.util.List;
import java.util.Map;

import ca.gc.ic.cipo.core.dao.UniversalDAO;

/**
 * A Universal Hibernate DAO implementation of the interface UniversalDAO that 
 * groups basic CRUD (create, read, update, delete). 
 * 
 * @author DenisJ1
 */
public class UniversalDAOImpl extends HibernateBaseDAO implements UniversalDAO { 

	@Override
	public <TKey, TEntity> TKey create(TEntity entity) {
		return super._save(entity);
	}

	@Override
	public <TKey, TEntity> TEntity load(Class<TEntity> entityClass, TKey key) {
		return super._load(entityClass, key);
	}

	@Override
	public <TKey, TEntity>  void load(TEntity entity, TKey key) {
		super._load(entity, key);
	}
	
	@Override
	public <TKey, TEntity> TKey save(TEntity entity) {
		return super._save(entity);
	}

	@Override
	public <TEntity> void persist(TEntity entity) {
		super._persist(entity);
	}

	@Override
	public <TEntity> TEntity merge(TEntity entity) {
		return super._merge(entity);
	}

	@Override
	public <TEntity> void update(TEntity entity) {
		super._update(entity);
	}

	@Override
	public <TKey, TEntity> boolean deleteById(Class<TEntity> entityClass, TKey key) {
		return super._deleteById(entityClass, key);
	}

	@Override
	public <TEntity> boolean delete(TEntity entity) {
		return super._delete(entity);
	}

	@Override
	public <TEntity> boolean deleteEntities(TEntity... entities) {
		return super._deleteEntities(entities);
	}

	@Override
	public <TKey, TEntity> TEntity find(Class<TEntity> entityClass, TKey key) {
		return super._find(entityClass, key);
	}

	@Override
	public <TEntity> List<TEntity> findAll(Class<TEntity> entityClass) {
		return super._findAll(entityClass);
	}
	
	@Override
	public <TEntity> List<?> findByExample(Class<TEntity> entityClass, TEntity example) {
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
