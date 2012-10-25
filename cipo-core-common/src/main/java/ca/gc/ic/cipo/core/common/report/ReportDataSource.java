package ca.gc.ic.cipo.core.common.report;

/**
 * ReportDataSouce class is the interface class responsible of accessing and 
 * retrieving data using a DAO.  
 *  
 * @author DenisJ1
 */
public interface ReportDataSource {

	public Object firstValue();

	public Object nextValue(); 

	public Object currentValue();	
}
