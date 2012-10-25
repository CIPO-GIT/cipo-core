package ca.gc.ic.cipo.core.msg.utils;

/**
 * 
 * LocalizedMessageProviderFactory is an interface use to create LocalizedMessageProvider.
 * LocalizedMessageProvider are created based on a provider identifier.
 *   
 * @see LocalizedMessageProvider   
 * @author J.Denis
 *
 */
public interface LocalizedMessageProviderFactory {
		
	/**
	 * Create a LocalizedMessageProvider containing localized messages. 
	 *  
	 * @param providerId Repository identifier.
	 * 
	 * @return An instance LocalizedMessageProvider.
	 */
	public LocalizedMessageProvider create(String providerId);
}
