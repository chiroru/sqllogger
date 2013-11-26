package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.Method;

public class CallableStatementProxyStrategy
extends AbstractProxyStrategy
implements ProxyStrategy {
    
    public CallableStatementProxyStrategy(String sessionId, Object target) {
        super(sessionId, target);
        
    }

    @Override
    public boolean isProxyMethod(String methodName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void preProcess(Method method, Object[] arguments) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void postProcess(Method method, Object[] arguments) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected Object enwrapTarget(Object o, Method method) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
