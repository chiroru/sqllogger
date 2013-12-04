package jp.ddo.chiroru.sqllogger.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Query {

    private String query;

    private boolean isPrepared;

    private Map<String, Object> queryParamterMap = new LinkedHashMap<>();

    public Query(String query) {
        this(query, false);
    }

    public Query(String query, boolean isPrepared) {
        this.query = query;
        this.isPrepared = isPrepared;
    }

    public String getQuery() {
        return query;
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void putParameter(Object parameterIndex, Object value) {
        queryParamterMap.put(String.valueOf(parameterIndex), value);
    }
    
    public Map<String, Object> getParameter() {
        return queryParamterMap;
    }
}
