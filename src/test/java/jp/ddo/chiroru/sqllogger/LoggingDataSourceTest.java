package jp.ddo.chiroru.sqllogger;

import java.sql.Connection;
import java.sql.Statement;

import jp.ddo.chiroru.sqllogger.util.DataSourceUtil;
import jp.ddo.chiroru.sqllogger.util.Environment;
import jp.ddo.chiroru.sqllogger.util.H2DatabaseTCPServerManager;
import jp.ddo.chiroru.sqllogger.util.MigrationManager;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * <p>
 * {@link LogginDataSource}のユニットテストクラスです.
 * </p>
 * @author smts1008@outlook.com
 * @since 1.0.0
 */
public class LoggingDataSourceTest {

    @ClassRule
    public static H2DatabaseTCPServerManager serverManager = new H2DatabaseTCPServerManager();

    @BeforeClass
    public static void setUp() {
        MigrationManager.maigration(Environment.UnitTest);
    }
    
    @Test
    public void DataSourceのラッパーを生成できる()
            throws Exception {
        LoggingDataSource dataSource = new LoggingDataSource(DataSourceUtil.getDataSource());
        Connection conn = dataSource.getConnection();
        Statement statement = conn.createStatement();
        statement.executeQuery("SELECT * FROM \"sqllogger\".TEST ");
    }
}
