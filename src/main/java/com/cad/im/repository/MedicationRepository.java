package com.cad.im.repository;

import com.cad.im.entity.mysql.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    public List<Medication> findByUserId(String userId);

    @Override
    public void deleteById(Integer id);
}
