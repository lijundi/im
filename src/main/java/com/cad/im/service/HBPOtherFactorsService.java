package com.cad.im.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.risk.BpLevel;
import com.cad.im.entity.risk.ClinicalComplications;
import com.cad.im.entity.risk.RiskFactors;
import com.cad.im.entity.risk.TargetOrganDamage;
import com.cad.im.repository.BpLevelRepository;
import com.cad.im.repository.ClinicalComplicationsRepository;
import com.cad.im.repository.RiskFactorsRepository;
import com.cad.im.repository.TargetOrganDamageRepository;
import com.cad.im.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Name: com.cad.im.service.HBPFactorsService
 * @Date: 2020/11/17
 * @Auther: weiwending
 * @Description: 高血压其他因素Service
 */
@Service
public class HBPOtherFactorsService {
    @Autowired
    BpLevelRepository bpLevelRepository;
    @Autowired
    ClinicalComplicationsRepository clinicalComplicationsRepository;
    @Autowired
    RiskFactorsRepository riskFactorsRepository;
    @Autowired
    TargetOrganDamageRepository targetOrganDamageRepository;

    public Result addInfo(JSONObject jsonObject) {
        BpLevel bpLevel = JSON.parseObject(jsonObject.getJSONObject("bpLevel").toJSONString(), BpLevel.class);
        ClinicalComplications clinicalComplications = JSON.parseObject(jsonObject.getJSONObject("clinicalComplications").toJSONString(), ClinicalComplications.class);
        RiskFactors riskFactors = JSON.parseObject(jsonObject.getJSONObject("riskFactors").toJSONString(), RiskFactors.class);
        TargetOrganDamage targetOrganDamage = JSON.parseObject(jsonObject.getJSONObject("targetOrganDamage").toJSONString(), TargetOrganDamage.class);
        bpLevelRepository.save(bpLevel);
        clinicalComplicationsRepository.save(clinicalComplications);
        riskFactorsRepository.save(riskFactors);
        targetOrganDamageRepository.save(targetOrganDamage);
        return Result.success();
    }

    public Result getList(String userId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bpLevel", bpLevelRepository.findById(userId));
        jsonObject.put("clinicalComplications", bpLevelRepository.findById(userId));
        jsonObject.put("riskFactors", riskFactorsRepository.findById(userId));
        jsonObject.put("targetOrganDamage", targetOrganDamageRepository.findById(userId));
        return Result.success(jsonObject);
    }

}
