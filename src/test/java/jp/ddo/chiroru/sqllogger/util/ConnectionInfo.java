package jp.ddo.chiroru.sqllogger.util;

import java.io.IOException;
import java.util.Properties;

public class ConnectionInfo {

    private final static String TARGET_PROPERTY_FILE = "/jdbc_ut.properties";
    private final Properties props;

    public ConnectionInfo() {
        props = new Properties();
        try {
            props.load(this.getClass().getResourceAsStream(TARGET_PROPERTY_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        return props.getProperty("jdbc.url");
    }

    public String getUserName() {
        return props.getProperty("jdbc.username");
    }

    public String getPassword() {
        return props.getProperty("jdbc.password");
    }

    public String getDriver() {
        return props.getProperty("jdbc.driver");
    }

    public String getSchema() {
        return props.getProperty("jdbc.schema");
    }

    protected String generateUrl() {
        return "jdbc:h2:tcp://127.0.0.1/" + getBaseDir() + "/" + getDbName();
    }

    public String getBaseDir() {
        return props.getProperty("base.dir");
    }

    public String getDbName() {
        return props.getProperty("db.name");
    }
}
