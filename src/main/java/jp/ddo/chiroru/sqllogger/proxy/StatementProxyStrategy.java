package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import jp.ddo.chiroru.sqllogger.SQLLogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger L =
            LoggerFactory.getLogger(SQLLogger.class);
    
    private Long startTime;
    
    private Set<String> enwrapTargetMethodName = new HashSet<>();
    
    public StatementProxyStrategy(String sessionId, Object target) {
        super(sessionId, target);
        enwrapTargetMethodName.add("addBatch");
        enwrapTargetMethodName.add("execute");
        enwrapTargetMethodName.add("executeQuery");
        enwrapTargetMethodName.add("executeUpdate");
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
        Long elapsed = System.currentTimeMillis() - startTime;
        StringBuilder message = new StringBuilder();
        message.append(elapsed);
        message.append(",");
        if (method.getName().equals("executeQuery") || method.getName().equals("execute") || method.getName().equals("addBatch")) {
            message.append(arguments[0].toString());
        }
        L.info(message.toString());
    }

    @Override
    protected Object enwrapTarget(Object o, Method method) {
        if (method.getName().equals("executeQuery") || method.getName().equals("getResultSet")) {
            return ProxyFactory.getProxy(ResultSet.class, (ResultSet)o, new ResultSetProxyStrategy(sessionId, o));
        }
        
        return o;
    }

}
