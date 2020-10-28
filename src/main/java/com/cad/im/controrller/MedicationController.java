package com.cad.im.controrller;

import com.alibaba.fastjson.JSONObject;
import com.cad.im.entity.mysql.Medication;
import com.cad.im.service.MedicationService;
import com.cad.im.util.Result;
import com.cad.im.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//            List res = new ArrayList();
//            for (Medication medication : medications) {
//                Map map = new HashMap();
//                map.put("id", medication.getId());
//                map.put("createTime", medication.getCreate_time());
//                map.put("medicineName", medication.getMedicine_name());
//                map.put("startTime", medication.getStart_time());
//                map.put("endTime", medication.getEnd_time());
//                map.put("frequency", medication.getFrequency());
//                map.put("medicineDose", medication.getMedicine_dose());
//                map.put("beforeBp", medication.getBefore_bp());
//                map.put("afterBp", medication.getAfter_bp());
//                res.add(map);
//            }
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("data", res);
            return Result.success(medications);
        } catch (Exception ex) {
            return Result.failure(ResultCode.FAILURE, ex.toString());
        }
    }

    //添加用药记录
    @GetMapping("/add")
    public Result addMedication(@RequestBody Medication medication) {
        try {
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
