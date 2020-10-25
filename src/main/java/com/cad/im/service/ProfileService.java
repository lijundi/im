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
    AntihypertensiveDrugsEffectRepository antihypertensiveDrugsEffectRepository;
    @Autowired
    BasicInformationRepository basicInformationRepository;
    @Autowired
    ChiefComplaintRepository chiefComplaintRepository;
    @Autowired
    FamilyHaveHypertensionRepository familyHaveHypertensionRepository;
    @Autowired
    GeneralSituationAssessmentRepository generalSituationAssessmentRepository;
    @Autowired
    IncentiveHaveSymptomRepository incentiveHaveSymptomRepository;
    @Autowired
    IncentiveOfHypertensionRepository incentiveOfHypertensionRepository;
    @Autowired
    LifeHabitsRepository lifeHabitsRepository;
    @Autowired
    MetabolicSyndromeCardiovascularDiseaseRepository metabolicSyndromeCardiovascularDiseaseRepository;
    @Autowired
    PersonalFamilyHistoryRepository personalFamilyHistoryRepository;
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

    public List<AntihypertensiveDrugsEffect> adeList(Integer userId) {
        List<AntihypertensiveDrugsEffect> aDEList = antihypertensiveDrugsEffectRepository.findByUserId(userId);
        if(aDEList.size()!=0) {
            return aDEList;
        } else {
            return null;
        }
    }

    public BasicInformation bIList(Integer userId) {
        List<BasicInformation> bIList = basicInformationRepository.findByUserId(userId);
        if(bIList.size()!=0) {
            return bIList.get(0);
        } else {
            return null;
        }
    }

    public ChiefComplaint cCList(Integer userId) {
        List<ChiefComplaint> cCList = chiefComplaintRepository.findByUserId(userId);
        if(cCList.size()!=0) {
            return cCList.get(0);
        } else {
            return null;
        }
    }

    public List<FamilyHaveHypertension> fhhList(Integer userId) {
        List<FamilyHaveHypertension> tmpList = familyHaveHypertensionRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList;
        } else {
            return null;
        }
    }

    public GeneralSituationAssessment gsaList(Integer userId) {
        List<GeneralSituationAssessment> gSAList = generalSituationAssessmentRepository.findByUserId(userId);
        if(gSAList.size()!=0) {
            return gSAList.get(0);
        } else {
            return null;
        }
    }

    public List<IncentiveHaveSymptom> ihsList(Integer userId) {
        List<IncentiveHaveSymptom> tmpList = incentiveHaveSymptomRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList;
        } else {
            return null;
        }
    }

    public IncentiveOfHypertension iOHList(Integer userId) {
        List<IncentiveOfHypertension> tmpList = incentiveOfHypertensionRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }

    public LifeHabits lHList(Integer userId) {
        List<LifeHabits> tmpList = lifeHabitsRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }

    public MetabolicSyndromeCardiovascularDisease mscdList(Integer userId) {
        List<MetabolicSyndromeCardiovascularDisease> tmpList = metabolicSyndromeCardiovascularDiseaseRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }

    public PersonalFamilyHistory pFHList(Integer userId) {
        List<PersonalFamilyHistory> tmpList = personalFamilyHistoryRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }

    public PhysicalExamination pEList(Integer userId) {
        List<PhysicalExamination> tmpList = physicalExaminationRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }

    public PreviousBloodPressureMeasurement pBPMList(Integer userId) {
        List<PreviousBloodPressureMeasurement> tmpList = previousBloodPressureMeasurementRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }

    public SecondaryHypertension sHList(Integer userId) {
        List<SecondaryHypertension> tmpList = secondaryHypertensionRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }

    public TargetOrganCardiovascularDiseaseAssessment tocdaList(Integer userId) {
        List<TargetOrganCardiovascularDiseaseAssessment> tmpList = targetOrganCardiovascularDiseaseAssessmentRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList.get(0);
        } else {
            return null;
        }
    }

    public List<TocdaHaveSymptom> thsList(Integer userId) {
        List<TocdaHaveSymptom> tmpList = tocdaHaveSymptomRepository.findByUserId(userId);
        if(tmpList.size()!=0) {
            return tmpList;
        } else {
            return null;
        }
    }


    public Result adeAdd(AntihypertensiveDrugsEffect a){
        try{
            antihypertensiveDrugsEffectRepository.save(a);
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
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

    public Result gsaAdd(GeneralSituationAssessment g){
        try{
            GeneralSituationAssessment gsa = gsaList(g.getUserId());
            if(gsa!=null){
                gsa.setStaple_food(g.getStaple_food());
                gsa.setPickles_bean_paste(g.getPickles_bean_paste());
                gsa.setSleep_hour(g.getSleep_hour());
                gsa.setSleep_quality(g.getSleep_quality());
                generalSituationAssessmentRepository.save(gsa);
            } else {
                generalSituationAssessmentRepository.save(g);
            }
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
            LifeHabits lh = lHList(l.getUserId());
            if(lh!=null){
                lh.setLife_salty_food(l.getLife_salty_food());
                lh.setLife_smoke(l.getLife_smoke());
                lh.setLife_drinking(l.getLife_drinking());
                lh.setLife_nervous(l.getLife_nervous());
                lh.setLife_stay_up_late(l.getLife_stay_up_late());
                lifeHabitsRepository.save(lh);
            } else {
                lifeHabitsRepository.save(l);
            }
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

    public Result pFHAdd(PersonalFamilyHistory p){
        try{
            PersonalFamilyHistory pfh = pFHList(p.getUserId());
            if(pfh!=null){
                pfh.setSmoke_year(p.getSmoke_year());
                pfh.setSmoke_num(p.getSmoke_num());
                pfh.setHigh_drink_year(p.getHigh_drink_year());
                pfh.setHigh_drink_quantity(p.getHigh_drink_quantity());
                pfh.setWeek_sports_num(p.getWeek_sports_num());
                pfh.setSnoring_night(p.getSnoring_night());
                personalFamilyHistoryRepository.save(pfh);
            } else {
                personalFamilyHistoryRepository.save(p);
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
                case 0 :
                    antihypertensiveDrugsEffectRepository.deleteById(id);
                    break;
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

    public Result delAllByType(Integer userId, Integer type){
        try{
            switch (type){
                case 0 :
                    if(adeList(userId)!=null){
                        antihypertensiveDrugsEffectRepository.deleteByUserId(userId);
                    }
                    break;
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
