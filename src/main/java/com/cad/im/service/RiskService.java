package com.cad.im.service;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.profile.*;
import com.cad.im.entity.risk.*;
import com.cad.im.repository.*;
import com.cad.im.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author lijundi
 * @date 2020/11/13 17:27
 */
@Service
public class RiskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RiskService.class);
    private final AssessmentConditionRepository assessmentConditionRepository;
    private final AssessmentResultRepository assessmentResultRepository;

    private final PhysicalExaminationRepository physicalExaminationRepository;
    private final BasicInformationRepository basicInformationRepository;
    private final LifeHabitsRepository lifeHabitsRepository;
    private final MetabolicSyndromeCardiovascularDiseaseRepository metabolicSyndromeCardiovascularDiseaseRepository;

    public RiskService(PhysicalExaminationRepository physicalExaminationRepository, AssessmentConditionRepository assessmentConditionRepository, AssessmentResultRepository assessmentResultRepository, BasicInformationRepository basicInformationRepository, LifeHabitsRepository lifeHabitsRepository, MetabolicSyndromeCardiovascularDiseaseRepository metabolicSyndromeCardiovascularDiseaseRepository) {
        this.physicalExaminationRepository = physicalExaminationRepository;
        this.assessmentConditionRepository = assessmentConditionRepository;
        this.assessmentResultRepository = assessmentResultRepository;
        this.basicInformationRepository = basicInformationRepository;
        this.lifeHabitsRepository = lifeHabitsRepository;
        this.metabolicSyndromeCardiovascularDiseaseRepository = metabolicSyndromeCardiovascularDiseaseRepository;
    }

    // 更新风险评估表
    public void updateRisk(String userId) {
        AssessmentCondition ac = assessmentConditionRepository.findById(userId).orElse(new AssessmentCondition(userId));

        List<PhysicalExamination> peList = physicalExaminationRepository.findByUserId(userId);
        if(peList.size()!=0){
            PhysicalExamination pe = peList.get(0);
            String bp = pe.getBlood_pressure();
            Float height = pe.getHeight();
            Float weight = pe.getWeight();
            Float waistline = pe.getWaistline();
            if(bp!=null){
                String[] sList = bp.substring(0, bp.length()-4).split("/");
                ac.setDbp(Integer.valueOf(sList[0]));
                ac.setSbp(Integer.valueOf(sList[1]));
            }
            if(height!=null && weight!=null){
                DecimalFormat df = new DecimalFormat("##.00");
                Float bmi = Float.parseFloat(df.format((weight*10000) / (height*height)));
                ac.setBmi(bmi);
            }
            if(waistline!=null){
                ac.setWaistline(waistline);
            }
        }

        List<BasicInformation> biList = basicInformationRepository.findByUserId(userId);
        if(biList.size()!=0){
            String gender = biList.get(0).getGender();
            Integer age = biList.get(0).getAge();
            if(gender!=null){
                ac.setGender(gender);
            } else {
                ac.setGender("男");
            }
            if(age!=null){
                ac.setAge(age);
            }
        }

        List<LifeHabits> lhList = lifeHabitsRepository.findByUserId(userId);
        if(lhList.size()!=0){
            // 危险因素表
            String smoke = lhList.get(0).getLife_smoke();
            if(smoke.equals("有")){
                ac.setSmoke("吸烟");
            } else {
                ac.setSmoke("不吸烟");
            }
        }

        List<MetabolicSyndromeCardiovascularDisease> mscdList = metabolicSyndromeCardiovascularDiseaseRepository.findByUserId(userId);
        if(mscdList.size()!=0){
            MetabolicSyndromeCardiovascularDisease mscd = mscdList.get(0);
            String dyslipidemia = mscd.getDyslipidemia();
            String diabetes = mscd.getDiabetes();
            String chd = mscd.getCoronary_heart_disease();
            String apoplexy = mscd.getApoplexy();
            String retinopathy = mscd.getRetinopathy();
            String pvd = mscd.getPeripheral_vascular_disease();
            if(dyslipidemia.equals("有")){
                ac.setDyslipidemia("是");
            } else {
                ac.setDyslipidemia("否");
            }
            if(diabetes.equals("有")){
                ac.setDiabetes("是");
            } else {
                ac.setDiabetes("否");
            }
            if(chd.equals("有")){
                ac.setChd("是");
            } else {
                ac.setChd("否");
            }
            if(apoplexy.equals("有")){
                ac.setCvd("是");
            } else {
                ac.setCvd("否");
            }
            if(retinopathy.equals("有")){
                ac.setRetionpathy("是");
            } else {
                ac.setRetionpathy("否");
            }
            if(pvd.equals("有")){
                ac.setPvd("是");
            } else {
                ac.setPvd("否");
            }
        }

        assessmentConditionRepository.save(ac);
    }

    // 查表评估
    public void assessment(String userId) throws Exception {
        AssessmentCondition ac = assessmentConditionRepository.findById(userId).orElse(new AssessmentCondition(userId));
        AssessmentResult ar = assessmentResultRepository.findById(userId).orElse(new AssessmentResult(userId));
        if(ac.getDbp()==null || ac.getSbp()==null){
            throw new Exception("缺少血压值");
        }
        Integer dbp = ac.getDbp();
        Integer sbp = ac.getSbp();
        // 血压分级判断
        if(dbp<120 && sbp<80){
            ar.setBpLevel("正常");
        } else if(dbp>=140 && sbp<90){
            ar.setBpLevel("单纯收缩期高血压");
        } else if(dbp>=120&&dbp<=139 || sbp<=89) {
            ar.setBpLevel("正常高值");
        } else if(dbp>=140&&dbp<=159 || sbp<=99){
            ar.setBpLevel("1级高血压");
        } else if(dbp>=160&&dbp<=179 || sbp<=109){
            ar.setBpLevel("2级高血压");
        } else {
            ar.setBpLevel("3级高血压");
        }
        boolean cc = isClinicalComplications(ac, ar);
        boolean tod = isTargetOrganDamage(ac, ar);
        int rfNum = numRiskFactors(ac, ar);
        // 危险分级
        if(dbp<130 || sbp<85){
            ar.setRiskLevel("正常");
        } else if(dbp<=139 || sbp<=89){
            if(cc){
                ar.setRiskLevel("高危");
            } else if(tod || rfNum>=3) {
                ar.setRiskLevel("中危");
            } else if(rfNum>=1) {
                ar.setRiskLevel("低危");
            } else {
                ar.setRiskLevel("正常");
            }
        } else if(dbp<=159 || sbp<=99){
            if(cc){
                ar.setRiskLevel("很高危");
            } else if(tod || rfNum>=3) {
                ar.setRiskLevel("高危");
            } else if(rfNum>=1) {
                ar.setRiskLevel("中危");
            } else {
                ar.setRiskLevel("低危");
            }
        } else if(dbp<=179 || sbp<=109){
            if(cc){
                ar.setRiskLevel("很高危");
            } else if(tod || rfNum>=3) {
                ar.setRiskLevel("高危");
            } else{
                ar.setRiskLevel("中危");
            }
        } else {
            if(cc){
                ar.setRiskLevel("很高危");
            } else if(tod || rfNum!=0) {
                ar.setRiskLevel("很高危");
            } else {
                ar.setRiskLevel("高危");
            }
        }
        assessmentResultRepository.save(ar);
    }

    // 封装评估结果
    public JSONObject result(String userId) throws Exception {
        AssessmentResult ar = assessmentResultRepository.findById(userId).orElse(null);
        if(ar==null){
            throw new Exception("无评估结果");
        } else {
            JSONObject jo = new JSONObject();
            jo.put("riskLevel", ar.getRiskLevel());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jo.put("updateTime", sdf.format(ar.getUpdateTime()));
            jo.put("bpLevel", ar.getBpLevel());
            // 危险因素
            JSONObject factors = new JSONObject();
            factors.put("num", ar.getRfNum());
            ArrayList<String> contents = new ArrayList<>();
            contents.add(ar.getGender_age());
            contents.add(ar.getSmoke());
            contents.add(ar.getIgt());
            contents.add(ar.getDyslipidemia());
            contents.add(ar.getCvd_family_history());
            contents.add(ar.getAbdominal_obesity());
            contents.add(ar.getHhe());
            removeFou(contents);
            factors.put("contents", contents);
            jo.put("factors", factors);
            // 靶器官损害
            ArrayList<String> organs = new ArrayList<>();
            organs.add(ar.getLeft_ventricular());
            organs.add(ar.getUsca());
            organs.add(ar.getDgfr());
            organs.add(ar.getIsc());
            organs.add(ar.getMicroalbuminuria());
            removeFou(organs);
            jo.put("organs", organs);
            // 临床并发症
            ArrayList<String> complications = new ArrayList<>();
            complications.add(ar.getCvd());
            complications.add(ar.getChd());
            complications.add(ar.getCkd());
            complications.add(ar.getPvd());
            complications.add(ar.getRetinopathy());
            complications.add(ar.getDiabetes());
            removeFou(complications);
            jo.put("complications", complications);
            return jo;
        }
    }

    // 是否有临床并发症
    private boolean isClinicalComplications(AssessmentCondition ac, AssessmentResult ar){
        boolean flag = false;
        if(ac.getCvd().equals("是")){
            ar.setCvd("脑血管疾病");
            flag = true;
        } else {
            ar.setCvd("否");
        }
        if(ac.getChd().equals("是")){
            ar.setChd("心脏疾病");
            flag = true;
        } else {
            ar.setChd("否");
        }
        if(ac.getCkd().equals("是")){
            ar.setCkd("肾脏疾病");
            flag = true;
        } else {
            ar.setCkd("否");
        }
        if(ac.getPvd().equals("是")){
            ar.setPvd("外周血管疾病");
            flag = true;
        } else {
            ar.setPvd("否");
        }
        if(ac.getRetionpathy().equals("是")){
            ar.setRetinopathy("视网膜病变");
            flag = true;
        } else {
            ar.setRetinopathy("否");
        }
        if(ac.getDiabetes().equals("是")){
            ar.setDiabetes("糖尿病");
            flag = true;
        } else {
            ar.setDiabetes("否");
        }
        if(flag){
            ar.setClinicalComplications("是");
        } else {
            ar.setClinicalComplications("否");
        }
        assessmentResultRepository.save(ar);
        return flag;
    }

    // 是否有靶器官损害
    private boolean isTargetOrganDamage(AssessmentCondition ac, AssessmentResult ar){
        boolean flag = false;
        String gender = ac.getGender();
        if(ac.getSl_voltage()!=null && ac.getSl_voltage()>3.8){
            ar.setLeft_ventricular("左心室肥厚");
            flag = true;
        } else {
            ar.setLeft_ventricular("否");
        }
        if(ac.getLvmi()!=null && (ac.getLvmi()>=115&&gender.equals("男") || ac.getLvmi()>=95&&gender.equals("女"))){
            ar.setLeft_ventricular("左心室肥厚");
            flag = true;
        } else {
            ar.setLeft_ventricular("否");
        }
        if(ac.getImt()!=null && ac.getImt()>=0.9){
            ar.setUsca("超声显示颈动脉粥样硬化");
            flag = true;
        } else {
            ar.setUsca("否");
        }
        if(ac.getEgfr()!=null && ac.getEgfr()>=30&&ac.getEgfr()<=59){
            ar.setDgfr("肾小球滤过率降低");
            flag = true;
        } else {
            ar.setDgfr("否");
        }
        Float sc = ac.getSerum_creatinine();
        if(sc!=null && (sc>=115&&sc<=133&&gender.equals("男") || sc>=107&&sc<=124&&gender.equals("女"))){
            ar.setIsc("血清肌酐升高");
            flag = true;
        } else {
            ar.setIsc("否");
        }
        if(ac.getProteinuria()!=null && ac.getProteinuria()>=30&&ac.getProteinuria()<=300){
            ar.setMicroalbuminuria("微量白蛋白尿");
            flag = true;
        } else {
            ar.setMicroalbuminuria("否");
        }
        if(flag){
            ar.setTargetOrganDamage("是");
        } else {
            ar.setTargetOrganDamage("否");
        }
        assessmentResultRepository.save(ar);
        return flag;
    }

    // 危险因素个数
    private int numRiskFactors(AssessmentCondition ac, AssessmentResult ar){
        int rfNum = 0;
        if(ac.getAge()!=null && ac.getAge()>55&&ac.getGender().equals("男")){
            ar.setGender_age("男性>55岁");
            rfNum+=1;
        } else if(ac.getAge()!=null && ac.getAge()>65&&ac.getGender().equals("女")){
            ar.setGender_age("女性>65岁");
            rfNum+=1;
        } else {
            ar.setGender_age("否");
        }
        if(ac.getSmoke().equals("吸烟") || ac.getSmoke().equals("被动吸烟")){
            ar.setSmoke(ac.getSmoke());
            rfNum+=1;
        } else {
            ar.setSmoke("否");
        }
        if(ac.getH2Bg()!=null && ac.getH2Bg()>=7.8&&ac.getH2Bg()<=11.0){
            ar.setIgt("糖耐量受损");
            rfNum+=1;
        } else {
            ar.setIgt("否");
        }
        if(ac.getDyslipidemia().equals("是")){
            ar.setDyslipidemia("血脂异常");
            rfNum+=1;
        } else {
            if(ac.getTc()!=null && ac.getTc()>=5.2){
                ar.setDyslipidemia("血脂异常");
                rfNum+=1;
            } else if(ac.getLdl_c()!=null && ac.getLdl_c()>=3.4){
                ar.setDyslipidemia("血脂异常");
                rfNum+=1;
            } else if(ac.getHdl_c()!=null && ac.getHdl_c()<1.0){
                ar.setDyslipidemia("血脂异常");
                rfNum+=1;
            } else {
                ar.setDyslipidemia("否");
            }
        }
        if(ac.getCvd_family_history().equals("是")){
            ar.setCvd_family_history("早发心血管病家族史");
            rfNum+=1;
        } else {
            ar.setCvd_family_history("否");
        }
        if(ac.getWaistline()!=null && (ac.getWaistline()>=90&&ac.getGender().equals("男") || ac.getWaistline()>=85&&ac.getGender().equals("女"))){
            ar.setAbdominal_obesity("腹型肥胖");
            rfNum+=1;
        } else {
            ar.setAbdominal_obesity("否");
        }
        if(ac.getBmi()!=null && ac.getBmi()>=28){
            ar.setAbdominal_obesity("腹型肥胖");
            rfNum+=1;
        } else {
            ar.setAbdominal_obesity("否");
        }
        if(ac.getCysteine()!=null && ac.getCysteine()>=15){
            ar.setHhe("高同型半胱氨酸血症");
            rfNum+=1;
        } else {
            ar.setHhe("否");
        }
        ar.setRfNum(rfNum);
        assessmentResultRepository.save(ar);
        return rfNum;
    }

    // 去除数组中的否
    private void removeFou(ArrayList<String> arrayList){
        arrayList.removeIf(temp -> temp.equals("否"));
    }

    public Result  addAssessmentCondition(AssessmentCondition assessmentCondition){
        assessmentConditionRepository.save(assessmentCondition);
        return Result.success();
    }

    public Result getAssessmentCondition(String userId){
        return Result.success(assessmentConditionRepository.findById(userId));
    }
}
