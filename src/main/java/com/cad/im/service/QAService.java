package com.cad.im.service;

import com.cad.im.util.HttpUtil;
import com.cad.im.util.Result;
import org.springframework.stereotype.Service;

/**
 * @Name: com.cad.im.service.QAService
 * @Date: 2020/11/9
 * @Auther: weiwending
 * @Description: QA问答
 */
@Service
public class QAService {
    String url = "http://114.67.200.39:7662/qaai/";

    public Result getQAList(String requestJson) {
        String tranurl = url + "predict";
        return Result.success(HttpUtil.postJson(tranurl, requestJson));
    }

    public Result getAnswer(String requestJson) {
        String tranurl = url + "id2Answer";
        return Result.success(HttpUtil.postJson(tranurl, requestJson));
    }

    public Result addQA(String requestJson) {
        String tranurl = url + "adddata";
        return Result.success(HttpUtil.postJson(tranurl, requestJson));
    }

    public Result deleteQA(String requestJson) {
        String tranurl = url + "deldata";
        return Result.success(HttpUtil.postJson(tranurl, requestJson));
    }
}
