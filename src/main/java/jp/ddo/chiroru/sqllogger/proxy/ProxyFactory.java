package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * <p>
 * 動的プロキシのファクトリです.
 * デコレート対象のクラスに対する動的プロキシを生成します.
 * {@link }デコレートするプロセスは
 * </p>
 * @author smts1008@outlook.com
 * @version 1.0.0
 */
public class ProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(Class<T> clazz,
            final T obj,
            ProxyStrategy strategy) {

        if (strategy == null)
            throw new IllegalArgumentException("動的プロキシを生成するには{@link ProxyStrategy}の指定が必要です.");

        InvocationHandler handler;
        System.out.println("[ProxyFactory] [GENERATE] ================ [Start]");
        System.out.println("クラス [" + clazz.getSimpleName() + "] の動的プロキシを生成します.");
        handler = new LoggingInvocationHandler<T>(obj, strategy);
        T newProxyInstance = (T)Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                new Class[] {clazz},
                handler);
        System.out.println("[ProxyFactory] [GENERATE] ================ [End]");

        return newProxyInstance;
    }
}
