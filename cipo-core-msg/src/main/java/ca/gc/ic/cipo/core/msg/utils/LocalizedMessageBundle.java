package ca.gc.ic.cipo.core.msg.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * 
 * LocalizedMessageBundle is a class that retrieve messages from a resource bundle.
 * The message is localised using the MessageId(key) within the resource bundle based 
 * on the given locale.
 *   
 * @see LocalizedMessageProvider
 * @author J.Denis
 *
 */
public class LocalizedMessageBundle implements LocalizedMessageProvider {

	/** Log4J logger. */
	private final transient Logger logger = Logger.getLogger(LocalizedMessageBundle.class);

	/** The property resource bundle. */
	private HashMap<Locale, PropertyResourceBundle> bundles = 
		new HashMap<Locale, PropertyResourceBundle>(); 

	/** Base name of the resource bundle without the locale. */
	private String bundleName;

	/**
	 * Build a LocalizedMessageBundle using the given name.
	 * 
	 * @param bundleName Name of the resource bundle.
	 */
	public LocalizedMessageBundle(String bundleName) {
		super();
		//this.bundle = null;
		this.bundleName = bundleName;
		logger.debug("Building a LocalizedMessageBundle named = " + bundleName);
	}

	private String getMessage(String id, Locale locale) {

		// If resource bundle not yet initialised, let's do it and cache it.
		PropertyResourceBundle bundle = bundles.get(locale);
		if (bundle == null) {
			ClassLoader loader = LocalizedMessageBundle.class.getClassLoader();	
			bundle = (PropertyResourceBundle) 
				ResourceBundle.getBundle(bundleName, locale, loader);
			bundles.put(locale, bundle);
		}
		return bundle.getString(id);
	}

	@Override
	public String getMessage(MessageId msgId, Locale locale) {

		String msg = "";
		try {
			msg = getMessage(msgId.getId(), locale);
		} catch (Exception e) {
			logger.error("Undefined message ID: " + msgId.getId(), e);

			// Let's returned a special message in French if it is the locale used, 
			// otherwise, return it in English.
			if (locale == Locale.FRENCH) { 
				msg = "Message ID: " + msgId.getId() + " non défini.";
			} else {
				msg = "Undefined message ID: " + msgId.getId() + ".";
			}
		}
		return msg;
	}

}
