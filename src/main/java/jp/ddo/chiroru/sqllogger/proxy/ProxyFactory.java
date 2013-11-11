package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author smts1008@outlook.com
 * @version 1.0.0
 */
public class ProxyFactory {

    @SuppressWarnings("unchecked")
    public static<T> T getProxy(Class<T> intf,
                                final T obj,
                                ProxyStrategy strategy) {
        
        InvocationHandler handler;
        if (strategy == null) {
            handler = new LoggingInvocationHandler(obj);
        } else {
            System.out.println("================");
            if (strategy == null) 
                System.out.println("+++++++++++++++");
            handler = new LoggingInvocationHandler(obj, strategy);
        }
        
        T newProxyInstance = (T)Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                                                       new Class[] {intf},
                                                       handler);
        
        return newProxyInstance;
    }
}
