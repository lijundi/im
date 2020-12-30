package com.cad.im.controrller;

import com.cad.im.entity.mysql.BpMonitor;
import com.cad.im.service.MonitorService;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Name: com.cad.im.controrller.HBPmonitor
 * @Date: 2020/12/30
 * @Auther: weiwending
 * @Description: 高血压监测
 */

@RestController
@RequestMapping("/monitor")
public class MonitorController {
    @Autowired
    MonitorService monitorService;

    //获取高血压监测数据
    @GetMapping("/list")
    public Result List(String userId){
        try{
            return monitorService.List(userId);
        }catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    //添加高血压监测数据
    @PostMapping("/add")
    public Result Add(@RequestBody BpMonitor bpMonitor){
        try{
            return monitorService.Add(bpMonitor);
        }catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }
}
