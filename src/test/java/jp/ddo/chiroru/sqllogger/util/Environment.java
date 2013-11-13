package jp.ddo.chiroru.sqllogger.util;


public enum Environment {

    UnitTest("jdbc_ut.properties", "testing", true),
    IntegrationTest("jdbc_integration.properties", "testing", false);

    private String propertyPath;

    private String mybatisEnvironment;

    private boolean doMigration;

    private Environment(String propertyPath,
            String mybatisEnvironment,
            boolean doMigration) {
        this.propertyPath = propertyPath;
        this.mybatisEnvironment = mybatisEnvironment;
        this.doMigration = doMigration;
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    public String getMybatisEnvironment() {
        return mybatisEnvironment;
    }

    public boolean doMigration() {
        return doMigration;
    }
}
