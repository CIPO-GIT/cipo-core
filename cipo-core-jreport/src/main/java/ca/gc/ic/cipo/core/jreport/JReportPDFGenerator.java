package ca.gc.ic.cipo.core.jreport;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 * JasperReportPDFGenerator is used to generate JasperReport in a PDF format.
 * It inherits from the abstract class JasperReportGenerator.
 * 
 * @author DenisJ1
 * 
 */
public class JReportPDFGenerator extends JReportGenerator {

	@Override
	public JRExporter getExporter() {
		return new JRPdfExporter();
	}
}
