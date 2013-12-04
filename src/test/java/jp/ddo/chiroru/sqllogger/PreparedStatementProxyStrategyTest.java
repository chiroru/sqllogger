package jp.ddo.chiroru.sqllogger;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import jp.ddo.chiroru.sqllogger.util.DataSourceUtil;
import jp.ddo.chiroru.sqllogger.util.Environment;
import jp.ddo.chiroru.sqllogger.util.H2DatabaseTCPServerManager;
import jp.ddo.chiroru.sqllogger.util.MigrationManager;

import org.junit.Before;
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
public class PreparedStatementProxyStrategyTest {

    private DataSource dataSource;

    @ClassRule
    public static H2DatabaseTCPServerManager serverManager = new H2DatabaseTCPServerManager();

    @BeforeClass
    public static void setUpClass() {
        MigrationManager.maigration(Environment.UnitTest);
    }

    @Before
    public void setUp() {
        dataSource = new LoggingDataSource(DataSourceUtil.getDataSource());
    }

    @Test
    public void DataSourceのラッパーを生成できる()
            throws Exception {
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO \"sqllogger\".TEST (TEST_ID, TEST_NAME) VALUES (?, ?)");
        ps.setInt(1, 1);
        ps.setString(2, "TEST1");
        ps.addBatch();
        ps.setInt(1, 2);
        ps.setString(2, "TEST2");
        ps.addBatch();
        ps.setInt(1, 3);
        ps.setString(2, "TEST3");
        ps.addBatch();
        ps.executeBatch();
    }
}
