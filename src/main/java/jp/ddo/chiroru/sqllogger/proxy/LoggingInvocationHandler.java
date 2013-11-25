package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingInvocationHandler<T>
implements InvocationHandler {

    final T target;
    private ProxyStrategy strategy = null;

    public LoggingInvocationHandler(T target, ProxyStrategy strategy) {
        this.target = target;
        this.strategy = strategy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] arguments)
            throws Throwable {

        Object result = null;

        System.out.println("クラス [" + target.getClass().getSimpleName() + "] のメソッド　[ " + method.getName() + "] の呼出しをプロキシします.");

        if (strategy != null && strategy.isProxyMethod(method.getName())) {
            result = strategy.invoke(method, arguments);
        } else {
            method.invoke(target, arguments);
        }

        System.out.println("クラス [" + target.getClass().getSimpleName() + "] のメソッド　[ " + method.getName() + "] の呼出しをプロキシしました.");
        return result;
    }

}
