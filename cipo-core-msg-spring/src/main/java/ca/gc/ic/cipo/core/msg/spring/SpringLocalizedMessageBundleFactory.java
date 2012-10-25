package ca.gc.ic.cipo.core.msg.spring;

import org.springframework.context.support.ResourceBundleMessageSource;

import ca.gc.ic.cipo.core.msg.utils.LocalizedMessageProviderFactory;

/**
 * SpringLocalizedMessageBundleFactory is the concrete class to create a
 * SpringLocalizedMessageBundle. 
 *   
 * @see LocalizedMessageProviderFactory
 * @author J.Denis
 *
 */
public class SpringLocalizedMessageBundleFactory implements LocalizedMessageProviderFactory {
		
	public SpringLocalizedMessageBundleFactory() {
		super();
	}
	
	@Override
	public SpringLocalizedMessageBundle create(String providerId) {
		
		// First let`s create a ResourceBundleMessageSource.
		ResourceBundleMessageSource msgSource = new ResourceBundleMessageSource();
		msgSource.setBasename(providerId);
		
		// And then the bundle.
		return new SpringLocalizedMessageBundle(providerId, msgSource);
	}
}
