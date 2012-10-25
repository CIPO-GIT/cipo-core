package ca.gc.ic.cipo.core.security;

import org.apache.log4j.Logger;

import ca.gc.ic.cipo.core.common.model.RacfUser;
import ca.gc.ic.cipo.core.msg.exception.CipoException;
//import ca.gc.ic.cipo.core.msg.exception.CipoException;
import ca.gc.ic.cipo.schema.ServiceResponse;
import ca.gc.ic.cipo.stf.uas.UserAuthService;
import ca.gc.ic.cipo.stf.uas.UserAuthServicePortType;

/**
 * AuthorizeManager is a wrapper utility class used to validate or verify 
 * if a user has the authorisation or not based on given resource and privileges 
 * level.  This class follow the singleton design pattern.
 * 
 * The class simply invoke the existing UserAuthorize Web Service.  
 * The service host name should be given to the manager in order to invoke
 * the web service.   
 * 
 * @author J.Denis
 *
 */
public class AuthorizeManager {

	/** The AuthorizeManager single instance. */
	private static AuthorizeManager mgr = null;

	/** Log4J logger. */
	private static final Logger logger = Logger.getLogger(AuthorizeManager.class);

	/** Host name where the User Authorize Web Service is located. */
	private String serviceHostName = null;

	/** 
	 * Set the host name of the UserAuthorize Web Service.  For example:
	 * localhost:9080 or devt.ccs.ic.gc.ca.
	 * 
	 * @param serviceHostName The host name.
	 */
	public void setServiceHostName(final String serviceHostName) {
		logger.debug("Setting AuthorizeManager service host name = " + serviceHostName);
		this.serviceHostName = serviceHostName; 
	}

	/**
	 * Return the host name of the UserAuthorize Web Service.
	 * 
	 * @return The host name as a string.
	 */
	public String getServiceHostName() {
		return serviceHostName;
	}

	/**
	 * Return the unique instance of the AuthorizeManager.
	 * 
	 * @return A reference on the AuthorizeManager.
	 */
	public static AuthorizeManager getInstance() {
		if (null == mgr) {
			mgr = new AuthorizeManager();
		}
		return mgr;
	}


	/**
	 * Determines if the given RACF user provided has at least enough privileges
	 * to access the specified resource.
	 * 
	 * @param user A valid RACF user.
	 * @param resourceAcronym The resource acronym.
	 * @param accessLevelCode A value of 01 to 04
	 * @return True if the user	has the authority, a CipoException otherwise.
	 */
	public static boolean authorize(RacfUser user, 
			String resourceAcronym, ResourceAccessLevelCode accessLevelCode) {
		return authorize(user.getUserId(), user.getPassword(), 
				resourceAcronym, accessLevelCode);
	}


	/**
	 * Determines if the given RACF user provided has at least enough privileges
	 * to access the specified resource.
	 * 
	 * @param user A valid RACF user.
	 * @param resourceAcronym The resource acronym.
	 * @param accessLevelCode A value of 01 to 04
	 * @return True if the user	has the authority, a CipoException otherwise.
	 */
	public static boolean isAuthorize(RacfUser user, 
			String resourceAcronym, ResourceAccessLevelCode accessLevelCode) {
		return isAuthorize(user.getUserId(), user.getPassword(), 
				resourceAcronym, accessLevelCode);
	}


	/**
	 * Determines if the given user has at least enough privileges
	 * to access the specified resource.
	 * 
	 * @param username The user name.
	 * @param password The password of the user.
	 * @param resourceAcronym The resource acronym.
	 * @param accessLevelCode A value of 01 to 04.
	 * @return True if the user	has the authority, a CipoException otherwise.
	 */
	public static boolean isAuthorize(String username, String password, 
			String resourceAcronym, ResourceAccessLevelCode accessLevelCode) {

		boolean isAuthorize = false;
		try {
			isAuthorize = authorize(username, password, resourceAcronym, accessLevelCode);
		} catch (CipoException e) {
			isAuthorize = false;
		}
		
		return isAuthorize;
	}

	/**
	 * Determines if the given user has at least enough privileges
	 * to access the specified resource.
	 * 
	 * @param username The user name.
	 * @param password The password of the user.
	 * @param resourceAcronym The resource acronym.
	 * @param accessLevelCode A value of 01 to 04.
	 * @return True if the user	has the authority, false otherwise.
	 */
	public static boolean authorize(String username, String password, 
			String resourceAcronym, ResourceAccessLevelCode accessLevelCode) {

		// Ensure requesting user has authority -- default for service.
		logger.debug("Authorizing user " + username + " for access to [" + resourceAcronym 
				+ " : " + (accessLevelCode != null ? accessLevelCode.getValue() : "null") + "]");		

		String servicesHostName = getInstance().getServiceHostName(); 
		UserAuthServicePortType authClient = UserAuthService.getUserAuthService(servicesHostName);

		// Transform into a RacfUser for Web service call.
		ca.gc.ic.cipo.schema.RacfUser racfUser = new ca.gc.ic.cipo.schema.RacfUser();
		racfUser.setUserId(username);
		racfUser.setPassword(password);

		ServiceResponse authResponse = authClient.authorize(racfUser, 
				resourceAcronym, accessLevelCode.getValue());

		if (authResponse.getReturnCode() < 1) {
			// Since authorisation failed, throw an exception which forwards the service response.
			logger.debug("Authorization failed [" + authResponse.getComponentAcronym() + "," 
					+ authResponse.getReturnCode() + "," + authResponse.getReasonCode() + "]");
			throw new CipoException(authResponse.getComponentAcronym(),
					authResponse.getReturnCode(), authResponse.getReasonCode());
		}

		return true;
	}
}
