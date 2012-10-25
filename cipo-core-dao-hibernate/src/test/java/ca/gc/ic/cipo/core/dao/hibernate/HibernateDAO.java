package ca.gc.ic.cipo.core.dao.hibernate;

import java.util.List;
import java.util.Map;

import ca.gc.ic.cipo.core.dao.hibernate.HibernateBaseDAO;

public class HibernateDAO extends HibernateBaseDAO {

	public <TEntity> List<TEntity> findAll(Class<TEntity> entityClass) {
		return super._findAll(entityClass);
	}

	public <TKey, TEntity> boolean deleteById(Class<TEntity> entityClass, TKey id) {
		return super._deleteById(entityClass, id);
	}

	public <TEntity> boolean delete(TEntity entity) {
		return super._delete(entity);
	}

	public <TKey, TEntity> TEntity find(Class<TEntity> entityClass, TKey id) {
		return super._find(entityClass, id);
	}

	public <TKey, TEntity> TEntity load(Class<TEntity> entityClass, TKey id) {
		return super._load(entityClass, id);
	}

	public <TEntity> TEntity merge(TEntity entity) {
		return super._merge(entity);
	}

	public <TEntity> void persist(TEntity entity) {
		super._persist(entity);
	}

	public <TKey, TEntity> TKey save(TEntity entity) {
		return super._save(entity);
	}

	
	public <TKey, TEntity> TKey create(TEntity entity) {
		return super._save(entity);
	}

	
	public <TEntity> void update(TEntity entity) {
		super._update(entity);
		
	}

	
	public <TEntity> boolean deleteEntities(TEntity... entities) { 
		return super._deleteEntities(entities);
	}

	
	public List<?> findByNamedQuery(String name, Map<String, Object> parameters) {
		return super._findByNamedQuery(name, parameters);
	}

	
	public List<?> findByNativeQuery(String ql, Map<String, Object> parameters) {
		return super._findByNativeQuery(ql, parameters);
	}

	
	public List<?> findBySQLQuery(String sql, Map<String, Object> parameters) {
		return super._findBySQLQuery(sql, parameters);
	}

	
	public <TEntity> List<?> findByExample(Class<TEntity> entityClass,
			TEntity example) {
		return super._findByExample(entityClass, example);
	}

	
	public <TEntity> List<?> findByExample(Class<TEntity> entityClass,
			TEntity example, int firstResult, int maxResults) {
		return super._findByExample(entityClass, example, firstResult, maxResults);
	}
}
