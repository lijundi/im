package com.cad.im.service;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.util.HttpUtil;
import com.cad.im.util.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Name: com.cad.im.service.QAService
 * @Date: 2020/11/9
 * @Auther: weiwending
 * @Description: QA问答
 */
@Service
public class KnowledgeService {
    private String url = "http://114.67.200.39:7662/qaai/";

    public Result getQAList(String requestJson) {
        String tranurl = url + "predict";
        JSONObject tranJson = new JSONObject();
        List list = new ArrayList();
        ResponseEntity responseEntity = HttpUtil.postJson(tranurl, requestJson);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody().toString());
        jsonObject = jsonObject.getJSONObject("retData");
        int i = 0;
        while (jsonObject.get(String.valueOf(i)) != null) {
            Map map = new HashMap();
            map.put("qid", jsonObject.getJSONObject(String.valueOf(i)).get("qapid"));
            map.put("content", jsonObject.getJSONObject(String.valueOf(i)).get("question"));
            list.add(map);
            i++;
        }
        tranJson.put("data", list);
        return Result.success(tranJson);
    }

    public Result getAnswer(String requestJson) {
        String tranurl = url + "id2Answer";
        JSONObject tranJson = new JSONObject();
        ResponseEntity responseEntity = HttpUtil.postJson(tranurl, requestJson);
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody().toString());
        tranJson.put("answer", jsonObject.get("retData"));
        return Result.success(tranJson);
    }

}
