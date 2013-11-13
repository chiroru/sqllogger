package jp.ddo.chiroru.sqllogger.util;

import java.util.HashMap;
import java.util.Map;

public class ConnectionInfoProvider {

    private static final ConnectionInfoProvider INSTANCE = new ConnectionInfoProvider();

    private Map<Environment, ConnectionInfo> repo;

    private ConnectionInfoProvider() {
        repo = new HashMap<>();
        repo.put(Environment.UnitTest, new ConnectionInfo(Environment.UnitTest.getPropertyPath()));
        //repo.put(Environment.IntegrationTest, new ConnectionInfo(Environment.IntegrationTest.getPropertyPath()));
    }

    public static ConnectionInfo provide(Environment environment) {
        return INSTANCE.repo.get(environment);
    }
}
