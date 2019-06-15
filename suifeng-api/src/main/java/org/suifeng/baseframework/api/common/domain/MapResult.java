package org.suifeng.baseframework.api.common.domain;

import java.util.LinkedHashMap;
import java.util.Map;


public class MapResult extends Result<Map<String, Object>> {

    public MapResult() {
        data(new LinkedHashMap<>());
    }

    public MapResult put(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public static MapResult ok() {
        return new MapResult();
    }

    public static MapResult ok(String msg) {
        MapResult mapResult = new MapResult();
        mapResult.msg = msg;
        return mapResult;
    }

    public static MapResult error() {
        return new MapResult();
    }


    public static MapResult error(String msg) {
        MapResult mapResult = new MapResult();
        mapResult.msg = msg;
        return mapResult;
    }

    public static MapResult error(int status, String msg) {
        MapResult mapResult = new MapResult();
        mapResult.msg = msg;
        mapResult.status = status;
        return mapResult;
    }
}
