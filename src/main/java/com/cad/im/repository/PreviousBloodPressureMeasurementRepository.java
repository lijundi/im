package com.cad.im.repository;

import com.cad.im.entity.profile.PreviousBloodPressureMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreviousBloodPressureMeasurementRepository extends JpaRepository<PreviousBloodPressureMeasurement, Integer> {
    public List<PreviousBloodPressureMeasurement> findByUserId(String userId);
}
