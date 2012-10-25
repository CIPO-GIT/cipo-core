package ca.gc.ic.cipo.core.common.report;

import java.io.OutputStream;
import java.io.Writer;

//TODO - Add documentation.
public interface ReportGenerator {
	
	public void generate(ReportInfo report, Object reportData, OutputStream stream) throws Exception;
	public void generate(ReportInfo report, Object reportData, Writer writer) throws Exception;
	
}
