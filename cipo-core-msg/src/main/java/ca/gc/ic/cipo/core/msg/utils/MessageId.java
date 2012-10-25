package ca.gc.ic.cipo.core.msg.utils;

/**
 * MessageId is an interface class that defined a message identifier.  The
 * identifier can be of any type but required to be returned as a 
 * a string.
 * 
 * @author J.Denis
 *
 */
public interface MessageId {	
	
	/**
	 * Return the identifier of the message as a string.
	 * 
	 * @return A string representation of the message id.
	 */
	public String getId();
	
}
