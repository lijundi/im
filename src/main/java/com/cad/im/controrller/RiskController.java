package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.risk.AssessmentCondition;
import com.cad.im.service.RiskService;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lijundi
 * @date 2020/11/13 17:23
 */
@RestController
@RequestMapping("/risk")
public class RiskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final RiskService riskService;

    public RiskController(RiskService riskService) {
        this.riskService = riskService;
    }

    @GetMapping("/assessment")
    public Result assessment(String userId){
        try{
            riskService.updateRisk(userId);
            riskService.assessment(userId);
            JSONObject jo = riskService.result(userId);
            return Result.success(jo);
        } catch (Exception ex){
            LOGGER.error(ex.toString());
            return Result.failure(ResultCode.FAILURE, ex.getMessage());
        }
    }

    @GetMapping("/result")
    public Result amResult(String userId){
        try{
            JSONObject jo = riskService.result(userId);
            return Result.success(jo);
        } catch (Exception ex){
            LOGGER.error(ex.toString());
            return Result.failure(ResultCode.FAILURE, ex.getMessage());
        }
    }

    //补充评估条件
    @PostMapping("/add")
    public Result addAssessmentCondition(@RequestBody AssessmentCondition assessmentCondition){
        return riskService.addAssessmentCondition(assessmentCondition);
    }

    @GetMapping("/list")
    public Result getAssessmentCondition(String userId){
        return riskService.getAssessmentCondition(userId);
    }
}
