package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class PreparedStatementProxyStrategy
    extends AbstractProxyStrategy
    implements ProxyStrategy {

    private Set<String> enwrapTargetMethodName = new HashSet<>();
    
    public PreparedStatementProxyStrategy(Object target) {
        super(target);
        enwrapTargetMethodName.add("executeQuery");
    }

    @Override
    public boolean isProxyMethod(String methodName) {
        if (enwrapTargetMethodName.contains(methodName))
            return true;
        
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
        if (enwrapTargetMethodName.contains(method.getName())) {
            return ProxyFactory.getProxy(ResultSet.class, (ResultSet)o, new ResultSetProxyStrategy(o));
        }
        
        return o;
    }
        

}
