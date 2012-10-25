package ca.gc.ic.cipo.core.msg.utils;

import java.io.Serializable;
import java.util.Locale;

/**
 * LocalizedMessage is an abstract class that represents a message that can be
 * localised.  The translation could come from different LocalizedMessageRepository
 * implementation.  
 * 
 * @author J.Denis
 */
public class LocalizedMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** The provider id describing to which LocalizedMessageProvider 
	    the definition of the message belongs too. */
	private String providerId;
	
	/** The message Id. */
	private MessageId msgId;

	/** The list of variable arguments required to format the message if required. 
	 *  May be null. */
	private Object[] listArgs;

	 /**
 	   * Build a LocalizedMessage using the given message Id and provider Id.
 	   * 
	   * @param providerId The unique identifier of the message provider.
	   * @param messageId Message identifier.
	   */
	  public LocalizedMessage(String providerId, MessageId messageId) {
	      this(providerId, messageId, (Object) null);
	  }
	
	/** 
	 * Build a LocalizedMessage using the provider Id, the message Id and 
	 * the list of arguments.
	 *  
     * @param providerId The unique identifier of the message provider.
	 * @param msgId Message identifier.
	 * @param args A list of optional arguments for message formatting.
	 */
	public LocalizedMessage(String providerId, MessageId msgId, Object... args) {
		super();
		this.providerId = providerId;
		this.msgId = msgId;
		this.listArgs = args;		
	}	

	/**
	 * Return the provider id for this message.
	 * 
	 * @return The provider id.
	 */
	public String getProviderId() {
		return providerId;
	}

	/**
	 * Set the provider id for this message.
	 * 
	 * @param msgId The provider Id.
	 */
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
	/**
	 * Return the message id of the message.
	 * 
	 * @return The message id.
	 */
	public MessageId getMsgId() {
		return msgId;
	}

	/**
	 * Set the message id of the message.
	 * 
	 * @param msgId The message Id.
	 */
	public void setMsgId(MessageId msgId) {
		this.msgId = msgId;
	}

	/**
	 * Return the list of optional arguments required for message formatting.
	 * 
	 * @return The list of message arguments for formatting purpose.
	 */
	public Object[] getListArgs() {
		return listArgs;
	}

	/**
	 * Set the list of optional arguments required for message formatting.
	 * 
	 * @param listArgs The list of message arguments for formatting purpose.
	 */
	public void setListArgs(Object[] listArgs) {
		this.listArgs = listArgs;
	}

	/**
	 * Return the message format of the message based on the given locale.
	 *  
	 * @param locale The locale to used.
	 * 
	 * @return A String format representing the message. 
	 */
	public String getMessage(Locale locale) {	
		return LocalizedMessageManager.getInstance().getMessage(providerId, msgId, locale, listArgs);				
	}
}
