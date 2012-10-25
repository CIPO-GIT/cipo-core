package ca.gc.ic.cipo.core.common.report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// TODO - Add ReportTemplate documentation.
// TODO - Retrieve the compile report if it exists (.jasper instead of .jrxml).
//        May need to create a sub-class for specific JasperReport template.
public class ReportTemplate {

	private File mainReport;
	private List<File> subReports = new ArrayList<File>();
	
	public ReportTemplate(File mainReport) {
		super();
		this.mainReport = mainReport;
	}
	
	public ReportTemplate(String mainReport) {
		this.mainReport = new File(mainReport);
	}

	public File getMainReport() {
		return mainReport;
	}

	public void setMainReport(File mainReport) {
		this.mainReport = mainReport;
	}
	
	public void addSubReport(File subReport) {
		subReports.add(subReport);
	}
}
