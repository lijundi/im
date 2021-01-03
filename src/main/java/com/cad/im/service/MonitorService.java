package com.cad.im.service;

import com.cad.im.entity.mysql.BpMonitor;
import com.cad.im.entity.profile.PhysicalExamination;
import com.cad.im.repository.MonitorRepository;
import com.cad.im.repository.PhysicalExaminationRepository;
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
    @Autowired
    PhysicalExaminationRepository physicalExaminationRepository;

    public Result List(String userId){
        return Result.success(monitorRepository.findByUserId(userId));
    }

    public Result Add(BpMonitor bpMonitor){
        // 更新体格检查资料
        PhysicalExamination pe = physicalExaminationRepository.findById(bpMonitor.getUserId()).orElse(null);
        assert pe != null;
        pe.setBlood_pressure(bpMonitor.getSbp() + "/" + bpMonitor.getDbp() + "mmHg");
        if(bpMonitor.getHeart_rate()!=null)
            pe.setHeart_rate(bpMonitor.getHeart_rate());
        physicalExaminationRepository.save(pe);
        monitorRepository.save(bpMonitor);
        return Result.success();
    }
}
