package jp.ddo.chiroru.sqllogger.util;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public final class DataSourceUtil {

    private final static ConnectionInfo info = ConnectionInfoProvider.provide(Environment.UnitTest);

    public static DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(info.getUrl());
        dataSource.setDriverClassName(info.getDriverClass());
        dataSource.setUsername(info.getUsername());
        dataSource.setPassword(info.getPassword());
        return dataSource;
    }
}
