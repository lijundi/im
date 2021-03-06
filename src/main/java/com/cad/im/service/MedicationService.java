package com.cad.im.service;

import com.cad.im.entity.mysql.Medication;
import com.cad.im.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
        return medicationRepository.findByUserId(userId);
    }

    public void addMedication(Medication medication){
        medicationRepository.save(medication);
    }

    public void delMedication(Integer id){
        medicationRepository.deleteById(id);
    }
}
