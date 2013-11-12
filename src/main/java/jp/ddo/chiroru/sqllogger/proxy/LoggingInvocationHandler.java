package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingInvocationHandler<T>
		implements InvocationHandler {

	final T object;
	private ProxyStrategy strategy = null;

	public LoggingInvocationHandler(T object) {
		this.object = object;
	}

	public LoggingInvocationHandler(T object, ProxyStrategy strategy) {
		this.object = object;
		this.strategy = strategy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
			throws Throwable {

		System.out.println("log -----> : " + method.getName());

		Object result = method.invoke(object, arguments);
		if (strategy == null)
			System.out.println("strategy is null!!!!!");
		if (strategy != null && strategy.isProxyMethod(method.getName())) {
			System.out.println("strategy");
			result = strategy.invoke(proxy, method, arguments, result);
		}

		return result;
	}

}
