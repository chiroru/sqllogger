package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.Method;

import jp.ddo.chiroru.sqllogger.model.LogInfo;

public class ResultSetProxyStrategy
extends AbstractProxyStrategy
implements ProxyStrategy {

    public ResultSetProxyStrategy(Object target, LogInfo logInfo) {
        super(target, logInfo);
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
        return o;
    }

}
