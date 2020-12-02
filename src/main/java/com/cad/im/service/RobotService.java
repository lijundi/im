package com.cad.im.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RobotService {

    public JSONObject[] sendMsgToRobot(String sender, String content){
//        String url="http://10.108.211.136:5005/webhooks/rest/webhook";
//        String url="http://127.0.0.1:5005/webhooks/rest/webhook";
        String url="http://114.67.200.39:10201/webhooks/rest/webhook";
        JSONObject jo = new JSONObject();
        jo.put("sender", sender);
        jo.put("message", content);
        ResponseEntity<JSONObject[]> responseEntity = postJsonArray(url, jo.toJSONString());
        return responseEntity.getBody();
    }

    public JSONObject sendRestartToRobot(String sender){
//        String url = "http://10.108.211.136:5005/conversations/" + String.valueOf(sender) + "/tracker/events";
//        String url = "http://127.0.0.1:5005/conversations/" + String.valueOf(sender) + "/tracker/events";
        String url = "http://114.67.200.39:10201/conversations/" + sender + "/tracker/events";
        JSONObject jo = new JSONObject();
        jo.put("event", "restart");
        ResponseEntity<JSONObject> responseEntity = postJsonObject(url, jo.toJSONString());
        return responseEntity.getBody();
    }

    public static JSONObject judgeType(JSONObject message, JSONObject msgObject) {
        String type = msgObject.getString("type");
        if (type.equals("navigate")) {
            message.put("type", "navigate");
            String navigate = msgObject.getString("navigate");
            message.put("navigate", navigate);
        }
        else if (type.equals("num")) {
            message.put("type", "num");
        }
        else {
            message.put("type", "buttons");
            JSONArray buttons = msgObject.getJSONArray("buttons");
            message.put("buttons", buttons);
        }
        return message;
    }

    private ResponseEntity<JSONObject[]> postJsonArray(String url, String requestJson) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<JSONObject[]> responseEntity;
        responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject[].class);
        return responseEntity;
    }

    private ResponseEntity<JSONObject> postJsonObject(String url, String requestJson) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<JSONObject> responseEntity;
        responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);
        return responseEntity;
    }
}
