package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.service.HBPOtherFactorsService;
import com.cad.im.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Name: com.cad.im.controrller.SuppleInfoController
 * @Date: 2020/11/17
 * @Auther: weiwending
 * @Description: 高血压其他影响因素
 */

@RestController
@RequestMapping("/hbpinfo")
public class HBPOtherFactorsController {
    @Autowired
    HBPOtherFactorsService hbpOtherFactorsService;

    //补充高血压其他影响因素
    @PostMapping("/addfactors")
    public Result addInfo(@RequestBody JSONObject jsonObject){
        return hbpOtherFactorsService.addInfo(jsonObject);
    }
    //获取高血压其他影响因素
    @GetMapping("/getlist")
    public  Result getList(String userId){
        return hbpOtherFactorsService.getList(userId);
    }
}
