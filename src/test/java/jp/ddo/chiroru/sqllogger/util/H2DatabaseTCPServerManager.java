package jp.ddo.chiroru.sqllogger.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.h2.tools.Server;
import org.h2.util.JdbcUtils;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * データベースサーバにアクセスするモジュールのユニットテスト実行時に、
 * H2 Database Server に対する事前処理、事後処理を行うための
 * {@code JUnit 4.x}系の{@link ExternalResource}です.
 * 
 * @author chiroru_0130@yahoo.co.jp
 * @since 1.0.0
 */
public class H2DatabaseTCPServerManager
extends ExternalResource {

    private static final Logger L = LoggerFactory.getLogger(H2DatabaseTCPServerManager.class);

    private final static ConnectionInfo configuration = ConnectionInfoProvider.provide(Environment.UnitTest);

    public H2DatabaseTCPServerManager() {
        printStartLog();
    }

    private Server server = null;

    @Override
    protected void before()
            throws Throwable {
        L.info("Execute H2DatabaseServerResource before method.Start up H2 Database Server...");
        server = Server.createTcpServer("-baseDir", configuration.getBaseDir());
        server.start();
        L.info("H2 Database Server started.");
        Properties info = new Properties();
        info.setProperty("user", configuration.getUsername());
        info.setProperty("password", configuration.getPassword());
        Connection conn = org.h2.Driver.load().connect(configuration.getUrl(), info);
        try {
            conn.createStatement()
            .execute("CREATE SCHEMA IF NOT EXISTS " + configuration.getSchema());
        } finally {
            JdbcUtils.closeSilently(conn);
        }
    }

    protected void after() {
        server.stop();
    }

    public void start() throws SQLException {
        L.info("Execute H2DatabaseServerResource before method.Start up H2 Database Server...");
        server = Server.createTcpServer("-baseDir", configuration.getBaseDir());
        server.start();
        L.info("H2 Database Server started.");
        Properties info = new Properties();
        info.setProperty("user", configuration.getUsername());
        info.setProperty("password", configuration.getPassword());
        Connection conn = org.h2.Driver.load().connect(configuration.getUrl(), info);
        try {
            conn.createStatement()
            .execute("CREATE SCHEMA IF NOT EXISTS " + configuration.getSchema());
        } finally {
            JdbcUtils.closeSilently(conn);
        }
    }
    
    public void stop() {
        server.stop();
    }
    
    private void printStartLog() {
        L.info("Initialize H2DatabaseServerResource.");
        L.info("[Loaded Properties] --------------------------------------------------");
        L.info(" Base Directory : " + configuration.getBaseDir());
        L.info(" Database Name  : " + configuration.getDatabaseName());
        L.info(" SCHEMA NAME    : " + configuration.getSchema());
        L.info(" USER NAME      : " + configuration.getUsername());
        L.info(" USER PASSWORD  : " + configuration.getPassword());
        L.info("----------------------------------------------------------------------"); 
    }
}
