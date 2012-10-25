package ca.gc.ic.cipo.core.msg.utils;

/**
 * CipoErrorMessage is a class describing a localised error message in both 
 * official language supported by CIPO office (English and/or French).
 * 
 * If no Localized message provider is defined, use the default one named
 * ComponentErrors.
 * 
 * @see CipoMessage
 * @see LocalizedMessage
 * 
 * @author J.Denis
 */
public class CipoErrorMessage extends CipoMessage {
	
	private static final long serialVersionUID = 1L;
	
	/** Default localised error message repository. */
	public static final String DEFAULT_ERR_MESSAGE_PROVIDER = "ComponentErrors";
		
   /** 
	 * Build a CIPO localised error message based on message ID.
	 * 
	 * @param id A String representing the error message ID.
	 */
	public CipoErrorMessage(String id) {
		this(DEFAULT_ERR_MESSAGE_PROVIDER, id, (Object) null);
	}
	
	/** 
	 * Build a CIPO localised error message based on the given message ID.
	 *  
	 * @param msgId The error message ID.
	 */
	public CipoErrorMessage(MessageId msgId) {
		this(DEFAULT_ERR_MESSAGE_PROVIDER, msgId, (Object) null);
	}
	
	/** 
	 * Build a CIPO localised error message based on the given message ID 
	 * and variable list of arguments used for message formatting.
	 *  
	 * @param id A String representing the error message ID.
	 * @param args The list of variable arguments for formatting the error message.
	 */
	public CipoErrorMessage(String id, Object... args) {
		super(DEFAULT_ERR_MESSAGE_PROVIDER, new TMessageId<String>(id), args);
	}	
	
	/** 
	 * Build a CIPO localised error message based on the given 
	 * message ID and variable list of arguments used for error message formatting.
	 *  
	 * @param msgId The error message ID.
	 * @param args The list of variable arguments for formatting the message.
	 */
	public CipoErrorMessage(MessageId msgId, Object... args) {
		super(DEFAULT_ERR_MESSAGE_PROVIDER, msgId, args);
	}	
	
	/** 
	 * Build a CIPO localised error message based on the given
	 * component, return code, reason code and a variable list of 
	 * arguments used for error message formatting.
	 * 
	 * @param component The name of the component.
	 * @param returnCode The return code of the problem.
	 * @param reasonCode The reason code of the problem.
	 * @param args The list of variable arguments for formatting the error message.
	 */
	public CipoErrorMessage(String component, int returnCode, int reasonCode, Object... args) {
		this(DEFAULT_ERR_MESSAGE_PROVIDER, new ErrorMessageId(component, returnCode, reasonCode), args);
	}
	
	
	/** 
	 * Build a CIPO localised error message based on message ID.
	 * 
	 * @param providerId The provider ID to which the message belongs too.
	 * @param id A String representing the error message ID.
	 */
	public CipoErrorMessage(String providerId, String id) {
		super(providerId, id);
	}
	
	/** 
	 * Build a CIPO localised error message based on the given message ID.
	 * 
	 * @param providerId The provider ID to which the message belongs too.
	 * @param msgId The error message ID.
	 */
	public CipoErrorMessage(String providerId, MessageId msgId) {
		super(providerId, msgId);
	}
	
	/** 
	 * Build a CIPO localised error message based on the given message ID 
	 * and variable list of arguments used for message formatting.
	 *  
	 * @param providerId The provider ID to which the message belongs too.
	 * @param id A String representing the error message ID.
	 * @param args The list of variable arguments for formatting the error message.
	 */
	public CipoErrorMessage(String providerId, String id, Object... args) {
		super(providerId, id, args);
	}	
	
	/** 
	 * Build a CIPO localised error message based on the given 
	 * message ID and variable list of arguments used for error message formatting.
	 *  
	 * @param providerId The provider ID to which the message belongs too.
	 * @param msgId The error message ID.
	 * @param args The list of variable arguments for formatting the message.
	 */
	public CipoErrorMessage(String providerId, MessageId msgId, Object... args) {
		super(providerId, msgId, args);
	}	
	
	/** 
	 * Build a CIPO localised error message based on the given
	 * component, return code, reason code and a variable list of 
	 * arguments used for error message formatting.
	 * 
	 * @param providerId The provider ID to which the message belongs too.
	 * @param component The name of the component.
	 * @param returnCode The return code of the problem.
	 * @param reasonCode The reason code of the problem.
	 * @param args The list of variable arguments for formatting the error message.
	 */
	public CipoErrorMessage(String providerId, String component, int returnCode, int reasonCode, Object... args) {
		super(providerId, new ErrorMessageId(component, returnCode, reasonCode), args);
	}	

}
