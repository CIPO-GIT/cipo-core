/* Copyright (c) 2010 CIPO
 * Created on 2011-09-01
 */
package ca.gc.ic.cipo.core.msg.exception;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import ca.gc.ic.cipo.core.msg.utils.CipoErrorMessage;
import ca.gc.ic.cipo.core.msg.utils.MessageId;


/**
 * CIPO's base exception class. It captures a CIPO standard 
 * Return/Response codes which will be reported back to the 
 * caller when errors occur. A human-readable English and 
 * French description can also be returned by looking up 
 * the description in a localised resource lookup which can
 * be a resource bundle, a database, or any other type of resource
 * supporting the interface LocalizedMessageProvider.
 * 
 * Error messages can also be formatted using Java message style 
 * formatting by defining error messages with special tag identifier 
 * used for conversion.  These special tag are used during conversion of 
 * the message substituting each tag with a corresponding value that should
 * be defined in the list of variable arguments that have been passed at 
 * creation of the CIPOErrorMessage.
 *  
 * @author J.Denis
 * @version 1.0
 */
public class CipoException extends RuntimeException {
		
	/** Unique serial ID. */
	private static final long serialVersionUID = 584976742308461626L;
	
	/** Error message. */
	private CipoErrorMessage errMessage;	 
	
	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param message A message explaining the error.
	 */
	// TODO: Do we have to build a default CIPOErrorMessage ?
	public CipoException(String message) {
		this(message, (Throwable) null, (CipoErrorMessage) null);
	}
	
	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param errMessage A CIPO localised error message describing the problem.
	 */
	public CipoException(CipoErrorMessage errMessage) {
		this(null, null, errMessage);
	}

	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param msgId The message identifier.
	 */	 
	public CipoException(MessageId msgId) {
		this(msgId, (Object) null);
	}
	
	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param msgId The message identifier.
	 * @param args The list of variable arguments for formatting the error message.
	 */	 
	public CipoException(MessageId msgId, Object... args) {
		this(new CipoErrorMessage(msgId, args));
	}	

	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param providerId The name of the message provider. 
	 * @param msgId The message identifier.
	 * @param args The list of variable arguments for formatting the error message.
	 */	 
	public CipoException(String providerId, MessageId msgId, Object... args) {
		this(new CipoErrorMessage(providerId, msgId, args));
	}	
	
	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param component The name of the component.
	 * @param returnCode The return code of the problem.
	 * @param reasonCode The reason code of the problem.
	 */
	public CipoException(String component, int returnCode, int reasonCode) {		
		this(new CipoErrorMessage(component, returnCode, reasonCode));
	}
	
	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param providerId The name of the message provider. 
	 * @param component The name of the component.
	 * @param returnCode The return code of the problem.
	 * @param reasonCode The reason code of the problem.
	 */
	public CipoException(String providerId, String component, int returnCode, int reasonCode) {
		this(new CipoErrorMessage(providerId, component, returnCode, reasonCode));
	}
	
	
	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param component The name of the component.
	 * @param returnCode The return code of the problem.
	 * @param reasonCode The reason code of the problem.
	 * @param args The list of variable arguments for formatting the error message.
	 */
	public CipoException(String component, int returnCode, int reasonCode, Object... args) {
		this(new CipoErrorMessage(component, returnCode, reasonCode, args));
	}
	
	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param providerId The name of the message provider. 
	 * @param component The name of the component.
	 * @param returnCode The return code of the problem.
	 * @param reasonCode The reason code of the problem.
	 * @param args The list of variable arguments for formatting the error message.
	 */
	public CipoException(String providerId, String component, int returnCode, int reasonCode, Object... args) {
		this(new CipoErrorMessage(providerId, component, returnCode, reasonCode, args));
	}
	
	
	/**
	 * Build a CIPO Exception containing the error message and the root cause 
	 * of the problem.
	 *  
	 * @param message A message explaining the error.
	 * @param cause Root cause of the problem.
	 */
	public CipoException(String message, Throwable cause) {
		this(message, cause, (CipoErrorMessage) null);
	}
	
	/**
	 * Build a CIPO Exception containing the root cause of the problem.
	 *  
	 * @param cause Root cause of the problem.
	 */
	// TODO: Do we have to build a default CIPOErrorMessage ?
	public CipoException(Throwable cause) {
		this(null, cause, (CipoErrorMessage) null);
	}

	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param message A message explaining the error.
	 * @param cause Root cause of the problem. 
	 * @param msgId The message identifier.
	 */
	public CipoException(String message, Throwable cause, MessageId msgId) {
		this(message, cause, new CipoErrorMessage(msgId));
	}
	
	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param message A message explaining the error.
	 * @param cause Root cause of the problem.
	 * @param providerId The name of the message provider. 
	 * @param msgId The message identifier.
	 */
	public CipoException(String message, Throwable cause, String providerId, MessageId msgId) {
		this(message, cause, new CipoErrorMessage(providerId, msgId));
	}
		
	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param message A message explaining the error.
	 * @param cause Root cause of the problem.
	 * @param msgId The message identifier.
	 * @param args The list of variable arguments for formatting the error message.
	 */
	public CipoException(String message, Throwable cause, MessageId msgId, Object... args) {
		this(message, cause, new CipoErrorMessage(msgId, args));
	}	
	
	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param message A message explaining the error.
	 * @param cause Root cause of the problem.
	 * @param providerId The name of the message provider.
	 * @param msgId The message identifier.
	 * @param args The list of variable arguments for formatting the error message.
	 */
	public CipoException(String message, Throwable cause, String providerId, MessageId msgId, Object... args) {
		this(message, cause, new CipoErrorMessage(providerId, msgId, args));
	}
	
	
	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param message A message explaining the error.
	 * @param cause Root cause of the problem.
	 * @param component The name of the component.
	 * @param returnCode The return code of the problem.
	 * @param reasonCode The reason code of the problem.
	 * @param args The list of variable arguments for formatting the error message.
	 */
	public CipoException(String message, Throwable cause, String component, int returnCode, int reasonCode, Object... args) {
		this(message, cause, new CipoErrorMessage(component, returnCode, reasonCode, args));
	}
	
	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param message A message explaining the error.
	 * @param cause Root cause of the problem.
	 * @param providerId The name of the message provider.
	 * @param component The name of the component.
	 * @param returnCode The return code of the problem.
	 * @param reasonCode The reason code of the problem.
	 * @param args The list of variable arguments for formatting the error message.
	 */
	public CipoException(String message, Throwable cause, String providerId, String component, int returnCode, int reasonCode, Object... args) {
		this(message, cause, new CipoErrorMessage(providerId, component, returnCode, reasonCode, args));
	}
	
	/**
	 * Build a CIPO Exception containing the error message of the problem that 
	 * could be localised.
	 *  
	 * @param message A message explaining the error.
	 * @param cause Root cause of the problem.
	 * @param errMessage A CIPO localised error message describing the problem.
	 */
	public CipoException(String message, Throwable cause, CipoErrorMessage errMessage) {		
		super(message, cause);
		this.errMessage = errMessage;		  
	}	

	
	/**
	 * Return a localised error message based on the given locale (FRENCH or ENGLISH) if
	 * a CipoErrorMessage exists, otherwise used the one of the super class.
	 * 
	 * @param locale The language locale (Locale.FRENCH or Locale.ENGLISH). 
	 * @return A formatted message based on the given locale. 
	 */
	public String getMessage(Locale locale) {
		if (errMessage != null) {
			return errMessage.getMessage(locale);
		}
		
		return super.getMessage();		
	}
	
	/**
	 * Return the localised error message in French.
	 *  
	 * @return A French formatted message. 
	 */
	public String getFrenchMessage() {
		return getMessage(Locale.FRENCH);
	}
	
	/**
	 * Return the localised error message in English.
	 *  
	 * @return An English formatted message. 
	 */
	public String getEnglishMessage() {
		return getMessage(Locale.ENGLISH);
	}
	
	@Override
	public String getMessage() {
		// If no message is provided, use the english service error
		// description as a default
		if (StringUtils.isBlank(super.getMessage())) {
			return getEnglishMessage();
		} 
		return super.getMessage();
	}		
}
