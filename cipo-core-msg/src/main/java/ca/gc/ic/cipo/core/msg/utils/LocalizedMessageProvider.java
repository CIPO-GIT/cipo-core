package ca.gc.ic.cipo.core.msg.utils;

import java.util.Locale;

/**
 * 
 * LocalizedMessageProvider is an interface class representing a 
 * localized message provider where pre-defined messages exists.  These messages 
 * could be localised based on a given locale.
 *
 * @author J.Denis
 *
 */
public interface LocalizedMessageProvider {	
	
	public String getMessage(MessageId msgId, Locale locale);	
}
