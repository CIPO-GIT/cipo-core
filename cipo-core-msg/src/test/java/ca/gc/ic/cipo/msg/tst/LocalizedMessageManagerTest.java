package ca.gc.ic.cipo.msg.tst;

import java.util.Locale;

import junit.framework.TestCase;

import org.junit.Test;


import ca.gc.ic.cipo.core.msg.utils.LocalizedMessageBundleFactory;
import ca.gc.ic.cipo.core.msg.utils.LocalizedMessageManager;

public class LocalizedMessageManagerTest extends TestCase {
	public static final String PROVIDERID = "MyComponentMessages";
	private LocalizedMessageManager mgr = LocalizedMessageManager.getInstance();
	
	@Override
	protected void setUp() throws Exception {

		// Add the default resource bundle into the manager.		
		LocalizedMessageBundleFactory factory = new LocalizedMessageBundleFactory();
		mgr.setFactory(factory);
		mgr.add(PROVIDERID, factory.create(PROVIDERID));
		
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
    @Test    
 	public void testGetMessage() {

		// Test No. 1 - Lookup for a simple message (English and French).    	
 		assertEquals("LocalizedMessageManagerTest - testGetMessage(1) result", 
					"Message number 1.", mgr.getMessage(PROVIDERID, "MSG_1", Locale.ENGLISH));
		assertEquals("LocalizedMessageManagerTest - testGetMessage(1) result", 
				"Message numéro 1.", mgr.getMessage(PROVIDERID, "MSG_1", Locale.FRENCH));

		// Test No. 2 - Don't a valid key.
		assertEquals("LocalizedMessageManagerTest - testGetMessage(2) result", 
					"Undefined message ID: XYZ.", mgr.getMessage(PROVIDERID, "XYZ", Locale.ENGLISH));
		assertEquals("LocalizedMessageManagerTest - testGetMessage(2) result", 
				"Message ID: XYZ non défini.", mgr.getMessage(PROVIDERID, "XYZ", Locale.FRENCH));
		
		// Test No. 3 - Lookup for message with arguments (English and French).
		String author = "CIPO";
		assertEquals("LocalizedMessageManagerTest - testGetMessage(3) result", 
				"Message number 2 created by CIPO.", mgr.getMessage(PROVIDERID, "MSG_2", Locale.ENGLISH, author));
		assertEquals("LocalizedMessageManagerTest - testGetMessage(3) result", 
				"Message numéro 2 créé par CIPO.", mgr.getMessage(PROVIDERID, "MSG_2", Locale.FRENCH, author));
		
		// Test No. 4 -Lookup for a simple  message (English and French) by 
		// do not specifying the PROVIDER.
		assertEquals("LocalizedMessageManagerTest - testGetMessage(4) result", 
				"Message number 1.", mgr.getMessage("DEFMSG_1", Locale.ENGLISH));
		assertEquals("LocalizedMessageManagerTest - testGetMessage(4) result", 
				"Message numéro 1.", mgr.getMessage("DEFMSG_1", Locale.FRENCH));
 	} 	 	
}
