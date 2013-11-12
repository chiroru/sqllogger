package jp.ddo.chiroru.sqllogger.wrapper;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * javax.sql.DataSource Wrapper<br>
 * 
 * @author smts1008@outlook.com
 * @version 1.0.0
 */
public class LoggingDataSource
		implements DataSource {

	//------------------------------------------------------------ properties

	protected DataSource realDataSource = null;

	/** Connection Instance counter */
	private static int counter = 0;

	/** connection id */
	private int connectionId = counter++;

	//------------------------------------------------------------ Constructors
	/**
	 * 
	 * @param realDataSource
	 */
	public LoggingDataSource(DataSource realDataSource) {
		this.realDataSource = realDataSource;
	}

	//------------------------------------------------------------ Methods
	/**
	 * @return connection id
	 */
	public int getConnectionId() {
		return this.connectionId;
	}

	/**
	 * 
	 * @return <tt>java.sql.Connection</tt>�̃ wrapper
	 */
	public Connection getConnection()
			throws SQLException {

		return realDataSource.getConnection();
	}

	public Connection getConnection(String username, String password)
			throws SQLException {

		return realDataSource.getConnection(username, password);
	}

	public PrintWriter getLogWriter() throws SQLException {
		return this.realDataSource.getLogWriter();
	}

	public void setLogWriter(PrintWriter writer) throws SQLException {
		this.realDataSource.setLogWriter(writer);
	}

	/**
	 * 
	 */
	public void setLoginTimeout(int loginTimeout) throws SQLException {
		this.realDataSource.setLoginTimeout(loginTimeout);
	}

	/**
	 * �
	 */
	public int getLoginTimeout() throws SQLException {
		return this.realDataSource.getLoginTimeout();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return realDataSource.getParentLogger();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return realDataSource.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return isWrapperFor(iface);
	}

}
