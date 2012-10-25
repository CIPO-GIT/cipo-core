package ca.gc.ic.cipo.core.common.report;

import org.apache.log4j.Logger;

//TODO - Add documentation.
public abstract class ReportAbstractDataSource implements ReportDataSource {

	protected int index = 0;
	protected Object bean;
	protected ReportQuery dao;

	/** Logger. */
	protected final Logger logger = Logger.getLogger(getClass());

	public ReportAbstractDataSource(ReportQuery dao) {
		super();
		
		this.index = 0;
		this.bean = null;
		this.dao = dao;		
	}

	public ReportQuery getDao() {
		return dao;
	}

	public void setDao(ReportQuery dao) {
		this.dao = dao;
	}

	private Object getValue(int curIndex) {		
		try {
			bean = dao.getObject(curIndex);
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO - Add a new type of exception;
		}
		return null;
	}

	@Override
	public Object firstValue() {
		index = 0;
		return getValue(index);
	}
	
	@Override
	public Object nextValue() {
		return getValue(index++);
	}
	
	@Override
	public Object currentValue() {
		return getValue(index);
	}
}
