package ca.gc.ic.cipo.security.tst;

import junit.framework.TestCase;

import ca.gc.ic.cipo.core.common.model.RacfUser;
import ca.gc.ic.cipo.core.security.AuthorizeManager;
import ca.gc.ic.cipo.core.security.ResourceAccessLevelCode;

public class AuthorizationManagerTest extends TestCase {

	//@Test
	public void testIsAuthorize() {

		// Use Intellect user.
		RacfUser racfUser0 = new RacfUser("INTEL0", "");
		RacfUser racfUser1 = new RacfUser("INTEL01", "INTEL01");

		String hostName = "devt.ccs.ic.gc.ca";
		String resource = "CONTRACT";
		String invalidResource = "UNKNOWN";
		AuthorizeManager.getInstance().setServiceHostName(hostName);

		// Test No.1 - Use a valid user but no password and a valid resource.
		assertFalse(AuthorizeManager.isAuthorize(racfUser0, resource, 
				ResourceAccessLevelCode.READ_ONLY_PUBLIC));

		// Test No.2 - Use a valid user and password and a valid resource.	
		assertTrue(AuthorizeManager.isAuthorize("INTEL01", "INTEL01", resource, 
				ResourceAccessLevelCode.UPDATE));
		
		assertTrue(AuthorizeManager.isAuthorize("INTEL02", "INTEL02", resource, 
				ResourceAccessLevelCode.DELETE));

		assertTrue(AuthorizeManager.isAuthorize("INTEL03", "INTEL03", resource, 
				ResourceAccessLevelCode.UPDATE));

		assertTrue(AuthorizeManager.isAuthorize("INTEL04", "INTEL04", resource, 
				ResourceAccessLevelCode.READ_ONLY_ALL));

		// Test No.3 - Use a valid user and password and a valid resource but 
		// access level to high.	
		assertFalse(AuthorizeManager.isAuthorize("INTEL04", "INTEL04", resource, 
				ResourceAccessLevelCode.DELETE));	
		
		// Test No.4 - Use a valid user and password with an invalid resource.
		assertFalse(AuthorizeManager.isAuthorize(racfUser1, invalidResource, 
				ResourceAccessLevelCode.UPDATE));

		// Test No.5 - Use a valid user and password with a null resource.
		assertFalse(AuthorizeManager.isAuthorize(racfUser1, null,	
				ResourceAccessLevelCode.READ_ONLY_PUBLIC));
	} 	 	
}
