package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.Method;
import java.sql.Statement;

public class ConnectionProxyStrategy implements ProxyStrategy {
    
    private String[] proxyMethods = {"createStatement"};
    
    public boolean isProxyMethod(String methodName) {
        for (String proxyMethodName : proxyMethods) {
            System.out.println(methodName + "->" + proxyMethodName);
            if (proxyMethodName.equals(methodName)) {
                return true;
            }
        }
        return false;
    }

    public Object invoke(Object proxy, Method method, Object[] arguments, Object object) {
        return proxyGetConnection(proxy, method, arguments, object);
    }

    private Object proxyGetConnection(Object proxy, Method method, Object[] arguments, Object object) {
        Statement statement = ProxyFactory.getProxy(Statement.class, (Statement)object, null);
        return statement;
    }
}
