package com.cad.im.repository;

import com.cad.im.entity.mysql.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    @Query(value = "select * from medication where user_id = ?1", nativeQuery = true)
    public List<Medication> getHistorys(String userId);

    @Modifying
    @Query(value = "delete from medication where id = ?1", nativeQuery = true)
    public void delMedication(Integer id);
}
