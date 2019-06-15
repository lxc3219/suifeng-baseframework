package org.suifeng.baseframework.api.common.helper;


import com.fasterxml.jackson.core.type.TypeReference;
import org.suifeng.baseframework.api.rest.RestProperties;
import org.suifeng.baseframework.api.common.domain.ResultCode;
import org.suifeng.baseframework.api.common.exception.BizException;
import org.suifeng.baseframework.api.common.exception.RestException;
import org.suifeng.baseframework.common.util.databind.JsonMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;

/**
 * 使用spring的restTemplate替代httpClient工具
 */
@Slf4j
@Component
public class RestClientHelper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestProperties restProperties;

    public <T> T get(String url, TypeReference<T> typeReference)  {
        String serverAddr = restProperties.getServerAddr();
        try {
            log.debug("get请求地址：{}", serverAddr + url);
            String result = restTemplate.getForObject(serverAddr + url, String.class);
            log.debug("返回结果：{}", result);
            return parseJson(result, typeReference);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接");
        } catch (RestException ex) {
            log.error("调用get接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用get接口失败" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    public <T> T get(String url, Class<T> responseType) {
        String serverAddr = restProperties.getServerAddr();
        try {
            log.debug("get请求地址：{}", serverAddr + url);
            String result = restTemplate.getForObject(serverAddr + url, String.class);
            log.debug("返回结果：{}", result);
            return parseJson(result, responseType);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接");
        } catch (RestException ex) {
            log.error("调用get接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用get接口失败" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    public void post(String url, Object request) {
        post(url, request, Void.class);
    }

    public <T> T post(String url, Object request, Class<T> responseType) {
        String serverAddr = restProperties.getServerAddr();
        try {
            String result = restTemplate.postForObject(serverAddr + url, request, String.class);
            log.debug("返回结果:{}", result);
            return parseJson(result, responseType);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接");
        } catch (RestException ex) {
            log.error("调用post接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用post接口失败:" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    public <T> T post(String url, Object request, TypeReference<T> typeReference) {
        String serverAddr = restProperties.getServerAddr();
        log.debug("post请求地址：{}，请求参数：{}", serverAddr + url, request);
        try {
            String result = restTemplate.postForObject(serverAddr + url, request, String.class);
            log.debug("返回结果：{}", result);
            return parseJson(result, typeReference);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接：" + serverAddr + url);
        } catch (RestException ex) {
            log.error("调用post接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用post接口失败" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    public <T> T post(String url, Object request, TypeReference<T> typeReference, Object... objects) {
        String serverAddr = restProperties.getServerAddr();
        try {
            log.debug("post请求地址：{}，请求参数：{}", serverAddr + url, request);
            String result = restTemplate.postForObject(serverAddr + url, request, String.class, objects);
            log.debug("返回结果：{}", result);
            return parseJson(result, typeReference);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接");
        } catch (RestException ex) {
            log.error("调用post接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用post接口失败" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    public void put(String url, Object request) {
        put(url, request, Void.class);
    }

    public <T> T put(String url, Object request, Class<T> responseType) {
        String serverAddr = restProperties.getServerAddr();
        log.debug("put请求地址：{}，请求参数：{}", serverAddr + url, request);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<Object> entity = new HttpEntity<Object>(request, headers);
            ResponseEntity<String> response = restTemplate.exchange(serverAddr + url, HttpMethod.PUT, entity, String.class);
            String result = response.getBody();
            log.debug("返回结果：{}", result);
            return parseJson(result, responseType);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接");
        } catch (RestException ex) {
            log.error("调用put接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用put接口失败" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    /**
     * 调用删除的rest接口
     */
    public void delete(String url) {
        delete(url, Void.class);
    }

    /**
     * 调用删除的rest接口
     * @param url          地址
     * @param responseType 返回类型
     * @return 结果
     */
    public <T> T delete(String url, Class<T> responseType) {
        String serverAddr = restProperties.getServerAddr();
        log.debug("DELETE请求地址：{}", serverAddr + url);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<Object> entity = new HttpEntity<Object>("", headers);
            ResponseEntity<String> response = restTemplate.exchange(serverAddr + url, HttpMethod.DELETE, entity, String.class);
            String result = response.getBody();
            log.debug("返回结果：{}", result);
            return parseJson(result, responseType);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            log.error("接口服务器无法连接", e);
            throw new BizException("接口服务器无法连接");
        } catch (RestException ex) {
            log.error("调用delete接口失败" + serverAddr + url + ",错误代码：" + ex.getStatus(), ex);
            ResultCode resultCode = parseJson(ex.getMessage(), ResultCode.class);
            throw new BizException(resultCode);
        } catch (Exception ex) {
            log.error("调用DELETE接口失败" + serverAddr + url, ex);
            throw new BizException(ex.getMessage());
        }
    }

    /**
     * 反序列化
     * @param result
     * @param responseType
     * @param <T>
     * @return
     */
    private <T> T parseJson(String result, Class<T> responseType) {
        if (responseType == String.class) {
            return responseType.cast(result);
        }
        if (responseType == Void.class) {
            return null;
        }
        JsonMapperUtils mapper = new JsonMapperUtils();
        T res = (T) mapper.fromJson(result, responseType);
        return res;
    }

    /**
     * 反序列化
     * @param result
     * @param typeReference
     * @param <T>
     * @return
     */
    private <T> T parseJson(String result, TypeReference<?> typeReference) {
        Type type = typeReference.getType();
        if (type instanceof Class<?>) {
            Class<?> clz = (Class<?>) type;
            if (clz == String.class) {
                return (T) result;
            }
        }
        JsonMapperUtils mapper = new JsonMapperUtils();
        T res = (T) mapper.fromJson(result, typeReference);
        return res;
    }

    /**
     * 使用RestTemplate进行文件操作（上传、转发）时用到
     * 当方法参数中涉及MultipartFile
     * @createTime 2019/6/14 16:17
     * @author luoxc
     */
//    public MultiValueMap<String, Object> convert(Object obj) {
//        if (obj == null) {
//            return null;
//        }
//        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
//        return multiValueMap;
//    }


}