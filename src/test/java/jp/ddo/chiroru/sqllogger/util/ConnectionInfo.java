package jp.ddo.chiroru.sqllogger.util;

import java.io.IOException;
import java.util.Properties;

public final class ConnectionInfo {

    private String username;
    private String password;
    private String url;
    private String schema;
    private String driverClass;
    private String baseDir;
    private String databaseName;

    public ConnectionInfo(String propertyPath) {
        try {
            ClassPathPropertyLoader loader = ClassPathPropertyLoader.getInstance();
            Properties props = loader.load(propertyPath);
            username = props.getProperty("jdbc.username");
            password = props.getProperty("jdbc.password");
            url = props.getProperty("jdbc.url");
            driverClass = props.getProperty("jdbc.driver");
            schema = props.getProperty("jdbc.schema");
            baseDir = props.getProperty("h2.baseDir");
            databaseName = props.getProperty("h2.databaseName");
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUrl() {
        return this.url;
    }

    public String getDriverClass() {
        return this.driverClass;
    }

    public String getSchema() {
        return this.schema;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
