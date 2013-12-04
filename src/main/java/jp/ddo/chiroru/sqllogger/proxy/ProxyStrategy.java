package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jp.ddo.chiroru.sqllogger.model.LogInfo;

/**
 * @author smts1008@outlook.com
 * @version 1.0.0
 */
public interface ProxyStrategy {

    boolean isProxyMethod(String methodName);

    void setTraceId(String traceId);
    
    Object invoke(Method method, Object[] arguments)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;

    LogInfo getLogInfo();
}
