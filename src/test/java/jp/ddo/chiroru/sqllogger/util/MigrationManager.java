package jp.ddo.chiroru.sqllogger.util;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.MigrationInfo;

public class MigrationManager {

    private static Map<Environment, MigrationExecuter> repo;

    static {
        repo = new HashMap<>();
        repo.put(Environment.UnitTest, new MigrationExecuter(Environment.UnitTest));
        // repo.put(Environment.IntegrationTest, new MigrationExecuter(Environment.IntegrationTest));
    }

    private MigrationManager() {}

    public static void maigration(Environment environment) {
        synchronized (repo) {
            MigrationExecuter executer = repo.get(environment);
            executer.maigration();
        }
    }

    private static class MigrationExecuter {

        private Flyway flyway;

        private MigrationInfo info;

        public MigrationExecuter(Environment environment) {
            ConnectionInfo info = ConnectionInfoProvider.provide(environment);
            flyway = new Flyway();
            flyway.setSchemas(info.getSchema());
            flyway.setDataSource(setupDateSource(info));
        }

        public void maigration() {
            info = flyway.info().current();
            if (info == null || !info.getState().isApplied()) {
                flyway.migrate();
            }
        }

        private DataSource setupDateSource(ConnectionInfo info) {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl(info.getUrl());
            dataSource.setDriverClassName(info.getDriverClass());
            dataSource.setUsername(info.getUsername());
            dataSource.setPassword(info.getPassword());
            return dataSource;
        }
    }
}
