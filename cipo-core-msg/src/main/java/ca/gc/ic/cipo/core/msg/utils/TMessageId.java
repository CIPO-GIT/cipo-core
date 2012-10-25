package ca.gc.ic.cipo.core.msg.utils;

/**
 * TMessageId is a generic implementation of a message identifier.  The
 * identifier support any types.
 * 
 * @see MessageId
 * @author J.Denis
 *
 */
public class TMessageId<T> implements MessageId {

    /**
     * The message identifier value.
     */
    public T 	msgId;
	
	/**
	 * Build an instance of a TMessageId based on the given identifier type.
	 * @param msgId The message identifier.
	 */
	public TMessageId(T msgId) {
		this.msgId = msgId;
	}

	public String getId() {
		
		String id = "";
		if (msgId != null) {
			id += msgId;
		}
		
		return id;
	}

}
