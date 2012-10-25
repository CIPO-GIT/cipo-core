package ca.gc.ic.cipo.msg.tst;

import java.util.Locale;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.gc.ic.cipo.core.msg.utils.CipoErrorMessage;
import ca.gc.ic.cipo.core.msg.utils.ErrorMessageId;
import ca.gc.ic.cipo.core.msg.utils.MessageId;
import ca.gc.ic.cipo.core.msg.utils.TMessageId;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration( locations = { "classpath:jUnit-applicationContext.xml" })
public class CipoErrorMessageTest extends TestCase {

	public static final String PROVIDERID = "MyComponentErrors";
	public static final String TST_PROVIDERID = "TestComponentErrors";
	
	@Test
 	public void testGetMessage() {

		// Test No. 1 -Lookup for a simple message (English and French).
 		MessageId msgId = new ErrorMessageId("CIPO", -99, 1);
 		CipoErrorMessage msg1 = new CipoErrorMessage(PROVIDERID, msgId);
		assertEquals("CipoErrorMessageTest - testGetMessage(1) result", 
					"Spring Exception number 1.", msg1.getMessage(Locale.ENGLISH));
		assertEquals("CipoErrorMessageTest - testGetMessage(1) result", 
				"Spring Exception numéro 1.", msg1.getMessage(Locale.FRENCH));

		// Test No. 2 - Don't provide a PROVIDER id.
 		CipoErrorMessage msg2 = new CipoErrorMessage(msgId);
		assertEquals("CipoErrorMessageTest - testGetMessage(2) result", 
					"Undefined message ID: CIPO.-99.1.", msg2.getMessage(Locale.ENGLISH));
		assertEquals("CipoErrorMessageTest - testGetMessage(2) result", 
				"Message ID: CIPO.-99.1 non défini.", msg2.getMessage(Locale.FRENCH));
		
		// Test No.3 - Don't provide an valid key for the message id. 
		CipoErrorMessage msg3 = new CipoErrorMessage(TST_PROVIDERID, "", -99, 1);
		assertEquals("CipoErrorMessageTest - testGetMessage(3) result", 
				"Undefined message ID: .-99.1.", msg3.getMessage(Locale.ENGLISH));

		// Test No. 4 - Lookup for message with arguments (English and French).
		String author = "CIPO";
		CipoErrorMessage msg4 = new CipoErrorMessage(TST_PROVIDERID, "TEST", -1, 2, author);
		assertEquals("CipoErrorMessageTest - testGetMessage(4) result", 
				"Spring Test Exception number 2 created by CIPO.", msg4.getMessage(Locale.ENGLISH));
		assertEquals("CipoErrorMessageTest - testGetMessage(4) result", 
				"Spring Exception test numéro 2 créé par CIPO.", msg4.getMessage(Locale.FRENCH));
		
		// Test No. 5 - Lookup for a simple message with arguments (English and French).
		TMessageId<String> id = new TMessageId<String>("TEST.-1.2");		
		CipoErrorMessage msg5 = new CipoErrorMessage(TST_PROVIDERID, id, author);
		assertEquals("CipoErrorMessageTest - testGetMessage(5) result", 
				"Spring Test Exception number 2 created by CIPO.", msg5.getMessage(Locale.ENGLISH));
		assertEquals("CipoErrorMessageTest - testGetMessage(5) result", 
				"Spring Exception test numéro 2 créé par CIPO.", msg5.getMessage(Locale.FRENCH));
		
		// Test No. 6 -Lookup for a simple error message (English and French) by 
		// do not specifying the PROVIDER.
 		CipoErrorMessage msg6 = new CipoErrorMessage("ERR", -99, 1);
		assertEquals("CipoErrorMessageTest - testGetMessage(6) result", 
				"Spring Exception number 1.", msg6.getMessage(Locale.ENGLISH));
		assertEquals("CipoErrorMessageTest - testGetMessage(6) result", 
				"Spring Exception numéro 1.", msg6.getMessage(Locale.FRENCH));
 	}
}
