package jp.ddo.chiroru.sqllogger.model;

import java.util.Stack;

public class LogInfo {

    private Stack<Query> queryStack = new Stack<>();

    private long elapsed = 0L;
    
    private boolean executeQuery = false;
    
    private String traceId;
    
    public Stack<Query> getQueryList() {
        return queryStack;
    }

    public void setQuery(Query query) {
        this.queryStack.push(query);
    }

    public Query getQuery() {
        return queryStack.peek();
    }
    
    public long getElapsed() {
        return elapsed;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }

    public boolean isExecuteQuery() {
        return executeQuery;
    }

    public void setExecuteQuery(boolean executeQuery) {
        this.executeQuery = executeQuery;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void reset() {
        this.elapsed = 0L;
        this.executeQuery = false;
    }
}
