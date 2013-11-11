package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.Method;

/**
 * @author smts1008@outlook.com
 * @version 1.0.0
 */
public interface ProxyStrategy {
    
    /**
     * @param 
     * @return 
     */
    public boolean isProxyMethod(String methodName);
    
    public Object invoke(Object proxy, Method method, Object[] arguments, Object object);
}
