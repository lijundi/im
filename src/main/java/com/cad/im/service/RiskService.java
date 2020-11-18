package com.cad.im.service;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.profile.*;
import com.cad.im.entity.risk.BpLevel;
import com.cad.im.entity.risk.ClinicalComplications;
import com.cad.im.entity.risk.RiskFactors;
import com.cad.im.entity.risk.TargetOrganDamage;
import com.cad.im.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lijundi
 * @date 2020/11/13 17:27
 */
@Service
public class RiskService {
    private final BpLevelRepository bpLevelRepository;
    private final ClinicalComplicationsRepository clinicalComplicationsRepository;
    private final RiskFactorsRepository riskFactorsRepository;
    private final TargetOrganDamageRepository targetOrganDamageRepository;
    private final PhysicalExaminationRepository physicalExaminationRepository;
    @Autowired
    private BasicInformationRepository basicInformationRepository;
    @Autowired
    private LifeHabitsRepository lifeHabitsRepository;
    @Autowired
    private MetabolicSyndromeCardiovascularDiseaseRepository metabolicSyndromeCardiovascularDiseaseRepository;
    @Autowired
    private FamilyHaveHypertensionRepository familyHaveHypertensionRepository;

    public RiskService(BpLevelRepository bpLevelRepository,
                       ClinicalComplicationsRepository clinicalComplicationsRepository,
                       RiskFactorsRepository riskFactorsRepository,
                       TargetOrganDamageRepository targetOrganDamageRepository,
                       PhysicalExaminationRepository physicalExaminationRepository) {
        this.bpLevelRepository = bpLevelRepository;
        this.clinicalComplicationsRepository = clinicalComplicationsRepository;
        this.riskFactorsRepository = riskFactorsRepository;
        this.targetOrganDamageRepository = targetOrganDamageRepository;
        this.physicalExaminationRepository = physicalExaminationRepository;
    }

    // 更新风险评估表
    public void updateRisk(String userId) {
        // 血压分级表
        BpLevel bpLevel = bpLevelRepository.findById(userId).orElse(new BpLevel(userId));
        // 危险因素表
        RiskFactors riskFactors = riskFactorsRepository.findById(userId).orElse(new RiskFactors(userId));
        // 靶器官损害表
//        TargetOrganDamage targetOrganDamage = targetOrganDamageRepository.findById(userId).orElse(new TargetOrganDamage(userId));
        // 临床并发症表
        ClinicalComplications clinicalComplications = clinicalComplicationsRepository.findById(userId).orElse(new ClinicalComplications(userId));

        List<PhysicalExamination> peList = physicalExaminationRepository.findByUserId(userId);
        if(peList.size()!=0){
            PhysicalExamination pe = peList.get(0);
            // 血压分级表
            String bp = pe.getBlood_pressure();
            if(bp!=null && !bp.equals("")){
                String[] sList = bp.substring(0, bp.length()-4).split("/");
                bpLevel.setDbp(Integer.valueOf(sList[0]));
                bpLevel.setSbp(Integer.valueOf(sList[1]));
            }
            // 危险因素表
            Float height = pe.getHeight();
            Float weight = pe.getWeight();
            Float waistline = pe.getWaistline();
            if(height!=null && weight!=null){
                DecimalFormat df = new DecimalFormat(".00");
                Float bmi = Float.parseFloat(df.format(weight / (height*height)));
                riskFactors.setBmi(bmi);
            }
            if(waistline!=null){
                riskFactors.setWaistline(waistline);
            }
        }

        List<BasicInformation> biList = basicInformationRepository.findByUserId(userId);
        if(biList.size()!=0){
            // 危险因素表
            String gender = biList.get(0).getGender();
            Integer age = biList.get(0).getAge();
            if(gender!=null && !gender.equals("")){
                riskFactors.setGender(gender);
            }
            if(age!=null){
                riskFactors.setAge(age);
            }
        }

        List<LifeHabits> lhList = lifeHabitsRepository.findByUserId(userId);
        if(lhList.size()!=0){
            // 危险因素表
            String smoke = lhList.get(0).getLife_smoke();
            if(smoke.equals("有")){
                riskFactors.setSmoke("吸烟");
            } else {
                riskFactors.setSmoke("不吸烟");
            }
        }

        List<MetabolicSyndromeCardiovascularDisease> mscdList = metabolicSyndromeCardiovascularDiseaseRepository.findByUserId(userId);
        if(mscdList.size()!=0){
            MetabolicSyndromeCardiovascularDisease mscd = mscdList.get(0);
            // 危险因素表
            String dyslipidemia = mscd.getDyslipidemia();
            if(dyslipidemia.equals("有")){
                riskFactors.setDyslipidemia("是");
            } else {
                riskFactors.setDyslipidemia("否");
            }
            // 临床并发症
            String diabetes = mscd.getDiabetes();
            String chd = mscd.getCoronary_heart_disease();
            String apoplexy = mscd.getApoplexy();
            String retinopathy = mscd.getRetinopathy();
            String pvd = mscd.getPeripheral_vascular_disease();
            if(diabetes.equals("有")){
                clinicalComplications.setDiabetes("是");
            }
            if(chd.equals("有")){
                clinicalComplications.setChd("是");
            }
            if(apoplexy.equals("有")){
                clinicalComplications.setCvd("是");
            }
            if(retinopathy.equals("有")){
                clinicalComplications.setRetionpathy("是");
            }
            if(pvd.equals("有")){
                clinicalComplications.setPvd("是");
            }
        }

        List<FamilyHaveHypertension> fhhList = familyHaveHypertensionRepository.findByUserId(userId);
        List<String> familys = Arrays.asList("父亲", "母亲", "哥哥", "弟弟", "姐姐", "妹妹");
        for(FamilyHaveHypertension fhh : fhhList){
            // 危险因素表
            if(familys.contains(fhh.getFamily_who())&&fhh.getFamily_hypertension_age()<50){
                riskFactors.setFamily_history("是");
                break;
            } else {
                riskFactors.setFamily_history("否");
            }
        }

        bpLevelRepository.save(bpLevel);
        riskFactorsRepository.save(riskFactors);
        clinicalComplicationsRepository.save(clinicalComplications);
    }

    // 查表评估
    public JSONObject assessment(String userId) throws Exception {
        // 血压分级表
        BpLevel bpLevel = bpLevelRepository.findById(userId).orElse(new BpLevel(userId));
        if(bpLevel.getDbp()==null || bpLevel.getSbp()==null){
            throw new Exception("缺少血压值");
        }
        // 基本信息表
        List<BasicInformation> biList = basicInformationRepository.findByUserId(userId);
        BasicInformation bi;
        if(biList.size()==0){
            bi = new BasicInformation();
            bi.setUserId(userId);
        } else {
            bi = biList.get(0);
        }

        Integer dbp = bpLevel.getDbp();
        Integer sbp = bpLevel.getSbp();
        // 血压分级判断
        if(dbp<120 && sbp<80){
            bpLevel.setBp_level("正常");
        } else if(dbp>=120&&dbp<=139 || sbp>=80&&sbp<=89) {
            bpLevel.setBp_level("正常高值");
        } else if(dbp>=140 && sbp<90){
            bpLevel.setBp_level("单纯收缩期高血压");
        } else if(dbp>=140&&dbp<=159 || sbp<=99){
            bpLevel.setBp_level("1级高血压");
        } else if(dbp>=160&&dbp<=179 || sbp<=109){
            bpLevel.setBp_level("2级高血压");
        } else {
            bpLevel.setBp_level("3级高血压");
        }


        // 危险分级
        if(dbp<130 || sbp<85){
            bi.setRiskLevel("正常");
        } else if(dbp<=139 || sbp<=89){

        } else if(dbp<=159 || sbp<=99){

        } else if(dbp<=179 || sbp<=109){

        } else {

        }

    }

    // 是否有临床并发症
    private Boolean isClinicalComplications(String userId){
        ClinicalComplications cc = clinicalComplicationsRepository.findById(userId).orElse(new ClinicalComplications(userId));
        return cc.getChd().equals("是")
                || cc.getCkd().equals("是")
                || cc.getCvd().equals("是")
                || cc.getPvd().equals("是")
                || cc.getDiabetes().equals("是")
                || cc.getRetionpathy().equals("是");
    }
    // 是否有靶器官损害
    private Boolean isTargetOrganDamage(String userId){
        TargetOrganDamage tod = targetOrganDamageRepository.findById(userId).orElse(new TargetOrganDamage(userId));
        return tod.getDgfr().equals("是")
                || tod.getIsc().equals("是")
                || tod.getUsca().equals("是")
                || tod.getLeft_ventricular().equals("是")
                || tod.getMicroalbuminuria().equals("是");
    }
    // 危险因素个数
    private Integer numRiskFactors(String userId){
        RiskFactors riskFactors = riskFactorsRepository.findById(userId).orElse(new RiskFactors(userId));

    }
}
