package com.cad.im.service;

import com.cad.im.entity.http.BasicInfo;
import com.cad.im.entity.profile.*;
import com.cad.im.repository.*;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    BasicInformationRepository basicInformationRepository;
    @Autowired
    ChiefComplaintRepository chiefComplaintRepository;
    @Autowired
    FamilyHaveHypertensionRepository familyHaveHypertensionRepository;

    @Autowired
    IncentiveHaveSymptomRepository incentiveHaveSymptomRepository;
    @Autowired
    IncentiveOfHypertensionRepository incentiveOfHypertensionRepository;
    @Autowired
    LifeHabitsRepository lifeHabitsRepository;
    @Autowired
    MetabolicSyndromeCardiovascularDiseaseRepository metabolicSyndromeCardiovascularDiseaseRepository;

    @Autowired
    PhysicalExaminationRepository physicalExaminationRepository;
    @Autowired
    PreviousBloodPressureMeasurementRepository previousBloodPressureMeasurementRepository;
    @Autowired
    SecondaryHypertensionRepository secondaryHypertensionRepository;
    @Autowired
    TargetOrganCardiovascularDiseaseAssessmentRepository targetOrganCardiovascularDiseaseAssessmentRepository;
    @Autowired
    TocdaHaveSymptomRepository tocdaHaveSymptomRepository;
    @Autowired
    UserRepository userRepository;


    public BasicInformation bIList(String userId) {
        List<BasicInformation> bIList = basicInformationRepository.findByUserId(userId);
        if(bIList.size()!=0) {
            return bIList.get(0);
        } else {
            return null;
        }
    }

    public ChiefComplaint cCList(String userId) {
        List<ChiefComplaint> cCList = chiefComplaintRepository.findByUserId(userId);
        if(cCList.size()!=0) {
            return cCList.get(0);
        } else {
            return null;
        }
    }

    public List<FamilyHaveHypertension> fhhList(String userId) {
        List<FamilyHaveHypertension> tmpList = familyHaveHypertensionRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList;
        } else {
            return null;
        }
    }


    public List<IncentiveHaveSymptom> ihsList(String userId) {
        List<IncentiveHaveSymptom> tmpList = incentiveHaveSymptomRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList;
        } else {
            return null;
        }
    }

    public IncentiveOfHypertension iOHList(String userId) {
        List<IncentiveOfHypertension> tmpList = incentiveOfHypertensionRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }

    public LifeHabits lHList(String userId) {
        return lifeHabitsRepository.findById(userId).orElse(null);
    }

    public MetabolicSyndromeCardiovascularDisease mscdList(String userId) {
        List<MetabolicSyndromeCardiovascularDisease> tmpList = metabolicSyndromeCardiovascularDiseaseRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }


    public PhysicalExamination pEList(String userId) {
        List<PhysicalExamination> tmpList = physicalExaminationRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }

    public PreviousBloodPressureMeasurement pBPMList(String userId) {
        List<PreviousBloodPressureMeasurement> tmpList = previousBloodPressureMeasurementRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }

    public SecondaryHypertension sHList(String userId) {
        List<SecondaryHypertension> tmpList = secondaryHypertensionRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }

    public TargetOrganCardiovascularDiseaseAssessment tocdaList(String userId) {
        List<TargetOrganCardiovascularDiseaseAssessment> tmpList = targetOrganCardiovascularDiseaseAssessmentRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }

    public List<TocdaHaveSymptom> thsList(String userId) {
        List<TocdaHaveSymptom> tmpList = tocdaHaveSymptomRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList;
        } else {
            return null;
        }
    }


    public Result bIAdd(BasicInformation basicInfo){
        try{
            BasicInformation bI = bIList(basicInfo.getUserId());
            if(bI!=null){
                bI.setName(basicInfo.getName());
                bI.setAge(basicInfo.getAge());
                bI.setGender(basicInfo.getGender());
                bI.setOccupation(basicInfo.getOccupation());
                basicInformationRepository.save(bI);
            } else {
                basicInformationRepository.save(basicInfo);
            }
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result cCAdd(ChiefComplaint chiefComplaint){
        try{
            ChiefComplaint cC = cCList(chiefComplaint.getUserId());
            if(cC!=null){
                cC.setHypertensionYear(chiefComplaint.getHypertensionYear());
                chiefComplaintRepository.save(cC);
            } else {
                chiefComplaintRepository.save(chiefComplaint);
            }
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result fhhAdd(FamilyHaveHypertension f){
        try{
            familyHaveHypertensionRepository.save(f);
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }


    public Result ihsAdd(IncentiveHaveSymptom i){
        try{
            incentiveHaveSymptomRepository.save(i);
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result iOHAdd(IncentiveOfHypertension incentiveOfHypertension){
        try{
            IncentiveOfHypertension iOH = iOHList(incentiveOfHypertension.getUserId());
            if(iOH!=null){
                iOH.setIncentiveBloodPressure(incentiveOfHypertension.getIncentiveBloodPressure());
                iOH.setIncentive_reason(incentiveOfHypertension.getIncentive_reason());
                iOH.setIncentive_location(incentiveOfHypertension.getIncentive_location());
                incentiveOfHypertensionRepository.save(iOH);
            } else {
                incentiveOfHypertensionRepository.save(incentiveOfHypertension);
            }
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result lHAdd(LifeHabits l){
        try{
            lifeHabitsRepository.save(l);
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result mscdAdd(MetabolicSyndromeCardiovascularDisease m){
        try{
            MetabolicSyndromeCardiovascularDisease mscd = mscdList(m.getUserId());
            if(mscd!=null){
                mscd.setDiabetes(m.getDiabetes());
                mscd.setDyslipidemia(m.getDyslipidemia());
                mscd.setCoronary_heart_disease(m.getCoronary_heart_disease());
                mscd.setApoplexy(m.getApoplexy());
                mscd.setPeripheral_vascular_disease(m.getPeripheral_vascular_disease());
                mscd.setRetinopathy(m.getRetinopathy());
                metabolicSyndromeCardiovascularDiseaseRepository.save(mscd);
            } else {
                metabolicSyndromeCardiovascularDiseaseRepository.save(m);
            }
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result pEAdd(PhysicalExamination p){
        try{
            PhysicalExamination pe = pEList(p.getUserId());
            if(pe!=null){
                pe.setBlood_pressure(p.getBlood_pressure());
                pe.setHeart_rate(p.getHeart_rate());
                pe.setHeight(p.getHeight());
                pe.setWeight(p.getWeight());
                pe.setWaistline(p.getWaistline());
                pe.setEdema_both_lower_limbs(p.getEdema_both_lower_limbs());
                physicalExaminationRepository.save(pe);
            } else {
                physicalExaminationRepository.save(p);
            }
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result pBPMAdd(PreviousBloodPressureMeasurement p){
        try{
            PreviousBloodPressureMeasurement pBPM = pBPMList(p.getUserId());
            if(pBPM!=null){
                pBPM.setPrimary_school_blood_pressure(p.getPrimary_school_blood_pressure());
                pBPM.setMiddle_school_blood_pressure(p.getMiddle_school_blood_pressure());
                pBPM.setWorking_blood_pressure(p.getWorking_blood_pressure());
                pBPM.setPremarital_check_blood_pressure(p.getPremarital_check_blood_pressure());
                pBPM.setPrevious_accident_check_blood_pressure(p.getPrevious_accident_check_blood_pressure());
                pBPM.setOrdinary_blood_pressure(p.getOrdinary_blood_pressure());
                pBPM.setHighest_blood_pressure(p.getHighest_blood_pressure());
                previousBloodPressureMeasurementRepository.save(pBPM);
            } else {
                previousBloodPressureMeasurementRepository.save(p);
            }
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result sHAdd(SecondaryHypertension s){
        try{
            SecondaryHypertension sh = sHList(s.getUserId());
            if(sh!=null){
                sh.setSecondary_hypt_fever_cold(s.getSecondary_hypt_fever_cold());
                sh.setSecondary_hypt_edema_oliguria(s.getSecondary_hypt_edema_oliguria());
                sh.setSecondary_hypt_leg_weak(s.getSecondary_hypt_leg_weak());
                sh.setSecondary_hypt_fear_heat_panic(s.getSecondary_hypt_fear_heat_panic());
                sh.setSecondary_hypt_night_urine_num(s.getSecondary_hypt_night_urine_num());
                sh.setSecondary_hypt_day_urine_num(s.getSecondary_hypt_day_urine_num());
                secondaryHypertensionRepository.save(sh);
            } else {
                secondaryHypertensionRepository.save(s);
            }
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result tocdaAdd(TargetOrganCardiovascularDiseaseAssessment t){
        try{
            TargetOrganCardiovascularDiseaseAssessment tocda = tocdaList(t.getUserId());
            if(tocda!=null){
                tocda.setTocda_climb_floor_num(t.getTocda_climb_floor_num());
                tocda.setTocda_oppressive_wake(t.getTocda_oppressive_wake());
                tocda.setTocda_night_urine_num(t.getTocda_night_urine_num());
                tocda.setTocda_day_urine_num(t.getTocda_day_urine_num());
                targetOrganCardiovascularDiseaseAssessmentRepository.save(tocda);
            } else {
                targetOrganCardiovascularDiseaseAssessmentRepository.save(t);
            }
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result thsAdd(TocdaHaveSymptom t){
        try{
            tocdaHaveSymptomRepository.save(t);
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }


    public Result delByType(Integer id, Integer type){
        try{
            switch (type){
                case 1 :
                    familyHaveHypertensionRepository.deleteById(id);
                    break;
                case 2 :
                    incentiveHaveSymptomRepository.deleteById(id);
                    break;
                case 3 :
                    tocdaHaveSymptomRepository.deleteById(id);
                    break;
            }
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result delAllByType(String userId, Integer type){
        try{
            switch (type){

                case 1 :
                    if(fhhList(userId)!=null){
                        familyHaveHypertensionRepository.deleteByUserId(userId);
                    }
                    break;
                case 2 :
                    if(ihsList(userId)!=null){
                        incentiveHaveSymptomRepository.deleteByUserId(userId);
                    }
                    break;
                case 3 :
                    if(thsList(userId)!=null){
                        tocdaHaveSymptomRepository.deleteByUserId(userId);
                    }
                    break;
            }
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

//    public Result adeDel(Integer id){
//        try{
//            antihypertensiveDrugsEffectRepository.deleteById(id);
//            return Result.success();
//        } catch (Exception ex){
//            return Result.failure(ResultCode.FAILURE, ex.toString());
//        }
//    }
//
//    public Result adeDelAll(Integer userId){
//        try{
//            antihypertensiveDrugsEffectRepository.deleteByUserId(userId);
//            return Result.success();
//        } catch (Exception ex){
//            return Result.failure(ResultCode.FAILURE, ex.toString());
//        }
//    }
//
//    public Result fhhDel(Integer id){
//        try{
//            familyHaveHypertensionRepository.deleteById(id);
//            return Result.success();
//        } catch (Exception ex){
//            return Result.failure(ResultCode.FAILURE, ex.toString());
//        }
//    }
//
//    public Result fhhDelAll(Integer userId){
//        try{
//            familyHaveHypertensionRepository.deleteByUserId(userId);
//            return Result.success();
//        } catch (Exception ex){
//            return Result.failure(ResultCode.FAILURE, ex.toString());
//        }
//    }
}
