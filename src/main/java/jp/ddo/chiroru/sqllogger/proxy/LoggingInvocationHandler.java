package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.ddo.chiroru.sqllogger.Constraints;
import jp.ddo.chiroru.sqllogger.SQLLogger;
import jp.ddo.chiroru.sqllogger.model.LogInfo;
import jp.ddo.chiroru.sqllogger.model.Query;

public class LoggingInvocationHandler<T>
implements InvocationHandler {

    private static final Logger L =
            LoggerFactory.getLogger(SQLLogger.class);

    private final static String DEMITA = ",";
    final T target;

    private ProxyStrategy strategy = null;

    private String traceId;

    public LoggingInvocationHandler(String traceId, T target, ProxyStrategy strategy) {
        this.traceId = traceId;
        this.target = target;
        this.strategy = strategy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] arguments)
            throws Throwable {

        Object result = null;
        String proxyTargetName = generateProxyTargetName(method, arguments);

        if (Constraints.traceValid) {
            L.info("[START] " + proxyTargetName);
        }

        if (strategy != null && strategy.isProxyMethod(method.getName())) {
            result = strategy.invoke(method, arguments);
        } else {
            method.invoke(target, arguments);
        }

        LogInfo logInfo = strategy.getLogInfo();
        if (logInfo.isExecuteQuery()) {
            StringBuilder queryLog = new StringBuilder();
            queryLog.append(logInfo.getTraceId());
            queryLog.append(DEMITA);
            queryLog.append(logInfo.getElapsed());
            queryLog.append(DEMITA);
            for (Query q : logInfo.getQueryList()) {
                queryLog.append(q.getQuery());
                queryLog.append(" ");
                Map<String, Object> qm = q.getParameter();
                for (String i : qm.keySet()) {
                    queryLog.append("[" + i + ":" + qm.get(i) + "] ");
                }
            }
            L.info(queryLog.toString());

            logInfo.reset();
        }
        if (Constraints.traceValid) {
            StringBuilder traceMessage = new StringBuilder();
            traceMessage.append("[END]   " + proxyTargetName);
            L.info("[END]   " + proxyTargetName);
        }
        return result;
    }

    private String generateProxyTargetName(Method method, Object[] arguments) {
        StringBuilder proxyTargetName = new StringBuilder();
        proxyTargetName.append(traceId + " ");
        proxyTargetName.append(target.getClass().getName());
        proxyTargetName.append("#");
        proxyTargetName.append(method.getName());
        proxyTargetName.append("(");
        if (arguments != null) {
            for (Object o : arguments) {
                proxyTargetName.append(o.getClass().getName());
                proxyTargetName.append(" ");
            }
        }
        proxyTargetName.append(")");
        return proxyTargetName.toString();
    }
}
