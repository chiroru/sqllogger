package jp.ddo.chiroru.sqllogger;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * <p>
 * ロギング機構を組み込むためのエントリーポイントを提供する{@link DataSource}のラッパーです.
 * </p>
 * @author smts1008@outlook.com
 */
public class LoggingDataSource
        implements DataSource {

    private DataSource wrappedDataSource;

    public LoggingDataSource(DataSource wrappedDataSource) {
        this.wrappedDataSource = wrappedDataSource;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return wrappedDataSource.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        wrappedDataSource.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        wrappedDataSource.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return wrappedDataSource.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return wrappedDataSource.getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return wrappedDataSource.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return wrappedDataSource.isWrapperFor(iface);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return wrappedDataSource.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password)
            throws SQLException {
        return wrappedDataSource.getConnection(username, password);
    }

}
