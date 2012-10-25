package ca.gc.ic.cipo.core.common.lookup;

import java.util.Locale;

public interface SystemCode {
	
	public String getSystemCode();
	public String getSystemCodeType();
	public String getEnglishCodeValue();
	public String getFrenchCodeValue();
	public String getCodeValue(Locale locale);
}
