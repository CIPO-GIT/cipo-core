package ca.gc.ic.cipo.core.common.lookup;

import java.util.Locale;

public class SystemCodeElement implements SystemCode {
	
	private String systemCode;
	private String systemCodeType;
	private String englishCodeValue;
	private String frenchCodeValue;	
	
	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getSystemCodeType() {
		return systemCodeType;
	}

	public void setSystemCodeType(String systemCodeType) {
		this.systemCodeType = systemCodeType;
	}

	public String getEnglishCodeValue() {
		return englishCodeValue;
	}

	public void setEnglishCodeValue(String englishCodeValue) {
		this.englishCodeValue = englishCodeValue;
	}

	public String getFrenchCodeValue() {
		return frenchCodeValue;
	}
	
	public void setFrenchCodeValue(String frenchCodeValue) {
		this.frenchCodeValue = frenchCodeValue;
	}

	public String getCodeValue(Locale locale) {
		if (locale == Locale.ENGLISH) {
			return getEnglishCodeValue();
		} else if (locale == Locale.FRENCH) {
			return getFrenchCodeValue();
		} else {
			throw new IllegalArgumentException("The given locale (" + locale.getDisplayLanguage() 
					+ ")  is not supported...");
		}
	}

	public SystemCodeElement() {
		this(null, null, null, null);
	}
	
	public SystemCodeElement(String systemCode, String systemCodeType,
			String englishCodeValue, String frenchCodeValue) {
		super();
		this.systemCode = systemCode;
		this.systemCodeType = systemCodeType;
		this.englishCodeValue = englishCodeValue;
		this.frenchCodeValue = frenchCodeValue;
	}
	
	
}
