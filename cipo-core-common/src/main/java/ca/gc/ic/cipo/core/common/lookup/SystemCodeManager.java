package ca.gc.ic.cipo.core.common.lookup;

import java.util.List;

import ca.gc.ic.cipo.core.common.model.RacfUser;


/**
 * SystemCodeManager is an interface class responsible of retrieving system
 * code information base on a given system code type or system code.  System
 * code usually contains basic an English/French Description. 
 * 
 * @author DenisJ1
 *
 */
public interface SystemCodeManager {

	SystemCode getSystemCode(RacfUser user, String sysCodeType, String sysCode);	
	List<SystemCode> getSystemCodes(RacfUser user, String sysCodeType);	
}
