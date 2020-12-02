package com.cad.im.controrller;

import com.cad.im.entity.http.BasicInfo;
import com.cad.im.entity.profile.*;
import com.cad.im.service.ProfileService;
import com.cad.im.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    ProfileService profileService;


    @GetMapping("/basicInformation/list")
    public Result bIList(String userId){
        BasicInformation basicInformation = profileService.bIList(userId);
        return Result.success(basicInformation);
    }

    @GetMapping("/chiefComplaint/list")
    public Result cCList(String userId){
        ChiefComplaint chiefComplaint = profileService.cCList(userId);
        return Result.success(chiefComplaint);
    }

    @GetMapping("/fhh/list")
    public Result fhhList(String userId){
        List<FamilyHaveHypertension> fhhList = profileService.fhhList(userId);
        return Result.success(fhhList);
    }


    @GetMapping("/ihs/list")
    public Result ihsList(String userId){
        List<IncentiveHaveSymptom> ihsList = profileService.ihsList(userId);
        return Result.success(ihsList);
    }

    @GetMapping("/incentiveOfHypertension/list")
    public Result iOHList(String userId){
        IncentiveOfHypertension incentiveOfHypertension = profileService.iOHList(userId);
        return Result.success(incentiveOfHypertension);
    }

    @GetMapping("/lifeHabits/list")
    public Result lHList(String userId){
        LifeHabits lifeHabits = profileService.lHList(userId);
        return Result.success(lifeHabits);
    }

    @GetMapping("/mscd/list")
    public Result mscdList(String userId){
        MetabolicSyndromeCardiovascularDisease mscd = profileService.mscdList(userId);
        return Result.success(mscd);
    }


    @GetMapping("/physicalExamination/list")
    public Result pEList(String userId){
        PhysicalExamination physicalExamination = profileService.pEList(userId);
        return  Result.success(physicalExamination);
    }

    @GetMapping("/previousBloodPressureMeasurement/list")
    public Result pBPMList(String userId){
        PreviousBloodPressureMeasurement previousBloodPressureMeasurement = profileService.pBPMList(userId);
        return Result.success(previousBloodPressureMeasurement);
    }

    @GetMapping("/secondaryHypertension/list")
    public Result sHList(String userId){
        SecondaryHypertension secondaryHypertension = profileService.sHList(userId);
        return Result.success(secondaryHypertension);
    }

    @GetMapping("/tocda/list")
    public Result tocdaList(String userId){
        TargetOrganCardiovascularDiseaseAssessment tocda = profileService.tocdaList(userId);
        return Result.success(tocda);
    }

    @GetMapping("/ths/list")
    public Result thsList(String userId){
        return Result.success(profileService.thsList(userId));
    }



    @PostMapping("/basicInformation/add")
    public Result bIAdd(@RequestBody BasicInformation basicInformation){
        return profileService.bIAdd(basicInformation);
    }

    @PostMapping("/chiefComplaint/add")
    public Result cCAdd(@RequestBody ChiefComplaint chiefComplaint){
        return profileService.cCAdd(chiefComplaint);
    }

    @PostMapping("/fhh/add")
    public Result fhhAdd(@RequestBody FamilyHaveHypertension fhh){
        return profileService.fhhAdd(fhh);
    }

    @PostMapping("/ihs/add")
    public Result ihsAdd(@RequestBody IncentiveHaveSymptom ihs){
        return profileService.ihsAdd(ihs);
    }

    @PostMapping("/incentiveOfHypertension/add")
    public Result iOHAdd(@RequestBody IncentiveOfHypertension incentiveOfHypertension){
        return profileService.iOHAdd(incentiveOfHypertension);
    }

    @PostMapping("/lifeHabits/add")
    public Result lHAdd(@RequestBody LifeHabits lifeHabits){
        return profileService.lHAdd(lifeHabits);
    }

    @PostMapping("/mscd/add")
    public Result mscdAdd(@RequestBody MetabolicSyndromeCardiovascularDisease mscd){
        return profileService.mscdAdd(mscd);
    }

    @PostMapping("/physicalExamination/add")
    public Result pEAdd(@RequestBody PhysicalExamination physicalExamination){
        return profileService.pEAdd(physicalExamination);
    }

    @PostMapping("/previousBloodPressureMeasurement/add")
    public Result pBPMAdd(@RequestBody PreviousBloodPressureMeasurement previousBloodPressureMeasurement){
        return profileService.pBPMAdd(previousBloodPressureMeasurement);
    }

    @PostMapping("/secondaryHypertension/add")
    public Result sHAdd(@RequestBody SecondaryHypertension secondaryHypertension){
        return profileService.sHAdd(secondaryHypertension);
    }

    @PostMapping("/tocda/add")
    public Result tocdaAdd(@RequestBody TargetOrganCardiovascularDiseaseAssessment tocda){
        return profileService.tocdaAdd(tocda);
    }

    @PostMapping("/ths/add")
    public Result thsAdd(@RequestBody TocdaHaveSymptom ths){
        return profileService.thsAdd(ths);
    }




    @GetMapping("/fhh/del")
    public Result fhhDel(Integer id){
        return profileService.delByType(id, 1);
    }

    @GetMapping("/ihs/del")
    public Result ihsDel(Integer id){
        return profileService.delByType(id, 2);
    }

    @GetMapping("/ths/del")
    public Result thsDel(Integer id){
        return profileService.delByType(id, 3);
    }


    @GetMapping("/fhh/delAll")
    public Result fhhDelAll(String userId){
        return profileService.delAllByType(userId, 1);
    }

    @GetMapping("/ihs/delAll")
    public Result ihsDelAll(String userId){
        return profileService.delAllByType(userId, 2);
    }

    @GetMapping("/ths/delAll")
    public Result thsDelAll(String userId){
        return profileService.delAllByType(userId, 3);
    }
}
