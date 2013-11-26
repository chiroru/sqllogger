package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class PreparedStatementProxyStrategy
    extends AbstractProxyStrategy
    implements ProxyStrategy {

    private Set<String> enwrapTargetMethodName = new HashSet<>();
    
    public PreparedStatementProxyStrategy(String sessionId, Object target) {
        super(sessionId, target);
        enwrapTargetMethodName.add("executeQuery");
    }

    @Override
    public boolean isProxyMethod(String methodName) {
        if (enwrapTargetMethodName.contains(methodName))
            return true;
        
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
        if (enwrapTargetMethodName.contains(method.getName())) {
            return ProxyFactory.getProxy(ResultSet.class, (ResultSet)o, new ResultSetProxyStrategy(sessionId, o));
        }
        
        return o;
    }
        

}
