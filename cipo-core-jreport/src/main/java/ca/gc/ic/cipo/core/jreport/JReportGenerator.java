package ca.gc.ic.cipo.core.jreport;

import java.io.OutputStream;
import java.io.Writer;
import java.util.Collection;

import ca.gc.ic.cipo.core.common.report.ReportGenerator;
import ca.gc.ic.cipo.core.common.report.ReportInfo;
import ca.gc.ic.cipo.core.common.report.ReportTemplate;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataSourceProvider;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


/**
 * JasperReportGenerator is an abstract class that offers basic utility 
 * methods that help generating report using JasperReport.  Implementors
 * simply have to override the method getExporter(). 
 *
 * @author DenisJ1
 * 
 */
public abstract class JReportGenerator implements ReportGenerator {
	
	/**
	 * Convert the given report data value to a <code>JRDataSource.
	 * 
	 * <p>In the default implementation, a JRDataSource,
	 * <code>java.util.Collection or object array is detected.
	 * The latter are converted to <code>JRBeanCollectionDataSource
	 * or <code>JRBeanArrayDataSource, respectively.
	 * 
	 * @param value The report data value to convert.
	 * @return The JRDataSource
	 * @throws IllegalArgumentException if the value could not be converted
	 * @see net.sf.jasperreports.engine.JRDataSource
	 * @see net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
	 * @see net.sf.jasperreports.engine.data.JRBeanArrayDataSource
	 */
	protected JRDataSource convertReportData(Object value) throws IllegalArgumentException {
		if (value instanceof JRDataSource) {
			return (JRDataSource) value;
		}
		else if (value instanceof Collection) {
			return new JRBeanCollectionDataSource((Collection<?>) value, false);
		}
		else if (value instanceof Object[]) {
			return new JRBeanArrayDataSource((Object[]) value, false);
		}
		else if (value instanceof JRDataSourceProvider) {
			return null;
		}
		else {
			throw new IllegalArgumentException("Value [" + value + "] " 
					+ "cannot be converted to a JRDataSource");
		}
	}

	/**
	 * Return the Compile version of the template report. Usually this is
	 * the .jasper file and NOT the .jrxml file.
	 * 
	 * @param reportTemplate The report template name (.jrxml file). 
	 *                       It must be in the classpath.
	 *                        
	 * @return A reference to a JasperReport on success.
	 * 
	 * @throws JRException A JRException if anything goes wrong.
	 */
	protected JasperReport getCompileReport(String reportTemplate) throws JRException {
		JasperDesign template = JRXmlLoader.load(reportTemplate);
		return JasperCompileManager.compileReport(template);
	}
		
	/**
	 * Fill the given report with the data provided. 
	 * 
	 * @param report The report to fill.
	 * @param reportData The data to fill the report with.  It must be one
	 * 					 of the following type JRDataSource, java.lang.Collection, 
	 * 					 Object[], JRDataSourceProvider
	 *                        
	 * @return A reference to a JasperPrint on success.
	 * 
	 * @throws IllegalArgumentException if anything goes wrong.
	 * @throws JRException if anything goes wrong.
	 */
	@SuppressWarnings("unchecked")
	protected JasperPrint fillReport(ReportInfo report, Object reportData) throws IllegalArgumentException, JRException {
		ReportTemplate template = report.getTemplate();				
	    JasperReport jasperReport = getCompileReport(template.getMainReport().getPath());	
		return JasperFillManager.fillReport(jasperReport, report.getParameters(), convertReportData(reportData));
	}
	
	/**
	 * Generate the report according to the implementor format 
	 * using the supplied report data and writes the results to 
	 * the supplied <code>OutputStream.
	 * 
	 * @param report The JasperReport instance to generate.
	 * @param stream The OutputStream to write the generated report to.
	 * @param reportData A JRDataSource, java.util.Collection,
	 *    object array (converted accordingly) or a JRDataSourceProvider 
	 *    representing the report data to read fields from.
	 * @throws JRException if generation failed.
	 */
	@Override
	public void generate(ReportInfo report, Object reportData, OutputStream stream) throws JRException {
		JasperPrint print = fillReport(report, reportData);
		JRExporter exporter = getExporter(); 
		generate(exporter, print, stream);
	}
	
	/**
	 * Generate the report according to the implementor format 
	 * using the supplied report data and writes the results to 
	 * the supplied <code>Writer.
	 * 
	 * @param report The JasperReport instance to generate.
	 * 
	 * @param reportData A JRDataSource, java.util.Collection,
	 *    object array (converted accordingly) or a JRDataSourceProvider 
	 *    representing the report data to read fields from.
	 * @param writer Writes the results to the supplied Writer. * 
     *
	 * @throws JRException If rendering failed.
	 */
	@Override
	public void generate(ReportInfo report, Object reportData, Writer writer) throws JRException {
		JasperPrint print = fillReport(report, reportData);
		JRExporter exporter = getExporter();
		generate(exporter, print, writer);
	}
	
	/**
	 * Generate the supplied <code>JasperPrint instance using the supplied 
	 * <code>JRExporter instance and write the results to the supplied <code>Writer. 
	 * 
	 * @param exporter The <code>JRExporter to use to render the report.
	 * @param print The <code>JasperPrint instance to render.
	 * @param writer The <code>Writer to write the result to.
	 * @throws JRException If rendering failed.
	 */
	protected void generate(JRExporter exporter, JasperPrint print, Writer writer) throws JRException {
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, writer);
		exporter.exportReport();
	}

	/**
	 * Generate the supplied <code>JasperPrint instance using the supplied 
	 * <code>JRExporter instance and write the results to the supplied 
	 * <code>OutputStream.
	 * 
	 * @param exporter The <code>JRExporter to use to render the report.
	 * @param print The <code>JasperPrint instance to render.
	 * @param outputStream The <code>OutputStream to write the result to.
	 * @throws JRException If rendering failed.
	 */
	protected void generate(JRExporter exporter, JasperPrint print, OutputStream outputStream) throws JRException {
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.exportReport();
	}
	
	/**
	 * Return the type of Jasper Report exporter to generate to report.
	 *  
	 * @return A reference to the Jasper Report exporter format.
	 */
	public abstract JRExporter getExporter();
}
