package ca.gc.ic.cipo.core.msg.utils;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * CipoMessage is a class describing a localised message in both 
 * official language supported by CIPO office (English and/or French).
 * 
 * If no Localized message provider is defined, use the default one named
 * ComponentErrors.
 * 
 * @see LocalizedMessage
 * @author J.Denis
 */
public class CipoMessage extends LocalizedMessage {
	
	private static final long serialVersionUID = 1L;
	
	/** Default localised message provider. */
	public static final String DEFAULT_MESSAGE_PROVIDER = "ComponentMessages";	
	
	/** 
	 * Build a CIPO localised message based on the given message ID.
	 * 
	 * @param msgId The message ID.
	 */
	public CipoMessage(MessageId msgId) {
		this(DEFAULT_MESSAGE_PROVIDER, msgId, (Object) null);
	}

	/** 
	 * Build a CIPO localised message based on the given message ID.
	 * 
	 * @param id A String representing the message ID.
	 */
	public CipoMessage(String id) {
		this(DEFAULT_MESSAGE_PROVIDER, id, (Object) null);
	}
			
	/** 
	 * Build a CIPO localised message based on the given message ID and 
	 * a variable list of arguments used for message formatting.
	 *  
	 * @param id A String representing the message ID.
	 * @param args The list of variable arguments for formatting the message.
	 */
	public CipoMessage(String id, Object... args) {
		super(DEFAULT_MESSAGE_PROVIDER, new TMessageId<String>(id), args);
	}	
	
	/** 
	 * Build a CIPO localised message based on the given 
	 * provider ID, message ID and a variable list of arguments used
	 * for message formatting.
	 *  
	 * @param providerId The provider ID to which the message belongs too.
	 * @param msgId The message ID.
	 * @param args The list of variable arguments for formatting the message.
	 */
	public CipoMessage(MessageId msgId, Object... args) {
		this(DEFAULT_MESSAGE_PROVIDER, msgId, args);
	}	

	 /**
	   * Build a CipoMessage using the given message Id and provider Id.
	   * 
	   * @param providerId The unique identifier of the message provider.
	   * @param messageId Message identifier.
	   */
	  public CipoMessage(String providerId, String messageId) {
	      this(providerId, messageId, (Object) null);
	  }
	
	/** 
	 * Build a CIPO localised message based on the given 
	 * provider ID, message ID and a variable list of arguments used
	 * for message formatting.
	 *  
	 * @param providerId The provider ID to which the message belongs too.
	 * @param id A String representing the message ID.
	 * @param args The list of variable arguments for formatting the message.
	 */
	public CipoMessage(String providerId, String id, Object... args) {
		super(providerId, new TMessageId<String>(id), args);
	}	
	
	/** 
	 * Build a CIPO localised message based on the given 
	 * provider ID, message ID and a variable list of arguments used
	 * for message formatting.
	 *  
	 * @param providerId The provider ID to which the message belongs too.
	 * @param msgId The message ID.
	 * @param args The list of variable arguments for formatting the message.
	 */
	public CipoMessage(String providerId, MessageId msgId, Object... args) {
		super(providerId, msgId, args);
	}	
	
	/**
	 * Return the message in English.
	 *  
	 * @return An English formatted message. 
	 */
	public String getEnglishMessage() {
		return super.getMessage(Locale.ENGLISH);
	}
	
	/**
	 * Return the message in French.
	 *  
	 * @return A French formatted message. 
	 */
	public String getFrenchMessage() {
		return super.getMessage(Locale.FRENCH);
	}
	
	/**
	 * Return the message in a bilingual form based on the 
	 * given format (English first, French second) and the given providerId. 
	 *  
	 * @param providerId The provider id where the message is defined. 
	 * @return A bilingual formatted message. 
	 */
	public String getBilingualMessage(String format) {		
		return getBilingualMessage(format, true);		
	}

	/**
	 * Return the message in a bilingual form based on the 
	 * given format and the priority of the language.  
	 *  
	 * @return A bilingual formatted message. 
	 */
	public String getBilingualMessage(String format, boolean englishFirst) {
		String msgEnglish = getEnglishMessage();
		String msgFrench = getFrenchMessage();
		return englishFirst ? MessageFormat.format(format, msgEnglish, msgFrench) : 
			MessageFormat.format(format, msgFrench, msgEnglish);
	}
}
