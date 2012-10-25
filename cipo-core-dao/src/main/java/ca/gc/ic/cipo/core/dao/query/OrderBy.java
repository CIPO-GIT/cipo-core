package ca.gc.ic.cipo.core.dao.query;

/**
 * This is a simple class that defines the order by to return a 
 * search results.
 * 
 * Usage example:<br/>
 * 
 * <code>// Order by title ascending<br/>
 * OrderBy orderByName = new OrderBy("name");</code><br/>
 *  
 * <code>// Order by title descending<br/> 
 * OrderBy orderByName = new OrderBy("name", false); </code><br/>
 *
 * @author DenisJ1
 */
public class OrderBy {

    /**
     * Name of the property field in the persisted object.
     */
    public String property;

    /**
     * Flag indicating the ordering value. If set to true the return 
     * order is ascending, if set to false the return order is descending
     */
    public boolean ascending = true;

    
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public boolean isAscending() {
        return ascending;
    }
    
    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    /**
     * A simple order for a property which is ascending
     * @param property The name of the field (property) in the persisted object.
     */
    public OrderBy(String property) {
        this.property = property;
        this.ascending = true;
    }

    /**
     * Define an order for a property.
     * 
     * @param property The name of the field (property) in the persisted object
     * @param ascending Ascending order (true is ascending, false is descending).
     */
    public OrderBy(String property, boolean ascending) {
        this.property = property;
        this.ascending = ascending;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ascending ? 1231 : 1237);
		result = prime * result
				+ ((property == null) ? 0 : property.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderBy other = (OrderBy) obj;
		if (ascending != other.ascending)
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [property=" + property + ", ascending=" + ascending + "]";
	} 
}
