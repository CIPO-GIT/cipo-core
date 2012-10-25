package ca.gc.ic.cipo.core.msg.spring;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;

import ca.gc.ic.cipo.core.msg.utils.LocalizedMessageProvider;
import ca.gc.ic.cipo.core.msg.utils.MessageId;

/**
 * SpringLocalizedMessageBundle is a class wrapper that retrieve messages from a 
 * resource bundle using Spring API via MessageSource. The message is localised 
 * using the MessageId(key) within the resource bundle based on the given locale.
 *   
 * @see LocalizedMessageProvider
 * @author J.Denis
 *
 */
public class SpringLocalizedMessageBundle implements LocalizedMessageProvider {

	/** Log4J logger. */
	private final transient Logger logger = Logger.getLogger(SpringLocalizedMessageBundle.class);

	/** The Spring Message Source. */
	private MessageSource msgSource; 

	/** Base name of the resource bundle without the locale. */
	private String bundleName;

	/**
	 * Build a LocalizedMessageBundle using the given name and locale.
	 * 
	 * @param bundleName Name of the resource bundle.
	 * @param locale Language locale.
	 */
	public SpringLocalizedMessageBundle(String bundleName, MessageSource msgSource) {
		super();
		this.bundleName = bundleName;
		this.msgSource = msgSource;		
	}

	public String getBundleName() {
		return bundleName;
	}

	@Override
	public String getMessage(MessageId msgId, Locale locale) {

		String msg = "";
		try {
			// No arguments for the moment...  It will be formatted later on.
			msg = msgSource.getMessage(msgId.getId(), null, locale);
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
