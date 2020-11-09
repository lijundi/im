package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.service.QAService;
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
@RequestMapping("/qaai")
public class QAController {
    @Autowired
    QAService qaService;

    //获取QA列表
    @PostMapping("/predict")
    public Result getQAList(@RequestBody JSONObject jsonObject) {
        return qaService.getQAList(jsonObject.toString());
    }

    //获取QA答案
    @PostMapping("/answer")
    public Result getAnswer(@RequestBody JSONObject jsonObject) {
        return qaService.getAnswer(jsonObject.toString());
    }

    //添加QA
    @PostMapping("/addQA")
    public Result addQA(@RequestBody JSONObject jsonObject) {
        return qaService.addQA(jsonObject.toString());
    }

    //删除QA
    @PostMapping("/deleteQA")
    public Result deleteQA(@RequestBody JSONObject jsonObject) {
        return qaService.deleteQA(jsonObject.toString());
    }
}
