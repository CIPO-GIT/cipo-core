package ca.gc.ic.cipo.msg.tst;

import java.util.Locale;

import junit.framework.TestCase;

import org.junit.Test;


import ca.gc.ic.cipo.core.msg.utils.CipoMessage;
import ca.gc.ic.cipo.core.msg.utils.LocalizedMessageBundleFactory;
import ca.gc.ic.cipo.core.msg.utils.LocalizedMessageManager;
import ca.gc.ic.cipo.core.msg.utils.TMessageId;

public class CipoMessageTest extends TestCase {
	public static final String PROVIDERID = "MyComponentMessages";
	
	@Override
	protected void setUp() throws Exception {

		// Add the default resource bundle into the manager.		
		LocalizedMessageBundleFactory factory = new LocalizedMessageBundleFactory();
		LocalizedMessageManager mgr = LocalizedMessageManager.getInstance();
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
 		CipoMessage msg1 = new CipoMessage(PROVIDERID, "MSG_1");
		assertEquals("CipoMessageTest - testGetMessage(1) result", 
					"Message number 1.", msg1.getMessage(Locale.ENGLISH));
		assertEquals("CipoMessageTest - testGetMessage(1) result", 
				"Message numéro 1.", msg1.getMessage(Locale.FRENCH));

		// Test No. 2 - Don't a valid key.
		TMessageId<Integer> msgId = new TMessageId<Integer>(999);
 		CipoMessage msg2 = new CipoMessage(PROVIDERID, msgId);
		assertEquals("CipoMessageTest - testGetMessage(2) result", 
					"Undefined message ID: 999.", msg2.getMessage(Locale.ENGLISH));
		assertEquals("CipoMessageTest - testGetMessage(2) result", 
				"Message ID: 999 non défini.", msg2.getMessage(Locale.FRENCH));
		
		// Test No. 3 - Lookup for message with arguments (English and French).
		String author = "CIPO";
		CipoMessage msg3 = new CipoMessage(PROVIDERID, "MSG_2", author);
		assertEquals("CipoErrorMessageTest - testGetMessage(3) result", 
				"Message number 2 created by CIPO.", msg3.getMessage(Locale.ENGLISH));
		assertEquals("CipoErrorMessageTest - testGetMessage(3) result", 
				"Message numéro 2 créé par CIPO.", msg3.getMessage(Locale.FRENCH));
		
		// Test No. 4 -Lookup for a simple  message (English and French) by 
		// do not specifying the PROVIDER.
 		CipoMessage msg4 = new CipoMessage("DEFMSG_1");
		assertEquals("CipoMessageTest - testGetMessage(4) result", 
				"Message number 1.", msg4.getMessage(Locale.ENGLISH));
		assertEquals("CipoMessageTest - testGetMessage(4) result", 
				"Message numéro 1.", msg4.getMessage(Locale.FRENCH));
 	}
 	 	
    @Test
 	public void testGetBilingualMessage() {
 		
 		String format = "{0} / {1}";

 		// Test No. 1 - Lookup for a simple bilingual message.
 		CipoMessage msg1 = new CipoMessage(PROVIDERID, "MSG_1");
		assertEquals("CipoMessageTest - testGetBilingualMessage(1) result", 
					"Message number 1. / Message numéro 1.", msg1.getBilingualMessage(format));		
		assertEquals("CipoMessageTest - testGetBilingualMessage(1) result", 
				"Message numéro 1. / Message number 1.", msg1.getBilingualMessage(format, false));
		
		// Test No. 1 - Lookup for a simple bilingual message.
		String author = "CIPO";
		String format2 = "FR = {0} / EN = {1}";
 		CipoMessage msg2 = new CipoMessage(PROVIDERID, "MSG_2", author);
		assertEquals("CipoMessageTest - testGetBilingualMessage(1) result", 
					"Message number 2 created by CIPO. / Message numéro 2 créé par CIPO.", 
					msg2.getBilingualMessage(format));		
		assertEquals("CipoMessageTest - testGetBilingualMessage(1) result", 
				"FR = Message numéro 2 créé par CIPO. / EN = Message number 2 created by CIPO.", 
				msg2.getBilingualMessage(format2, false));
 	} 	
}
