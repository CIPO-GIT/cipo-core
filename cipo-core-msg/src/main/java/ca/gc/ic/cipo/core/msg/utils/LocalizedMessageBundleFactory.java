package ca.gc.ic.cipo.core.msg.utils;


/**
 * 
 * LocalizedMessageBundleFactory is the concrete class to create LocalizedMessageBundle.
 *   
 * @see LocalizedMessageProviderFactory
 * @author J.Denis
 *
 */
public class LocalizedMessageBundleFactory implements LocalizedMessageProviderFactory {
		
	public LocalizedMessageProvider create(String providerId) {
		return new LocalizedMessageBundle(providerId);
	}
}
