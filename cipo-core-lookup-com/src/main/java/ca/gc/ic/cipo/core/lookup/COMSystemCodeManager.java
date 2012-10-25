package ca.gc.ic.cipo.core.lookup;

import java.util.List;

import ca.gc.ic.cipo.core.common.lookup.SystemCode;
import ca.gc.ic.cipo.core.common.lookup.SystemCodeElement;
import ca.gc.ic.cipo.core.common.lookup.SystemCodeManager;
import ca.gc.ic.cipo.core.common.model.RacfUser;

// TODO - Add documentation.
public class COMSystemCodeManager implements SystemCodeManager {
	
	@Override
	public SystemCode getSystemCode(RacfUser user, String sysCodeType, String sysCode) {

		// TODO - Implement the client that invokes the Web Service.
		SystemCodeElement sysCodeElement = null;
	/*
		String servicesHostName = ServiceUtils.getServiceHost();
		
		SystemCodeElementServicePortType codeClient = 
			CodeElementServiceFactory.createClient(servicesHostName);

		SystemCodeElementServiceResponse codeResponse = codeClient.get(user, sysCodeType, sysCode);
		
		if (codeResponse.getReturnCode() < 1) {
			throw new CipoException(codeResponse);
		}
		sysCodeElement = codeResponse.getSystemCodeValue();
*/
		return (SystemCode) sysCodeElement;
	}

	@Override
	public List<SystemCode> getSystemCodes(RacfUser user, String sysCodeType) {
		// TODO - Implement the client that invokes the Web Service.

		List<SystemCode> listSysCodes = null;
	/*
		String servicesHostName = ServiceUtils.getServiceHost();
		
		SystemCodeElementServicePortType codeClient = 
			CodeElementServiceFactory.createClient(servicesHostName);

		SystemCodeElementServiceResponse codeResponse = codeClient.get(user, sysCodeType);
		
		if (codeResponse.getReturnCode() < 1) {
			throw new CipoException(codeResponse);
		}
		listSysCodes = codeResponse.getSystemCodes();
*/
		return listSysCodes;
	}
}
