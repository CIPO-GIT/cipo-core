/* Copyright (c) 2010 CIPO
 * Created on 2010-08-04
 */
package ca.gc.ic.cipo.core.common.model;

/**
 * This simple model class represents the user name
 * and password of a CIPO employee with a RACF mainframe account.
 * 
 * @author D.Rodrigues
 * @version 1.0
 */
public class RacfUser {
	
	private String userId;
	private String password;
	
	/**
	 * Constructor.
	 * 
	 * @param userId the RACF user id
	 * @param password the RACF password
	 */
	public RacfUser(String userId, String password) {
		this.userId = userId.toUpperCase();
		this.password = password;
	}

	/**
	 * Gets the defined RACF User id.
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Gets the user's RACF password.
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
}
