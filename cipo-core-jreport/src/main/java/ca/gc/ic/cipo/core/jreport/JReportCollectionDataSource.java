package ca.gc.ic.cipo.core.jreport;

import java.util.Collection;

import ca.gc.ic.cipo.core.common.report.ReportCollection;


import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JReportCollectionDataSource extends JRBeanCollectionDataSource implements ReportCollection {

	public JReportCollectionDataSource(Collection<?> reportData) {
		super(reportData);
	}
}
