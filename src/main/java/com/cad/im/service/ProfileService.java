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
        return basicInformationRepository.findById(userId).orElse(null);
    }

    public ChiefComplaint cCList(String userId) {
        return chiefComplaintRepository.findById(userId).orElse(null);

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
        return incentiveOfHypertensionRepository.findById(userId).orElse(null);
    }

    public LifeHabits lHList(String userId) {
        return lifeHabitsRepository.findById(userId).orElse(null);
    }

    public MetabolicSyndromeCardiovascularDisease mscdList(String userId) {
        return metabolicSyndromeCardiovascularDiseaseRepository.findById(userId).orElse(null);
    }


    public PhysicalExamination pEList(String userId) {
        return physicalExaminationRepository.findById(userId).orElse(null);
    }

    public PreviousBloodPressureMeasurement pBPMList(String userId) {
        return previousBloodPressureMeasurementRepository.findById(userId).orElse(null);
    }

    public SecondaryHypertension sHList(String userId) {
        return secondaryHypertensionRepository.findById(userId).orElse(null);
    }

    public TargetOrganCardiovascularDiseaseAssessment tocdaList(String userId) {
        return targetOrganCardiovascularDiseaseAssessmentRepository.findById(userId).orElse(null);
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
            basicInformationRepository.save(basicInfo);
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result cCAdd(ChiefComplaint chiefComplaint){
        try{
            chiefComplaintRepository.save(chiefComplaint);
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
            incentiveOfHypertensionRepository.save(incentiveOfHypertension);
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
            metabolicSyndromeCardiovascularDiseaseRepository.save(m);
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result pEAdd(PhysicalExamination p){
        try{
            physicalExaminationRepository.save(p);
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result pBPMAdd(PreviousBloodPressureMeasurement p){
        try{
            previousBloodPressureMeasurementRepository.save(p);
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result sHAdd(SecondaryHypertension s){
        try{
            secondaryHypertensionRepository.save(s);
            return Result.success();
        } catch (Exception ex){
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    public Result tocdaAdd(TargetOrganCardiovascularDiseaseAssessment t){
        try{
            targetOrganCardiovascularDiseaseAssessmentRepository.save(t);
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
