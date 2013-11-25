package jp.ddo.chiroru.sqllogger.util;

public class TestEnvironmentConfig {

    private TestPhase testPhase;

    private String propertyFilePath;

    public TestPhase getTestPhase() {
        return testPhase;
    }

    public void setTestPhase(TestPhase testPhase) {
        this.testPhase = testPhase;
    }

    public String getPropertyFilePath() {
        return propertyFilePath;
    }

    public void setPropertyFilePath(String propertyFilePath) {
        this.propertyFilePath = propertyFilePath;
    }
}
