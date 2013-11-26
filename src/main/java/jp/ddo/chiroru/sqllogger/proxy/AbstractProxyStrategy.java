package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractProxyStrategy
        implements ProxyStrategy {

    protected String sessionId;
    protected Object target;
    
    public AbstractProxyStrategy(String sessionId, Object target) {
        this.sessionId = sessionId;
        this.target = target;
    }

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

    protected abstract void preProcess(Method method, Object[] arguments);

    protected abstract void postProcess(Method method, Object[] arguments);

    protected abstract Object enwrapTarget(Object o, Method method);
}
