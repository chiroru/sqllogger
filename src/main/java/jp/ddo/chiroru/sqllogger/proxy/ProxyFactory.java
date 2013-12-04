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
    public static <T> T getProxy(
            String traceId,
            Class<T> clazz,
            final T obj,
            ProxyStrategy strategy) {

        if (strategy == null)
            throw new IllegalArgumentException("動的プロキシを生成するには{@link ProxyStrategy}の指定が必要です.");

        strategy.setTraceId(traceId);
        InvocationHandler handler = new LoggingInvocationHandler<T>(traceId, obj, strategy);
        T newProxyInstance = (T)Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                new Class[] {clazz},
                handler);

        return newProxyInstance;
    }
}
