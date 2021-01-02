package com.cad.im.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.mysql.DirectoryTable;
import com.cad.im.entity.mysql.HtqaModelData;
import com.cad.im.repository.DirectoryTableRepository;
import com.cad.im.repository.HtqaModelDataRepository;
import com.cad.im.util.HttpUtil;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    DirectoryTableRepository directoryTableRepository;
    @Autowired
    HtqaModelDataRepository htqaModelDataRepository;


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

    public Result getNextList(int id) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();

        try {
            System.out.println(directoryTableRepository.findAllByPid(id));
            if (directoryTableRepository.findAllByPid(id) != null && !directoryTableRepository.findAllByPid(id).isEmpty()){
                JSONArray jsonArray = new JSONArray();
                for(DirectoryTable d : directoryTableRepository.findAllByPid(id)){
                    JSONObject jo = new JSONObject();
                    jo.put("id", d.getId());
                    jo.put("dName", d.getNode());
                    jsonArray.add(jo);
                }
                jsonObject1.put("type", "dir");
                jsonObject1.put("dList", jsonArray);
                return Result.success(jsonObject1);
            }else{
                try {
                    if (htqaModelDataRepository.findAllByDirectory_id(id) != null && !htqaModelDataRepository.findAllByDirectory_id(id).isEmpty()){
                        JSONArray jsonArray = new JSONArray();
                        for(HtqaModelData h : htqaModelDataRepository.findAllByDirectory_id(id)){
                            JSONObject jo = new JSONObject();
                            jo.put("qid", h.getId());
                            jo.put("content", h.getQuestion());
                            jsonArray.add(jo);
                        }
                        jsonObject1.put("type", "qa");
                        jsonObject1.put("qList", jsonArray);
                        return Result.success(jsonObject1);
                    }else{
                        return Result.failure(ResultCode.FAILURE);
                    }
                }catch(Exception ex) {
                    return Result.failure(ResultCode.FAILURE, ex.toString());
                }
            }
        }catch (Exception ex){
            System.out.println(ex);
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }

    }

}
