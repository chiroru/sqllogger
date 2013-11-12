package jp.ddo.chiroru.sqllogger.wrapper;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <tt>java.sql.Connection</tt> wrapper
 * 
 * @author smts1008@outlook.com
 * @version 1.0.0
 */
public class LoggingConnection
        implements Connection {

    Log log = LogFactory.getLog(LoggingConnection.class);

    /** Connection Instance */
    private Connection realConnection = null;
    /** Connection ID */
    private static int connectionId = 0;

    /**
     * Constructor
     */
    public LoggingConnection(Connection realConnection) {
        this.realConnection = realConnection;
        ++connectionId;
    }

    public int getConnectionId() {
        return connectionId;
    }

	public void clearWarnings()
	        throws SQLException {
		this.realConnection.clearWarnings();
	}

	public void close() throws SQLException {
		this.realConnection.close();
	}

	public void commit() throws SQLException {
	    long startTime = System.currentTimeMillis();;
	    long endTime;
	    
	    try {
	        this.realConnection.commit();
	    } finally {
	        endTime = System.currentTimeMillis();
	        
	        log.info(String.valueOf(getConnectionId()) + "," + "commit" + "," + String.valueOf(endTime-startTime));
	    }
	}

    public void rollback() throws SQLException {
        long startTime = System.currentTimeMillis();
        
        try {
            this.realConnection.rollback();
        } finally {
            long endTime = System.currentTimeMillis();
            log.info(String.valueOf(getConnectionId()) + "," + "rollback" + "," + String.valueOf(endTime - startTime));
        }
    }

    public void rollback(Savepoint savepoint)
            throws SQLException {
        long startTime = System.currentTimeMillis();
        try {
            this.realConnection.rollback(savepoint);
        } finally {
            long endTime = System.currentTimeMillis();
            log.info(String.valueOf(getConnectionId()) + "," + "rollback" + "," + String.valueOf(endTime - startTime));
        }
    }

	public Statement createStatement() throws SQLException {
		
	    return this.realConnection.createStatement();
	}

	public Statement createStatement(int resultSetType,
	                                 int resultSetConcurrency)
			throws SQLException {
	    
		return this.realConnection.createStatement(resultSetType, resultSetConcurrency);
	}

	public Statement createStatement(int resultSetType,
			                         int resultSetConcurrency,
			                         int resultSetHoldability)
			throws SQLException {
		
		return this.realConnection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	public boolean getAutoCommit()
	        throws SQLException {
		
		return this.realConnection.getAutoCommit();
	}

	public String getCatalog() throws SQLException {
		
		return this.realConnection.getCatalog();
	}

	public int getHoldability() throws SQLException {
		
		return this.realConnection.getHoldability();
	}

	public DatabaseMetaData getMetaData()
	        throws SQLException {
		
		return this.realConnection.getMetaData();
	}

	public int getTransactionIsolation()
	        throws SQLException {
		
		return this.realConnection.getTransactionIsolation();
	}

	public Map<String, Class<?>> getTypeMap()
	        throws SQLException {
		
		return this.realConnection.getTypeMap();
	}

	public SQLWarning getWarnings() throws SQLException {
		
		return this.realConnection.getWarnings();
	}

	public boolean isClosed() throws SQLException {
		
		return this.realConnection.isClosed();
	}

	public boolean isReadOnly() throws SQLException {
		
		return this.isReadOnly();
	}

	public String nativeSQL(String sql) throws SQLException {
		
		return this.realConnection.nativeSQL(sql);
	}

	public CallableStatement prepareCall(String sql)
	        throws SQLException {
		
		return this.realConnection.prepareCall(sql);
	}

	public CallableStatement prepareCall(String sql,
	                                     int resultSetType,
			                             int resultSetConcurrency)
	        throws SQLException {
		
		return this.realConnection.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	public CallableStatement prepareCall(String sql,
	                                     int resultSetType,
			                             int resultSetConcurrency,
			                             int resultSetHoldability)
            throws SQLException {
		
		return this.realConnection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	public PreparedStatement prepareStatement(String sql)
	        throws SQLException {
		
		return this.realConnection.prepareStatement(sql);
	}

	public PreparedStatement prepareStatement(String sql,
	                                          int autoGeneratedKeys)
			throws SQLException {
		
		return this.realConnection.prepareStatement(sql, autoGeneratedKeys);
	}

	public PreparedStatement prepareStatement(String sql,
	                                          int[] columnIndexes)
			throws SQLException {
		
		return this.realConnection.prepareStatement(sql, columnIndexes);
	}

	public PreparedStatement prepareStatement(String sql,
	                                          String[] columnNames)
			throws SQLException {
        
		return this.realConnection.prepareStatement(sql, columnNames);
	}

	public PreparedStatement prepareStatement(String sql,
	                                          int resultSetType,
			                                  int resultSetConcurrency)
	        throws SQLException {
		
		return this.realConnection.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	public PreparedStatement prepareStatement(String sql,
	                                          int resultSetType,
	                                          int resultSetConcurrency,
	                                          int resultSetHoldability)
	        throws SQLException {
	    
	    return this.realConnection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	public void releaseSavepoint(Savepoint savepoint)
	        throws SQLException {
		
	    this.realConnection.releaseSavepoint(savepoint);
	}

	public void setAutoCommit(boolean autoCommit)
	        throws SQLException {
		
        this.realConnection.setAutoCommit(autoCommit);
	}

	public void setCatalog(String catalog)
	        throws SQLException {
		
        this.realConnection.setCatalog(catalog);
	}

	public void setHoldability(int holdability)
	        throws SQLException {
		
        this.realConnection.setHoldability(holdability);
	}

	public void setReadOnly(boolean readOnly)
	        throws SQLException {
		
        this.realConnection.setReadOnly(readOnly);
	}

	public Savepoint setSavepoint()
	        throws SQLException {
		
		return this.realConnection.setSavepoint();
	}

	public Savepoint setSavepoint(String name)
	        throws SQLException {
		
		return this.realConnection.setSavepoint(name);
	}

	public void setTransactionIsolation(int level)
	        throws SQLException {
		
        this.realConnection.setTransactionIsolation(level);
	}

	public void setTypeMap(Map<String, Class<?>> map)
	    throws SQLException {
		
        this.realConnection.setTypeMap(map);
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Clob createClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blob createBlob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NClob createNClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSchema(String schema) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSchema() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void abort(Executor executor) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
