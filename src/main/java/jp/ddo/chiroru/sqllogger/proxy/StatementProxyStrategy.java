package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.Method;

public class StatementProxyStrategy implements ProxyStrategy {

    @Override
    public boolean isProxyMethod(String methodName) {
        if (methodName.equals("executeQuery"))
            return true;
        return false;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] arguments, Object object) {
        if (method.getName().equals("executeQuery"))
            System.out.println(arguments[0]);
        return null;
    }

}
