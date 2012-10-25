package ca.gc.ic.cipo.core.jreport;
import org.apache.commons.beanutils.PropertyUtils;

import ca.gc.ic.cipo.core.common.report.ReportAbstractDataSource;
import ca.gc.ic.cipo.core.common.report.ReportQuery;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * The JReportDataSource class is the Data Source responsible of 
 * accessing data using a ReportQuery (DAO) object and make it compliant 
 * with Jasper Report data source (JRDataSource).  
 *  
 * @author DenisJ1
 *
 * @see ReportAbstractDataSource
 */
public class JReportDataSource extends ReportAbstractDataSource implements JRDataSource  {

	/**
	 * Build a JReportSourceDAO using the given Report Data Source. 
	 * 
	 * @param dataSource The data source form which information is retrieve.
	 */
	public JReportDataSource(ReportQuery dao) {
		super(dao);
	}
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {

		Object property = null;
		try {
			property = PropertyUtils.getProperty(bean, field.getName());
		} catch (Exception e) {
			// Rethrow a better exception.			
		}

		return property;
	}
	
	@Override
	public boolean next() throws JRException {
		Object nextBean = super.nextValue();
		return (nextBean != null);
	}

}
