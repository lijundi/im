package com.cad.im.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author lijundi
 * @date 2020/10/27 11:22
 */
public class HttpUtil {
    public static ResponseEntity<JSONObject> getJson(String url, Map<String, String> params) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, JSONObject.class, params);
    }

    public static ResponseEntity<JSONObject> getJson(String url) {
        // 由于微信返回时text/plain 故配置消息转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate.getForEntity(url, JSONObject.class);
    }

    public static ResponseEntity<JSONObject> postJson(String url, String requestJson) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<JSONObject> responseEntity;
        responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);
        return responseEntity;
    }

    public static ResponseEntity<String> postJson2(String url, String requestJson) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> responseEntity;
        responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return responseEntity;
    }

    public static ResponseEntity<JSONObject[]> postJson3(String url, String requestJson) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<JSONObject[]> responseEntity;
        responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject[].class);
        return responseEntity;
    }

}
