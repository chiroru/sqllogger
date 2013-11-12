package jp.ddo.chiroru.sqllogger;

import jp.ddo.chiroru.sqllogger.util.DataSourceUtil;
import jp.ddo.chiroru.sqllogger.util.H2DatabaseTCPServerManager;

import org.junit.ClassRule;
import org.junit.Test;


public class LoggingDataSourceTest {

    @ClassRule
    public static H2DatabaseTCPServerManager serverManager = new H2DatabaseTCPServerManager();

    @Test
    public void DataSourceのラッパーを生成できる()
            throws Exception {
        LoggingDataSource dataSource = new LoggingDataSource(DataSourceUtil.getDataSource());
        dataSource.getConnection();
    }
}
