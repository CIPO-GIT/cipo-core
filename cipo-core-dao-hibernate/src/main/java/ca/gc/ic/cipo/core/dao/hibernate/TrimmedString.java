package ca.gc.ic.cipo.core.dao.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

/**
 * The TrimmedString is a custom utility class for trimming strings 
 * when storing or retrieving values of a CHAR SQL type using JDBC.   
 * 
 * @author DenisJ1
 *
 */
public class TrimmedString implements UserType {

	public TrimmedString() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#sqlTypes()
	 */
	public int[] sqlTypes() {
		return new int[] { Types.CHAR };
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#returnedClass()
	 */
	public Class<String> returnedClass() {
		return String.class;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#equals(java.lang.Object, java.lang.Object)
	 */
	public boolean equals(Object x, Object y) throws HibernateException {
		return (x == y) || (x != null && y != null && (x.equals(y)));
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#deepCopy(java.lang.Object)
	 */
	public Object deepCopy(Object value) throws HibernateException {
		if (value == null) 
			return null;
		
		return new String((String)value);
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#isMutable()
	 */
	public boolean isMutable() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#assemble(java.io.Serializable, java.lang.Object)
	 */
	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return (String) deepCopy(cached);		
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#disassemble(java.lang.Object)
	 */
	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (String) deepCopy(value);
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#hashCode(java.lang.Object)
	 */
	@Override
	public int hashCode(Object obj) throws HibernateException {		
		return obj.hashCode();
	}

	/* (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[], org.hibernate.engine.spi.SessionImplementor, java.lang.Object)
	 */
	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object ownew) throws HibernateException, SQLException {
		
		String val = rs.getString(names[0]);
		if (null == val) {
			return null;
		}
		
		return val.trim();		
	}
	
	/**
	 * Write an instance of the mapped class to a prepared statement. 
	 * A multi-column type should be written to parameters starting from index. 
	 * 
	 * @param st A JDBC prepared statement
	 * @param value The object to write
	 * @param index The statement parameter index 
	 * @throws HibernateException
	 * @throws SQLException 
	 * @throws SQLException
	 */
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		st.setString(index, (String)value);
	}


	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws HibernateException, SQLException {
		st.setString(index, (String)value);
	}

	@Override
	public Object replace(Object original, Object target, Object owner)	throws HibernateException {
		return deepCopy(original);
	}
}



