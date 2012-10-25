package ca.gc.ic.cipo.core.dao.query;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCriteria implements CriteriaSet {

	private List<OrderBy> ordersBy = new ArrayList<OrderBy>();

	public List<OrderBy> getOrdersBy() {
		return ordersBy;
	}

	public void setOrderBy(List<OrderBy> ordersBy) {
		this.ordersBy = ordersBy;
	}		
	
	public void addOrderBy(OrderBy orderBy) {
		ordersBy.add(orderBy);
	}
}
