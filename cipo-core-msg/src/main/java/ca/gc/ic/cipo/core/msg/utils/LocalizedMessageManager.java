package ca.gc.ic.cipo.core.msg.utils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;

import org.apache.log4j.Logger;

/**
 * The LocalizedMessageManager provides methods for retrieving localized messages 
 * and adding custom localized message providers. This class should not be called 
 * directly for other purposes than registering a custom LocalizedMessageProvider 
 * or retrieving information about a message.
 *  
 * You can call getMessage directly but first ensure that the given entry key 
 * really exists in one of the registered providers.  In case of a message cannot
 * be found, the exception MessageNotFoundException will be thrown.
 * 
 * @see LocalizedMessageProvider
 * @author J.Denis
 */
public class LocalizedMessageManager {

	/** Log4J logger. */
	private final transient Logger logger = Logger.getLogger(LocalizedMessageManager.class);

	private static LocalizedMessageManager instance = null;

	/** List of providers where the key is providerId and the value the provider itself. */
	private HashMap<String, LocalizedMessageProvider> providers = 
		new HashMap<String, LocalizedMessageProvider>();

	/** Factory used to create the provider. */
	private LocalizedMessageProviderFactory factory;

	/**
	 * Return the factory used to create LocalizedMessageProvider.  If not
	 * already set, return the default one which is a LocalizedMessageBundleFactory.
	 * 
	 * @return A reference on the LocalizedMessageRepositoryFactory.
	 */
	public LocalizedMessageProviderFactory getFactory() {
		if (null == factory) {
			// If no factory setup, let's create a default one.
			factory = new LocalizedMessageBundleFactory();
		}
		return factory;
	}

	/**
	 * Set the factory used to create LocalizedMessageProvider.
	 *  
	 * @param factory The factory to create the LocalizedMessageProvider.
	 */
	public void setFactory(LocalizedMessageProviderFactory factory) {
		this.factory = factory;
	}

	public String getDefaultMessageProvider() {
		return CipoMessage.DEFAULT_MESSAGE_PROVIDER;
	}
	
	public String getDefaultErrorMessageProvider() {
		return CipoErrorMessage.DEFAULT_ERR_MESSAGE_PROVIDER;
	}

	/**
	 * Return the message based on the given message identifier, the provider 
	 * identifier and the language locale.
	 *   
	 * @param providerId Provider identifier.
	 * @param msgId Message identifier.
	 * @param locale The language locale (FRENCH or ENGLISH).
	 * 
	 * @return A string representing the message.
	 */
	public String getMessage(String providerId, MessageId msgId, Locale locale) {

		LocalizedMessageProvider provider = null;

		if (providerId != null) {		
			provider = get(providerId);
		}

		// Do we have a matching case ?
		if (provider == null) {
			// Try to create one...
			provider = create(providerId);
			if (provider == null) {
				throw new RuntimeException("The message provider Id = '" + providerId + "' " 
						+ " was not found in the list of providers for locale '" 
						+ locale.getLanguage() + "' and a new instance cannot be created...");
			} 
		}			
		return provider.getMessage(msgId, locale);			
	}
		
	public String getMessage(LocalizedMessage message, Locale locale, Object... args) {
		return getMessage(message.getProviderId(), message.getMsgId(), locale, args);
	}

	public String getMessage(MessageId msgId, Locale locale, Object... args) {
		return getMessage(getDefaultMessageProvider(), msgId, locale, args);
	}
	
	public String getErrorMessage(MessageId msgId, Locale locale, Object... args) {
		return getMessage(getDefaultErrorMessageProvider(), msgId, locale, args);
	}
	
	public String getMessage(String key, Locale locale, Object... args) {
		TMessageId<String> msgId = new TMessageId<String>(key);
		return getMessage(getDefaultMessageProvider(), msgId, locale, args);
	}
	
	public String getErrorMessage(String key, Locale locale, Object... args) {
		TMessageId<String> msgId = new TMessageId<String>(key);
		return getMessage(getDefaultErrorMessageProvider(), msgId, locale, args);
	}
	
	public String getMessage(String providerId, String key, Locale locale, Object... args) {
		TMessageId<String> msgId = new TMessageId<String>(key);
		return getMessage(providerId, msgId, locale, args);
	}
	
	public String getErrorMessage(String providerId, String key, Locale locale, Object... args) {
		TMessageId<String> msgId = new TMessageId<String>(key);
		return getMessage(providerId, msgId, locale, args);
	}
	
	/**
	 *  Return the message based on the given message identifier, the provider 
	 *  identifier, the language locale, and a list of variable arguments used 
	 *  for message formatting. 
	 * 
	 * 
	 * @param msgId Message identifier.
	 * @param providerId Provider identifier.
	 * @param locale The language locale (FRENCH or ENGLISH).
	 * @param args The list of variable arguments for formatting the message.
	 * 
	 * @return A string representing the message.
	 */
	public String getMessage(String providerId, MessageId msgId, Locale locale, Object... args) {
		String message = getMessage(providerId, msgId, locale);
		return (args == null) ? message : MessageFormat.format(message, args);
	}


	/**
	 * Return the LocalizedMessageProvider matching the given provider identifier 
	 * and language locale.
	 * 
	 * @param providerId Provider identifier.
	 * 
	 * @return A reference on a LocalizedMessageProvider if found, otherwise null. 
	 */
	public LocalizedMessageProvider get(String providerId) {		
		return providers.get(providerId);
	}


	/**
	 * Add the given LocalizedMessageProvider into the list of providers using the given
	 * provider id.  If an existing LocalizedMessageProvider is already associated to the 
	 * given provider id, it will be replace with the new one.
	 * 
	 * @param providerId Provider identifier.
	 * @param provider The new LocalizedMessageProvider to be added. 
	 */
	public void add(String providerId, LocalizedMessageProvider provider) {			
		logger.debug("Adding message provider = " + providerId);
		// Do we already have an LocalizedMessageProvider for a particular providerId ?
		if (providers.containsKey(providerId)) {
			logger.warn("Existing LocalizedMessageProvider " + providerId 
					+ " is beeing replaced by a new one...");
		}		
		providers.put(providerId, provider);
	}

	/**
	 * Remove from the list of providers the given provider id. 
	 * 
	 * @param providerId The Provider id.
	 */
	public void remove(String providerId) {
		logger.debug("Removing message provider = " + providerId);
		providers.remove(providerId); 			
	}

	/**
	 * Create a new LocalizedMessageProvider based on the given provider Id and
	 * add it to the list of providers for later access.
	 * 
	 * @param providerId The provider identifier.
	 *  
	 * @return A reference on the newly created LocalizedMessageProvider.
	 */
	public LocalizedMessageProvider create(String providerId) {
		logger.debug("Creating a new message provider = " + providerId);
		LocalizedMessageProvider repository = getFactory().create(providerId);
		add(providerId, repository);
		return repository;
	}

	public static LocalizedMessageManager getInstance() {
		if (instance == null) {
			instance = new LocalizedMessageManager();
		}

		return instance;
	}

	public static LocalizedMessageManager createInstance(LocalizedMessageProviderFactory factory) {
		LocalizedMessageManager mgr = getInstance();
		mgr.setFactory(factory);
		return mgr;
	}	
}
