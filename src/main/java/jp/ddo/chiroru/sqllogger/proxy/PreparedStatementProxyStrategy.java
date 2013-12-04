package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import jp.ddo.chiroru.sqllogger.model.LogInfo;
import jp.ddo.chiroru.sqllogger.model.Query;

public class PreparedStatementProxyStrategy
extends AbstractProxyStrategy
implements ProxyStrategy {

    private Long startTime;

    private Set<String> enwrapTargetMethodName = new HashSet<>();

    public PreparedStatementProxyStrategy(Object target, LogInfo logInfo) {
        super(target, logInfo);
        enwrapTargetMethodName.add("executeQuery");
        enwrapTargetMethodName.add("executeUpdate");
        enwrapTargetMethodName.add("execute");
        enwrapTargetMethodName.add("executeBatch");
        enwrapTargetMethodName.add("setInt");
        enwrapTargetMethodName.add("setString");
    }

    @Override
    public boolean isProxyMethod(String methodName) {
        if (enwrapTargetMethodName.contains(methodName))
            return true;

        return false;
    }

    @Override
    protected void preProcess(Method method, Object[] arguments) {
        startTime = System.currentTimeMillis();
        if (method.getName().matches("^set.*")) {
            Query q = logInfo.getQuery();
            q.putParameter(arguments[0], arguments[1]);
        }
    }

    @Override
    protected void postProcess(Method method, Object[] arguments) {
        long elapsed = System.currentTimeMillis() - startTime;
        if (!method.getName().matches("^set.*")) {
            logInfo.setElapsed(elapsed);
            logInfo.setExecuteQuery(true);
        }
    }

    @Override
    protected Object enwrapTarget(Object o, Method method) {
        if (method.getName().equals("executeQuery")) {
            return ProxyFactory.getProxy(traceId, ResultSet.class, (ResultSet)o, new ResultSetProxyStrategy(o, logInfo));
        }

        return o;
    }


}
