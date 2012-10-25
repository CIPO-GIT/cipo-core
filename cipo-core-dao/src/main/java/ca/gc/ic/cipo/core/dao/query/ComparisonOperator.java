package ca.gc.ic.cipo.core.dao.query;

/**
 * Define basic Comparison operators using a query language (SQL).
 * 
 * @author DenisJ1
 *
 */
public enum ComparisonOperator {

	EQUAL(" = "), 
	NOT_EQUAL(" <> "),
	LESS_THAN(" < "), 
	LESS_OR_EQ(" <= "),
	GREATER_THAN( " > "), 	 
	GREATER_OR_EQ(" >= "),
	LIKE(" LIKE "), 
	NOT_LIKE(" NOT LIKE ");	

	private final String value;

	private ComparisonOperator(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
