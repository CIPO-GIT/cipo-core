package ca.gc.ic.cipo.msg.tst;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;
import ca.gc.ic.cipo.core.msg.exception.CipoException;
import ca.gc.ic.cipo.core.msg.utils.CipoErrorMessage;
import ca.gc.ic.cipo.core.msg.utils.ErrorMessageId;
import ca.gc.ic.cipo.core.msg.utils.MessageId;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration( locations = { "classpath:jUnit-applicationContext.xml" })
public class CipoExceptionTest extends TestCase {

	public static final String PROVIDERID = "MyComponentErrors";
	public static final String TST_PROVIDERID = "TestComponentErrors";
		    
    @Test
 	public void testGetMessage() {
 		
 		// (1) - Raised a CIPOException by defining a CIPOErrorMessage and an ErrorMesssageId.
 		MessageId msgId = new ErrorMessageId("CIPO", -99, 1);
 		CipoErrorMessage msg = new CipoErrorMessage(PROVIDERID, msgId);
		try {			
			throw new CipoException(msg);
		} catch (CipoException e) {				
			assertEquals("CipoExceptionTest - testGetMessage(1) result", 
				"Spring Exception number 1.", e.getMessage());
		}		
		
 		// (2) - Raised an CIPOException by defining a CIPOErrorMessage using the bundle ComponentErrors.
		String author = "CIPO";
		msg = new CipoErrorMessage(PROVIDERID, "CIPO", -99, 2, author);
		try {
			throw new CipoException(msg);
		} catch (CipoException e) {				
			assertEquals("CipoExceptionTest - testGetMessage(2) result", 
				"Spring Exception number 2 created by CIPO.", e.getMessage());
		}
		
		// (3) - Raised a CIPOException by defining using the resource bundle ComponentErrors.
		String onDate = "Tuesday morning";
		try {
			throw new CipoException(PROVIDERID, "CIPO", -99, 3, author, onDate);
		} catch (CipoException e) {				
			assertEquals("CipoExceptionTest - testGetMessage(3) result", 
				"Spring Exception number 3 created by CIPO on Tuesday morning.", e.getMessage());
		}
		
		// (4) - Raised a CIPOException by not defining a resource bundle.  
		try {
			throw new CipoException("CIPO", -99, 1);
		} catch (CipoException e) {				
			assertEquals("CipoExceptionTest - testGetMessage(4) result", 
				"Undefined message ID: CIPO.-99.1.", e.getMessage());
		}
	}
 	
    @Test
	public void testGetEnglishMessage() {
		
		// (1) - Raised a CIPOException using the default resource bundle.
		try {			
			throw new CipoException("CIPO", -99, 1);
		} catch (CipoException e) {				
			assertEquals("CipoExceptionTest - testGetEnglishMessage(1) result", 
				"Undefined message ID: CIPO.-99.1.", e.getEnglishMessage());
		}	
		
		// (2) - Raised a CIPOException using the given resource bundle.
		String author = "CIPO";
		String onDate = "Wednesday";
		try {			
			throw new CipoException(TST_PROVIDERID, "TEST", -1, 3, author, onDate);
		} catch (CipoException e) {				
			assertEquals("CipoExceptionTest - testGetEnglishMessage(2) result", 
				"Spring Test Exception number 3 created by CIPO on Wednesday.", e.getEnglishMessage());
		}	
		
		// (3) - Raised a CIPOException using the Exception base class message.
		try {			
			throw new CipoException("Spring This is a CIPOException");
		} catch (CipoException e) {				
			assertEquals("CipoExceptionTest - testGetEnglishMessage(3) result", 
				"Spring This is a CIPOException", e.getEnglishMessage());
		}
	}
	
    @Test
	public void testGetFrenchMessage() {
		// (1) - Raised a CIPOException using the default resource bundle.
		try {			
			throw new CipoException("CIPO", -99, 3);
		} catch (CipoException e) {				
			assertEquals("CipoExceptionTest - testGetFrenchMessage(1) result", 
				"Message ID: CIPO.-99.3 non défini.", e.getFrenchMessage());
		}	
		
		// (2) - Raised a CIPOException using the given resource bundle.
		String author = "CIPO";
		try {			
			throw new CipoException(TST_PROVIDERID, "TEST", -1, 2, author);
		} catch (CipoException e) {				
			assertEquals("CipoExceptionTest - testGetFrenchMessage(2) result", 
				"Spring Exception test numéro 2 créé par CIPO.", e.getFrenchMessage());
		}	
		
		// (3) - Raised a CIPOException using the Exception base class message.
		try {			
			throw new CipoException("Spring This is a Test CIPOException");
		} catch (CipoException e) {				
			assertEquals("CipoExceptionTest - testGetFrenchMessage(3) result", 
				"Spring This is a Test CIPOException", e.getFrenchMessage());
		}
	}
}
