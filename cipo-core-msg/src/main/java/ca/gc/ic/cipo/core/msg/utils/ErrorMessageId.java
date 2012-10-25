package ca.gc.ic.cipo.core.msg.utils;

/**
 * ErrorMessageId is a generic implementation of an error message 
 * identifier.  The error message is defined using the component or
 * service name, the return code and reason code.  
 * 
 * @see MessageId
 * 
 * @author J.Denis
 *
 */
public class ErrorMessageId implements MessageId {

	/** The component or service name related to the error message. */
	private String component;
	
	/** The return code of the error. */
	private String returnCode;
	
	/** The reason code of the error. */
	private String reasonCode;

	/**
	 * Build an ErrorMessageId based on the given component, return code and 
	 * reason code.
	 * 
	 * @param component The component or service name related to the error.
	 * @param returnCode The return code of the error.
	 * @param reasonCode The reason code of the error.
	 */
	public ErrorMessageId(String component, String returnCode,
			String reasonCode) {
		super();
		this.component = component;
		this.returnCode = returnCode;
		this.reasonCode = reasonCode;
	}

	/**
	 * Build an ErrorMessageId based on the given component, return code and 
	 * reason code.
	 * 
	 * @param component The component or service name related to the error.
	 * @param returnCode The return code of the error.
	 * @param reasonCode The reason code of the error.
	 */
	public ErrorMessageId(String component, int returnCode, int reasonCode) {
		this(component, String.valueOf(returnCode), String.valueOf(reasonCode));
	}

	public String getId() {
		return component + "." + returnCode + "." + reasonCode;
	}

	/**
	 * Return the component or service name that constitute the error message id.
	 * 
	 * @return A String representation of the component or service name.
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * Set the component or service name that constitute the error message id.
	 * 
	 * @param component The component or service name that constitute the 
	 *                  error message id.
	 */
	public void setComponent(String component) {
		this.component = component;
	}

	/**
	 * Return the return code that constitute the error message id.
	 * 
	 * @return A String representation of the return code.
	 */
	public String getReturnCode() {
		return returnCode;
	}

	/**
	 * Set the return code that constitute the error message id.
	 * 
	 * @param component The return code that constitute the
	 *                  error message id.
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	/**
	 * Return the reason code that constitute the error message id.
	 * 
	 * @return A String representation of the reason code.
	 */
	public String getReasonCode() {
		return reasonCode;
	}

	/**
	 * Set the reason code that constitute the error message id.
	 * 
	 * @param component The reason code that constitute the
	 *                  error message id.
	 */
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

}
