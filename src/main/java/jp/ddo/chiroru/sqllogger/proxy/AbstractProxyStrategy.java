package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractProxyStrategy
        implements ProxyStrategy {

    protected Object target;
    
    public AbstractProxyStrategy(Object target) {
        this.target = target;
    }

    public Object invoke(Method method, Object[] arguments)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object result = null;

        preProcess(method);
        try {
            result = method.invoke(target, arguments);
        } finally {
            postProcess(method);
        }
        return enwrapTarget(result, method);
    }

    protected abstract void preProcess(Method method);

    protected abstract void postProcess(Method method);

    protected abstract Object enwrapTarget(Object o, Method method);
}
