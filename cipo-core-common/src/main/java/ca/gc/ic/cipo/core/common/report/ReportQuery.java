package ca.gc.ic.cipo.core.common.report;

import java.io.Serializable;

//TODO - Add documentation.
public interface ReportQuery extends Serializable {
    
	Object getObject(int index) throws Exception;
	
}

