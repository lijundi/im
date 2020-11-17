package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.service.KnowledgeService;
import com.cad.im.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Name: com.cad.im.controrller.QAController
 * @Date: 2020/11/9
 * @Auther: weiwending
 * @Description: QA问答
 */

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {
    @Autowired
    KnowledgeService knowledgeService;

    //获取QA列表
    @PostMapping("/predict")
    public Result getQAList(String question) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userText", question);
        return knowledgeService.getQAList(jsonObject.toString());
    }

    //获取QA答案
    @GetMapping("/answer")
    public Result getAnswer(String qid) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("qapid", qid);
        return knowledgeService.getAnswer(jsonObject.toString());
    }

}
