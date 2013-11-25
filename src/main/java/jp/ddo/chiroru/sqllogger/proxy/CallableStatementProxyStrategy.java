package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.Method;

public class CallableStatementProxyStrategy
extends AbstractProxyStrategy
implements ProxyStrategy {

    public CallableStatementProxyStrategy(Object target) {
        super(target);
    }

    @Override
    public boolean isProxyMethod(String methodName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void preProcess(Method method) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void postProcess(Method method) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected Object enwrapTarget(Object o, Method method) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
