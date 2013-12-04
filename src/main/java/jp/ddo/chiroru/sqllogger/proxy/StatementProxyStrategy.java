package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import jp.ddo.chiroru.sqllogger.model.LogInfo;
import jp.ddo.chiroru.sqllogger.model.Query;

/**
 * <p>
 * 課題
 * #getConnection()で呼出し元のConnectionの動的プロキシインスタンスを返却する必要がある。
 * </p>
 * 
 * @author smts1008@outlook.com
 *
 */
public class StatementProxyStrategy
        extends AbstractProxyStrategy
        implements ProxyStrategy {

    private Long startTime;
    
    private Set<String> enwrapTargetMethodName = new HashSet<>();
    
    public StatementProxyStrategy(Object target, LogInfo logInfo) {
        super(target, logInfo);
        enwrapTargetMethodName.add("addBatch");
        enwrapTargetMethodName.add("execute");
        enwrapTargetMethodName.add("executeQuery");
        enwrapTargetMethodName.add("executeUpdate");
        enwrapTargetMethodName.add("executeBatch");
        enwrapTargetMethodName.add("getGeneratedKeys");
        enwrapTargetMethodName.add("getResultSet");
    }

    @Override
    public boolean isProxyMethod(String methodName) {
        if (enwrapTargetMethodName.contains(methodName)) {
            return true;
        }
        
        return false;
    }

    @Override
    protected void preProcess(Method method, Object[] arguments) {
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void postProcess(Method method, Object[] arguments) {
        long elapsed = System.currentTimeMillis() - startTime;
        if (method.getName().equals("executeQuery") || method.getName().equals("execute")) {
            logInfo.setQuery(new Query(arguments[0].toString()));
            logInfo.setElapsed(elapsed);
            logInfo.setExecuteQuery(true);
        } else if (method.getName().equals("executeBatch")) {
            logInfo.setElapsed(elapsed);
            logInfo.setExecuteQuery(true);
        } else if (method.getName().equals("addBatch")) {
            logInfo.setQuery(new Query(arguments[0].toString()));
        }
    }

    @Override
    protected Object enwrapTarget(Object o, Method method) {
        if (method.getName().equals("executeQuery") || method.getName().equals("getResultSet")) {
            return ProxyFactory.getProxy(traceId, ResultSet.class, (ResultSet)o, new ResultSetProxyStrategy(o, logInfo));
        }
        
        return o;
    }

}
