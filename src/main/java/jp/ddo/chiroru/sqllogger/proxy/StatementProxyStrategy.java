package jp.ddo.chiroru.sqllogger.proxy;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 課題
 * #getConnection()で呼出し元のConnectionの動的プロキシインスタンスを返却する必要がある。
 * </p>
 * 
 * @author smts1008@outlook.com
 *
 */
public class StatementProxyStrategy
        extends AbstractProxyStrategy
        implements ProxyStrategy {

    private Set<String> enwrapTargetMethodName = new HashSet<>();
    
    public StatementProxyStrategy(Object target) {
        super(target);
        enwrapTargetMethodName.add("executeQuery");
        enwrapTargetMethodName.add("getGeneratedKeys");
        enwrapTargetMethodName.add("getResultSet");
    }

    @Override
    public boolean isProxyMethod(String methodName) {
        if (enwrapTargetMethodName.contains(methodName)) {
            return true;
        }
        
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
