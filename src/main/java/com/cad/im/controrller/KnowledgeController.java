package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.service.KnowledgeService;
import com.cad.im.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result getQAList(@RequestBody JSONObject jsonObject) {
        return knowledgeService.getQAList(jsonObject.toString());
    }

    //获取QA答案
    @GetMapping("/answer")
    public Result getAnswer(@RequestBody JSONObject jsonObject) {
        return knowledgeService.getAnswer(jsonObject.toString());
    }

}
