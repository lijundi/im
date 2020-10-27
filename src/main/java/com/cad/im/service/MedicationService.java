package com.cad.im.service;

import com.cad.im.entity.mysql.Medication;
import com.cad.im.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Name: com.cad.im.service.MedicationService
 * @Date: 2020/10/26
 * @Auther: weiwending
 * @Description: 用药
 */

@Transactional
@Service
public class MedicationService {
    @Autowired
    MedicationRepository medicationRepository;

    public List<Medication> getHistorys(String userId){
        return medicationRepository.getHistorys(userId);
    }

    public void addMedication(String userId, String medicineName, String startTime, String endTime, String frequency, String medicineDose, String beforeBp, String afterBp){
        Medication medication = new Medication();
        medication.setUser_id(userId);
        medication.setMedicine_name(medicineName);
        medication.setStart_time(startTime);
        medication.setEnd_time(endTime);
        medication.setFrequency(frequency);
        medication.setMedicine_dose(medicineDose);
        medication.setBefore_bp(beforeBp);
        medication.setAfter_bp(afterBp);
        medicationRepository.save(medication);
    }

    public void delMedication(Integer id){
        medicationRepository.delMedication(id);
    }
}
