package ca.gc.ic.cipo.core.common.report;

import java.util.HashMap;
import java.util.Map;

/**
 * ReportInfo is a basic class that encapsulate information
 * related to a report.  The report information encapsulates 
 * custom parameters using key, value pairs.  
 * 
 * For example one of the custom parameter could be a title.  
 * In this case, the key parameter would be a string with
 * value "title" and its corresponding value would be
 * "This is My Report Title".   
 * 
 * @author DenisJ1
 */
@SuppressWarnings("rawtypes")
public class ReportInfo {

	private Map parameters;
	private ReportTemplate template;

	public ReportInfo(ReportTemplate template) {
		super();
		this.parameters = new HashMap();
		this.template = template;
	}
	
	public ReportInfo(Map parameters, ReportTemplate template) {
		super();
		this.parameters = parameters;
		this.template = template;
	}

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	public ReportTemplate getTemplate() {
		return template;
	}
	
	public void setTemplate(ReportTemplate template) {
		this.template = template;
	}
}
