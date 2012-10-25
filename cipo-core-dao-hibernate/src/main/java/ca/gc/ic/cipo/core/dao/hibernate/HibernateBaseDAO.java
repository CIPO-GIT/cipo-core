package ca.gc.ic.cipo.core.dao.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ca.gc.ic.cipo.core.dao.exception.DataAccessException;
import ca.gc.ic.cipo.core.dao.query.ComparisonOperator;
import ca.gc.ic.cipo.core.dao.query.OrderBy;

/**
 * HibernateBaseDAO is a base class providing basic operations using Hibernate 
 * Session Factory and Hibernate Query Language.
 * 
 * @author DenisJ1
 */
/**
 * @author DenisJ1
 *
 */
public abstract class HibernateBaseDAO {
		
	/** Logger. */
	protected final Logger logger = Logger.getLogger(HibernateBaseDAO.class);
	
	/** Hibernate Session factory. */
	private SessionFactory sessionFactory;
		
	/**
	 * Default constructor.  
	 * Build an abstract Hibernate DAO to persist a specific entity model. 
	 */
	protected HibernateBaseDAO() {
		super();
		this.sessionFactory = null;
	}
	
	/**
	 * Return the Hibernate Session Factory.
	 * 
	 * @return A reference of the Session factory if it exists, otherwise null.
	 */
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/** 
	 * Sets the Hibernate Session factory.
	 * 
	 * @param sessionFactory The Session factory.
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {		
		this.sessionFactory = sessionFactory;
	}
	      
	
	/**
	 * Return the current session associated from the session factory.  
	 * 
	 * @return The current session if it exists, otherwise null.
	 */
	protected Session getSession() {
		return sessionFactory != null ? sessionFactory.getCurrentSession() : null;
	}

	
	protected Session openSession() {  
		return sessionFactory != null ? sessionFactory.openSession() : null;
	}
 	
	/**
	 * Returns true if the entity is connected to the current 
	 * Hibernate session.
	 * 
	 * @param entity The entity to verify. 
	 * 
	 * @return <code>True</code> if the entity is connected to the current session,
	 *         otherwise <code>false</code>.
	 */
	public <TEntity> boolean contains(TEntity entity) {
		return getSession().contains(entity);
	}

	/**
	 * Flushes changes in the Hibernate cache to the data store.
	 */
	public void flush() {
		getSession().flush();
	}
	
	
	/**
	 * Build a simple criterion that match the given comparison operator.
	 * 
	 * @param propertyName Name of the property.
	 * @param value Value of the property.
	 * @param operation Comparison operator.
	 * 
	 * @return A Criterion representing the given property with it's value. 
	 */
	public static Criterion toCriterion(String propertyName, 
			Object value, ComparisonOperator operation) {

		Criterion criterion = null;
		
		if (propertyName != null) {
			if (operation == ComparisonOperator.EQUAL) {
				criterion = Restrictions.eq(propertyName, value);
			} else if (operation == ComparisonOperator.NOT_EQUAL) {
				criterion = Restrictions.ne(propertyName, value);
			} else if (operation == ComparisonOperator.LESS_OR_EQ) {
				criterion = Restrictions.le(propertyName, value);
			} else if (operation == ComparisonOperator.LESS_THAN) {
				criterion = Restrictions.lt(propertyName, value);
			} else if (operation == ComparisonOperator.GREATER_OR_EQ) {
				criterion = Restrictions.ge(propertyName, value);
			} else if (operation == ComparisonOperator.GREATER_THAN) {
				criterion = Restrictions.gt(propertyName, value);
			} else if (operation == ComparisonOperator.LIKE) {
				criterion = Restrictions.like(propertyName, value);
			} else if (operation == ComparisonOperator.NOT_LIKE) {
				criterion = Restrictions.not(Restrictions.like(propertyName, value));
			} else {
				throw new IllegalArgumentException("Search operation NOT supported ...");
			}
		}
		return criterion;
	}
	
	/**
	 * Add an Order By. 
	 * 
	 * @param orderBy Order by details.
	 * 
	 * @return The resulting criteria. 
	 */

	public Criteria addOrder(Criteria criteria, OrderBy orderBy) {
		if (orderBy.isAscending()) {
			criteria.addOrder(Order.asc(orderBy.getProperty())); 
		} 
		
		criteria.addOrder(Order.desc(orderBy.getProperty()));
		return criteria; 
	}

	/**
	 * Add a list of Order By. 
	 * 
	 * @param ordersBy List of order by details.
	 * 
	 * @return The resulting criteria. 
	 */
	public Criteria addOrders(Criteria criteria, List<OrderBy> ordersBy) {		
		for (OrderBy orderBy : ordersBy) {
			addOrder(criteria, orderBy);
		}
		return criteria;
	}
	
	
	/**
	 * Return the persistent instance of the given entity class with the given
	 * key identifier, assuming that the instance exists. Throw an unrecoverable
	 * exception if there is no matching database row.
	 * 
 	 * @param entityClass The entity class.
	 * @param key The key identifier of the entity.
	 * 
	 * @return The persistent entity. 
	 */	
	@SuppressWarnings("unchecked")
	public <TKey, TEntity> TEntity _load(Class<TEntity> entityClass, TKey key) {
		
		TEntity entity = null;
		Session session = getSession();
		try {
			entity = (TEntity) session.load(entityClass, (Serializable) key);	
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 
		return entity;
	}
	
	/**
	 * Read the persistent state associated with the given identifier into the
	 * given entity transient instance. Throw an unrecoverable exception if there 
	 * is no matching database row.
	 * 
	 * @param entity The transient entity.
	 * @param key The key identifier of the transient entity.
	 *  
	 */
	public <TKey, TEntity> void _load(TEntity entity, TKey key) {
		
		Session session = getSession();
		try {
			session.load(entity, (Serializable) key);
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 
	}	
	
	/**
	 * Make a transient instance persistent and add it to the data store. This
	 * operation cascades to associated instances if the association is mapped
	 * with cascade="persist". Throws an error if the entity already exists.
	 * 
	 * This is different from <code>save()</code> in that it does not guarantee
	 * that the object will be assigned an identifier immediately. With
	 * <code>save()</code> a call is made to the data store immediately if the id
	 * is generated by the data store so that the id can be determined. With
	 * <code>persist</code> this call may not occur until flush time.
	 * 	 
	 * @see save
	 */
	public <TEntity> void _persist(TEntity entity) {
		Session session = getSession();
		try {
			session.persist(entity);	
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 	
	}
	
	
	/**
	 * Persist the given transient entity and add it to the data store, first
	 * assigning a generated identifier. (Or using the current value of the
	 * identifier property if the assigned generator is used.) This operation
	 * cascades to associated instances if the association is mapped with
	 * cascade="save-update".
	 * 
	 * This is different from <code>persist()</code> in that it does guarantee
	 * that the object will be assigned an identifier immediately. With
	 * <code>save()</code> a call is made to the data store immediately if the id
	 * is generated by the data store so that the id can be determined. With
	 * <code>persist</code> this call may not occur until flush time.
	 * 
	 * @return The id of the newly saved entity.
	 * 
	 * @see create
	 * @see update
	 */
	@SuppressWarnings("unchecked")
	public <TKey, TEntity> TKey _save(TEntity entity) {
		TKey id = null;
		Session session = getSession();
		try {
			id = (TKey) session.save(entity);
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 	
	
		return id; 
	}
	
	/**
	 * <p>
	 * Calls Hibernate's <code>saveOrUpdate()</code>, which behaves as follows:
	 * 
	 * Either <code>save()</code> or <code>update()</code> based on the
	 * following rules:
	 * <ul>
	 * <li>If the object is already persistent in this session, do nothing
	 * <li>If another object associated with the session has the same identifier,
	 * throw an exception
	 * <li>If the object has no identifier property, save() it
	 * <li>If the object's identifier has the value assigned to a newly
	 * instantiated object, save() it
	 * <li>If the object is versioned (by a &lt;version&gt; or
	 * &lt;timestamp&gt;), and the version property value is the same value
	 * assigned to a newly instantiated object, save() it
	 * <li>Otherwise update() the object
	 * </ul>
	 * 
	 * @see save
	 * @see update
	 */
	protected <TEntity> void _saveOrUpdate(TEntity entity) {
		try {
			getSession().saveOrUpdate(entity);	
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 			
	}
	
	/**
	 * <p>
	 * Update the persistent instance with the identifier of the given detached
	 * instance. If there is a persistent instance with the same identifier, an
	 * exception is thrown. This operation cascades to associated instances if
	 * the association is mapped with cascade="save-update".
	 * 
	 * <p>
	 * The difference between <code>update()</code> and <code>merge()</code> is
	 * significant: <code>update()</code> will make the given object persistent
	 * and throw and error if another object with the same ID is already
	 * persistent in the Session. <code>merge()</code> doesn't care if another
	 * object is already persistent, but it also doesn't make the given object
	 * persistent; it just copies over the values to the data store.
	 * 
	 * @see merge
	 * @see save
	 */
	public <TEntity> TEntity _update(TEntity entity) {
		Session session = getSession();
		try {
			session.update(entity);	
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 		
		return entity;
	}
	
	/**
	 * <p>
	 * Copy the state of the given object onto the persistent object with the
	 * same identifier. If there is no persistent instance currently associated
	 * with the session, it will be loaded. Return the persistent instance. If
	 * the given instance is unsaved, save a copy of and return it as a newly
	 * persistent instance. The given instance does not become associated with
	 * the session. This operation cascades to associated instances if the
	 * association is mapped with cascade="merge".
	 * 
	 * <p>
	 * The difference between <code>update()</code> and <code>merge()</code> is
	 * significant: <code>update()</code> will make the given object persistent
	 * and throw and error if another object with the same ID is already
	 * persistent in the Session. <code>merge()</code> doesn't care if another
	 * object is already persistent, but it also doesn't make the given object
	 * persistent; it just copies over the values to the data store.
	 * 
	 * @see update
	 */
	@SuppressWarnings("unchecked")
	public <TEntity> TEntity _merge(TEntity entity) {
		
		TEntity mergeEntity = null;
		Session session = getSession();
		try {
			mergeEntity = (TEntity) session.merge(entity);	
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 	
	
		return mergeEntity; 
	}
	
	
	/**
	 * Remove the entity of the specified class with the specified id 
	 * from the data store.
	 * 
	 * @return <code>True</code> if the object is found in the data store 
	 *         and deleted, <code>false</code> if the item is not found.
	 */
	public <TKey, TEntity> boolean _deleteById(Class<TEntity> entityClass, TKey key) {
		TEntity entity = _find(entityClass, key);
		if (entity != null) {
			return _delete(entity);
		}
		return false;
	}
	
	/**
	 * Remove the specified entities from the data store.
	 * 
	 * @return <code>True</code> if all entities are found in the data store 
	 *         and deleted, <code>false</code> if any was not found or deleted.
	 */
	public <TEntity> boolean _deleteEntities(TEntity... entities) {
		
		boolean allDeleted = true;
		for (TEntity entity : entities) {
			if (entity != null) {
				if (!_delete(entity)) {
					allDeleted = false;
				}
			}
		}
		
		return allDeleted;
	}
	
	/**
	 * Remove the entity of the specified class with the specified id 
	 * from the data store.
	 * 
	 * @return <code>True</code> if the entity is found in the data store 
	 *         and deleted, <code>false</code> if not found or deleted.
	 */
	public <TEntity> boolean _delete(TEntity entity) {
		
		boolean deleted = false;
		Session session = getSession(); 
		try {
			session.delete(entity);
			deleted = true;
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 		
		return deleted;
	}
	

	/**
	 * Return the persistent instance of the given entity class with the given
	 * key identifier, or null if there is no such persistent instance.
	 * 
	 * <code>find()</code> always hits the database immediately.
	 */
	@SuppressWarnings("unchecked")
	public<TKey, TEntity>  TEntity _find(Class<TEntity> entityClass, TKey key) {
		TEntity result = null;
		Session session = getSession();
		try {
			result = (TEntity) session.get(entityClass, (Serializable) key);
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 		
	
		return result; 
	}

	/**
	 * Get a list of all the entities of the specified class type from 
	 * the data store.  The returned list might be null.
	 * 
	 * @return List of entities.
	 */
	@SuppressWarnings("unchecked")
	public <TEntity> List<TEntity> _findAll(Class<TEntity> entityClass) { 
		
		List<TEntity> listResult = null;
		Session session = getSession();
		try {
			Criteria criteria = session.createCriteria(entityClass);
			listResult = (criteria != null) ? criteria.list() : null;
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 		
	
		return listResult; 
	}

	/**
	 * Get a list of entities base on the given named query with 
	 * its associated parameters. The returned list might be null.
	 * 
	 * @param name The named query.
	 * @param parameters The list of parameters if any.
	 * 
	 * @return The list of entities.
	 */
	public List<?> _findByNamedQuery(String name, Map<String, Object> parameters) {
		
		try {				
			Query query = getNamedQuery(name, parameters);			
			return query.list();
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 		
	}

	/**
	 * Get a list of entities base on the given Hibernate query with 
	 * its associated parameters. The returned list might be null.
	 * 
	 * @param ql The query using Hibernate Native Query Language (HQL).
	 * @param parameters The list of parameters if any.
	 * 
	 * @return The list of entities.
	 */
	public List<?> _findByNativeQuery(String ql, Map<String, Object> parameters) {
	 	
		try {				
			Query query = createNativeQuery(ql, parameters);			
			return query.list();
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 		
	}

	/**
	 * Get a list of entities base on the given SQL query with 
	 * its associated parameters. The returned list might be null.
	 * 
	 * @param sql The SQL query.
	 * @param parameters The list of parameters if any.
	 * 
	 * @return The list of entities.
	 */
	public List<?> _findBySQLQuery(String sql, Map<String, Object> parameters) {
	 	
		try {				
			Query query = createSQLQuery(sql, parameters);			
			return query.list();
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 		
	}

	
	public <TEntity> List<?> _findByExample(Class<TEntity> entityClass, TEntity example) {
	
		Session session = getSession(); 
		Example instanceExample = Example.create(example).excludeZeroes(); 
		
		// Create criteria based on the example 
		Criteria criteria = session.createCriteria(entityClass).add(instanceExample); 		
		return criteria.list();	
	}

	public <TEntity> List<?> _findByExample(Class<TEntity> entityClass, TEntity example, 
			int firstResult, int maxResults) {
	
		Session session = getSession(); 
		Example instanceExample = Example.create(example).excludeZeroes(); 
		
		// Create criteria based on the example 
		Criteria criteria = session.createCriteria(entityClass).add(instanceExample);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();	
	}

	/**
	 * Execute an Hibernate query (insert, update or delete) with 
	 * its associated parameters. 
	 * 
	 * @param ql The query using Hibernate Native Query Language (HQL).
	 * @param parameters The list of parameters if any.
	 * 
	 * @return <code>True</code> if the execution of the query was successful
	 * 		and one or more rows have been modified in the data store, 
	 *      <code>false</code> otherwise.
	 */
	public boolean _executeNativeUpdate(String ql, Map<String, Object> parameters) {
	 			
		boolean queryResult = false;
		try {				
			Query query = createNativeQuery(ql, parameters);
			int result = query.executeUpdate();
			queryResult = (result > 0);
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 		
		
		return queryResult;
	}

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
	public boolean _executeSQLUpdate(String sql, Map<String, Object> parameters) {
	 			
		boolean queryResult = false;
		try {				
			Query query = createSQLQuery(sql, parameters);
			int result = query.executeUpdate();
			queryResult = (result > 0);
		} catch (HibernateException e) {
			throw new DataAccessException(e);
		} 		
		
		return queryResult;
	}

	protected Query getNamedQuery(String name, Map<String, Object> parameters) {				
		Query query = getSession().getNamedQuery(name);			
		// If any parameters, then add it.
		if (parameters != null) {
			for (Map.Entry<String, Object> param : parameters.entrySet()) {
				query.setParameter(param.getKey(), param.getValue());
			}
		}			
		return query;
	}
	
	
	protected Query createNativeQuery(String ql, Map<String, Object> parameters) {		 
		Query query = getSession().createQuery(ql);		
		// If any parameters, then add it.
		if (parameters != null) {
			for (Map.Entry<String, Object> param : parameters.entrySet()) {
				query.setParameter(param.getKey(), param.getValue());
			}
		}			
		return query; 
	}
	
	
	protected Query createSQLQuery(String sql, Map<String, Object> parameters) {
				
		Query query = getSession().createSQLQuery(sql);			
		// If any parameters, then add it.
		if (parameters != null) {
			for (Map.Entry<String, Object> param : parameters.entrySet()) {
				query.setParameter(param.getKey(), param.getValue());
			}
		}			
		return query;
	}
	
	/**
     * Return list of entities model that match the given criterion.
     * 
     * @param criterion The list of criterion.
     * 
     * @return A list of entities.  
     */
    @SuppressWarnings("unchecked")
    protected <TEntity> List<?> findByCriteria(Class<TEntity> entityClass, Criterion... criterion) {
    	List<TEntity> listResult = null;
        Criteria crit = getSession().createCriteria(entityClass);
        if (criterion != null) {
        	for (Criterion c : criterion) {
        		crit.add(c);
        	}
        	listResult = crit.list();
        }
        return listResult;
   }

}
