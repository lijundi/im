package com.cad.im.service;

import com.cad.im.entity.mysql.BpMonitor;
import com.cad.im.repository.MonitorRepository;
import com.cad.im.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Name: com.cad.im.service.MonitorService
 * @Date: 2020/12/30
 * @Auther: weiwending
 * @Description: 高血压监测Service层
 */

@Service
public class MonitorService {
    @Autowired
    MonitorRepository monitorRepository;

    public Result List(String userId){
        return Result.success(monitorRepository.findByUserId(userId));
    }

    public Result Add(BpMonitor bpMonitor){
        monitorRepository.save(bpMonitor);
        return Result.success();
    }
}
