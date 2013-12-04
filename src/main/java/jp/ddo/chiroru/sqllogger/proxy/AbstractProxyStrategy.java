package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jp.ddo.chiroru.sqllogger.model.LogInfo;

public abstract class AbstractProxyStrategy
implements ProxyStrategy {

    protected String traceId;

    protected Object target;

    protected LogInfo logInfo;

    public AbstractProxyStrategy(Object target, LogInfo logInfo) {
        this.target = target;
        this.logInfo = logInfo;
    }

    @Override
    public void setTraceId(String traceId) {
        this.traceId = traceId;
        logInfo.setTraceId(traceId);
    }

    @Override
    public Object invoke(Method method, Object[] arguments)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object result = null;

        preProcess(method, arguments);
        try {
            result = method.invoke(target, arguments);
        } finally {
            postProcess(method, arguments);
        }
        return enwrapTarget(result, method);
    }

    @Override
    public LogInfo getLogInfo() {
        return logInfo;
    }
    protected abstract void preProcess(Method method, Object[] arguments);

    protected abstract void postProcess(Method method, Object[] arguments);

    protected abstract Object enwrapTarget(Object o, Method method);
}
