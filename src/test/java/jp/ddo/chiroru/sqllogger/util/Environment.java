package jp.ddo.chiroru.sqllogger.util;


public enum Environment {

    UnitTest("jdbc_ut.properties", true),
    IntegrationTest("jdbc_integration.properties", false);

    private String propertyPath;

    private boolean doMigration;

    private Environment(String propertyPath,
            boolean doMigration) {
        this.propertyPath = propertyPath;
        this.doMigration = doMigration;
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    public boolean doMigration() {
        return doMigration;
    }
}
