package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import jp.ddo.chiroru.sqllogger.model.LogInfo;
import jp.ddo.chiroru.sqllogger.model.Query;

/**
 * <p>
 * プロキシ対象かつ返却値としてプロキシを返すメソッドは以下の通り.
 * </p>
 * <ul>
 *     <li>#createStatement()</li>
 *     <li>#createStatement(int resultSetType, int resultSetConcurrency)</li>
 *     <li>#createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)</li>
 * </ul>
 * <ul>
 *     <li>#prepareStatement(String sql)</li>
 *     <li>#prepareStatement(String sql, int autoGeneratedKeys)</li>
 *     <li>#prepareStatement(String sql, int[] columnIndexes)</li>
 *     <li>#prepareStatement(String sql, int resultSetType, int resultSetConcurrency)</li>
 *     <li>#prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)</li>
 *     <li>#prepareStatement(String sql, String[] columnNames)</li>
 * </ul>
 * <ul>
 *     <li>#prepareCall(String sql)</li>
 *     <li>#prepareCall(String sql, int resultSetType, int resultSetConcurrency)</li>
 *     <li>#prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)</li>
 * </ul>
 * @author smts1008@outlook.com
 * @since 1.0.0
 */
public class ConnectionProxyStrategy
extends AbstractProxyStrategy
implements ProxyStrategy {
    
    private Set<String> enwrapTargetMethodName = new HashSet<>();

    public ConnectionProxyStrategy(Object target, LogInfo logInfo) {
        super(target, logInfo);
        enwrapTargetMethodName.add("createStatement");
        enwrapTargetMethodName.add("prepareStatement");
        enwrapTargetMethodName.add("prepareCall");
    }

    public boolean isProxyMethod(String methodName) {
        if (enwrapTargetMethodName.contains(methodName)) {
            return true;
        }

        return false;
    }

    @Override
    public void preProcess(Method method, Object[] arguments) {
    }

    @Override
    public void postProcess(Method method, Object[] arguments) {
        if (method.getName().equals("prepareStatement")) {
            logInfo.setQuery(new Query(arguments[0].toString()));
        }
    }

    @Override
    public Object enwrapTarget(Object o, Method method) {
        if (method.getName().equals("createStatement")) {
            return ProxyFactory.getProxy(traceId, Statement.class, (Statement)o, new StatementProxyStrategy(o, logInfo));
        } else if (method.getName().equals("prepareStatement")) {
            return ProxyFactory.getProxy(traceId, PreparedStatement.class, (PreparedStatement)o, new PreparedStatementProxyStrategy(o, logInfo));
        } else if (method.getName().equals("prepareCall")) {
            return ProxyFactory.getProxy(traceId, CallableStatement.class, (CallableStatement)o, new CallableStatementProxyStrategy(o, logInfo));
        }
        return o;
    }

}
