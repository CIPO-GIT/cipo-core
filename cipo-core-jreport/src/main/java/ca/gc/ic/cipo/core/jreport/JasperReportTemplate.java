package ca.gc.ic.cipo.core.jreport;

import java.io.File;
import java.io.FileInputStream;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import ca.gc.ic.cipo.core.common.report.ReportInfo;
import ca.gc.ic.cipo.core.common.report.ReportTemplate;

public class JasperReportTemplate extends ReportTemplate {
	
	public JasperReportTemplate(File mainReport) {
		super(mainReport);
	}

}
