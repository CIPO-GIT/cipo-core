package ca.gc.ic.cipo.core.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This is the base class for CIPO entities that requires persistence
 * with auditing record containing userId and time stamp information. 
 * These records are usually set at time of creation and/or update time. 
 * 
 * @author DenisJ1
 */
public abstract class PersistentAuditTrailEntity<TKey extends Serializable> 
	extends PersistentBaseEntity<TKey> {
    
	private static final long serialVersionUID = 1L;
    
	/** The create user ID. */
	protected String createUserId;
		
	/** The create time stamp. */
	protected Timestamp createTmstmp;
	
	/** The update user ID. */
	protected String updateUserId;
	
	/** The update time stamp. */
	protected Timestamp updateTmstmp;

	public PersistentAuditTrailEntity() {
		super();
	}
	
	public PersistentAuditTrailEntity(TKey id) {
		super(id);
	}
	
	/**
	 * Setting up an auditing to record the created timestmap 
	 * (using the current timestamp) and user identification.
	 * 
	 * @param user The user to audit.
	 */
	public void setCreateInfo(String user) {
		long now = System.currentTimeMillis();
		setCreateTmstmp(new Timestamp(now));
		setCreateUserId(user);
	}

	
	/**
	 * Update the audit information related to the update
	 * timestamp (using the current timestamp) and user identification.
	 * In case the auditing record for creation is not set, set it
	 * by default. 
	 * 
	 * @param user The user to audit.
	 */
	public void setUpdateInfo(String user) {
		long now = System.currentTimeMillis();
		if (null == getCreateUserId()) {
			setCreateTmstmp(new Timestamp(now));
			setCreateUserId(user);
		}
		setUpdateTmstmp(new Timestamp(now));
		setUpdateUserId(user);		
	}	
	
	
	/**
	 * Update the audit information based on the given audit trail
	 * entity.
	 * 
	 * @param entity Entity containing the auditing information.
	 */
	public void update(PersistentAuditTrailEntity<TKey> entity) {
		setCreateUserId(entity.getCreateUserId());
		setCreateTmstmp(entity.getCreateTmstmp());
		setUpdateUserId(entity.getUpdateUserId());
		setUpdateTmstmp(entity.getUpdateTmstmp());
	}
	
	/**
	 * Set the user id that have created the record.
	 * 
	 * @param createUserId The user id.
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * Return the user id that have create the record.
	 * 
	 * @return A String representing the user id.
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	
	/**
	 * Set the last user id that have update the record.
	 * 
	 * @param updateUserId The user id.
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * Return the last user id that have made an update.
	 * 
	 * @return A String representing the user id.
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}
	
	/**
	 * Return the last time (using a timestmap) the record have 
	 * been updated.
	 * 
	 * @return A reference on a timestamp.
	 */
	public Timestamp getUpdateTmstmp() {
		return updateTmstmp;
	}

	
	/**
	 * Set the last last time (using a timestmap) the record have 
	 * been updated.
	 * 
	 * @param updateTmstmp The update timestamp.
	 */
	public void setUpdateTmstmp(Timestamp updateTmstmp) {
		this.updateTmstmp = updateTmstmp;
	}

	/**
	 * Return the creation time (using a timestmap) of the record.
	 * 
	 * @return A reference on a timestmap.
	 */
	public Timestamp getCreateTmstmp() {
		return createTmstmp;
	}

	/**
	 * Set the creation time (using a timestmap) of the record.
	 * 
	 * @param createTmstmp The create timestmap.
	 */
	public void setCreateTmstmp(Timestamp createTmstmp) {
		this.createTmstmp = createTmstmp;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("createUserId", createUserId)
            .append("createTmstmp", createTmstmp)
            .append("updateUserId", updateUserId)
            .append("updateTmstmp", updateTmstmp)
            .toString();
    }            
}
