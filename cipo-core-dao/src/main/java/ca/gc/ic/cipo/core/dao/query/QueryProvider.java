package ca.gc.ic.cipo.core.dao.query;

import java.util.List;

/**
 * A QueryProvider provides a generic way of returning an arbitrary 
 * subset of a query results set. The essential point is that query 
 * results are read in small chunks, not all at once. This allows more 
 * efficient memory handling and better performance. Implementors can 
 * implements a caching mechanism if desired.
 *
 * @author DenisJ1
 *
 */
public interface QueryProvider {
	
	/**
	 * Return a set of objects based on a given criteria set.
	 * 
	 * @param criteria The criteria.
	 * @return A list of objects.
	 */
	public List<?> findByCriteria(CriteriaSet criteria);
}
