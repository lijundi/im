package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.mysql.Medication;
import com.cad.im.service.MedicationService;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Name: com.cad.im.controrller.MedicationController
 * @Date: 2020/10/26
 * @Auther: weiwending
 * @Description: 用药
 */

@RestController
@RequestMapping("/medication")
public class MedicationController {
    @Autowired
    MedicationService medicationService;

    //获取用药历史
    @GetMapping("/list")
    public Result getList(String userId) {
        try {
            List<Medication> medications = medicationService.getHistorys(userId);
            System.out.println(medications.size());
            return Result.success(medications);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    //添加用药记录
    @PostMapping("/add")
    public Result addMedication(@RequestBody Medication medication) {
        try {
            System.out.println(medication.getUserId());
            medicationService.addMedication(medication);
            return Result.success(ResultCode.SUCCESS);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    //删除用药记录
    @GetMapping("/del")
    public Result delMedication(Integer id) {
        try {
            medicationService.delMedication(id);
            return Result.success(ResultCode.SUCCESS);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }
}
