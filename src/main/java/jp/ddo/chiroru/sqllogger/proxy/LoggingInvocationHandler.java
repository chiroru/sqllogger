package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingInvocationHandler<T>
		implements InvocationHandler {

	final T object;
	private ProxyStrategy strategy = null;

	public LoggingInvocationHandler(T object, ProxyStrategy strategy) {
		this.object = object;
		this.strategy = strategy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
			throws Throwable {

		System.out.println("クラス [" + object.getClass().getSimpleName() + "] のメソッド　[ " + method.getName() + "] の呼出しをプロキシします.");

		Object result = method.invoke(object, arguments);

		if (strategy != null && strategy.isProxyMethod(method.getName())) {
			result = strategy.invoke(proxy, method, arguments, result);
		}
        System.out.println("クラス [" + object.getClass().getSimpleName() + "] のメソッド　[ " + method.getName() + "] の呼出しをプロキシしました.");
		return result;
	}

}
