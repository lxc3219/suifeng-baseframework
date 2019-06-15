package org.suifeng.baseframework.api.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 * 响应消息,controller中处理后，返回此对象，响应请求结果给客户端。
 * @param <T>
 */
@ApiModel(value = "响应结果")
@Setter
@Getter
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "是否成功", required =  true)
    private Boolean success;

    @ApiModelProperty(value = "状态码", required = true)
    protected Integer status;

    @ApiModelProperty(value = "业务代码，失败时返回具体错误码")
    private Integer code;

    @ApiModelProperty(value = "成功时具体返回值，失败时为null")
    protected T data;

    @ApiModelProperty(value = "成功时返回null，失败时返回具体错误消息")
    protected String msg;

    @ApiModelProperty(value = "时间戳", required = true, dataType = "Long")
    private Long timestamp;

    /**
     * 过滤字段：指定需要序列化的字段
     */
//    @ApiModelProperty(hidden = true)
//    private transient Map<Class<?>, Set<String>> includes;

    /**
     * 过滤字段：指定不需要序列化的字段
     */
//    @ApiModelProperty(hidden = true)
//    private transient Map<Class<?>, Set<String>> excludes;

    public Result() {}

//    public Result<T> include(Class<?> type, String... fields) {
//        return include(type, Arrays.asList(fields));
//    }

//    public Result<T> include(Class<?> type, Collection<String> fields) {
//        if (includes == null) {
//            includes = new HashMap<>();
//        }
//        if (fields == null || fields.isEmpty()) {
//            return this;
//        }
//        fields.forEach(field -> {
//            if (field.contains(".")) {
//                String tmp[] = field.split("[.]", 2);
//                try {
//                    Field field1 = type.getDeclaredField(tmp[0]);
//                    if (field1 != null) {
//                        include(field1.getType(), tmp[1]);
//                    }
//                } catch (Exception ignore) {
//                }
//            } else {
//                getStringListFromMap(includes, type).add(field);
//            }
//        });
//        return this;
//    }

//    public Result<T> exclude(Class type, String... fields) {
//        return exclude(type, Arrays.asList(fields));
//    }

//    public Result<T> exclude(Class type, Collection<String> fields) {
//        if (excludes == null) {
//            excludes = new HashMap<>();
//        }
//        if (fields == null || fields.isEmpty()) {
//            return this;
//        }
//        fields.forEach(field -> {
//            if (field.contains(".")) {
//                String tmp[] = field.split("[.]", 2);
//                try {
//                    Field field1 = type.getDeclaredField(tmp[0]);
//                    if (field1 != null) {
//                        exclude(field1.getType(), tmp[1]);
//                    }
//                } catch (Exception ignore) {
//                }
//            } else {
//                getStringListFromMap(excludes, type).add(field);
//            }
//        });
//        return this;
//    }

//    public Result<T> exclude(String... fields) {
//        return exclude(Arrays.asList(fields));
//    }

//    public Result<T> exclude(Collection<String> fields) {
//        if (excludes == null) {
//            excludes = new HashMap<>();
//        }
//        if (fields == null || fields.isEmpty()) {
//            return this;
//        }
//        Class type;
//        if (getData() != null) {
//            type = getData().getClass();
//        } else {
//            return this;
//        }
//        exclude(type, fields);
//        return this;
//    }

//    public Result<T> include(String... fields) {
//        return include(Arrays.asList(fields));
//    }

//    public Result<T> include(Collection<String> fields) {
//        if (includes == null) {
//            includes = new HashMap<>();
//        }
//        if (fields == null || fields.isEmpty()) {
//            return this;
//        }
//        Class type;
//        if (getData() != null) {
//            type = getData().getClass();
//        } else {
//            return this;
//        }
//        include(type, fields);
//        return this;
//    }

//    protected Set<String> getStringListFromMap(Map<Class<?>, Set<String>> map, Class type) {
//        return map.computeIfAbsent(type, k -> new HashSet<>());
//    }

    public Result<T> success(Boolean success) {
        this.success = success;
        return this;
    }

    public Result<T> status(Integer status) {
        this.status = status;
        return this;
    }

    public Result<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }

    public Result<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result<T> putTimeStamp() {
        this.timestamp = System.currentTimeMillis();
        return this;
    }


}