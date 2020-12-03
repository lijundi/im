package com.cad.im.repository;

import com.cad.im.entity.profile.TargetOrganCardiovascularDiseaseAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetOrganCardiovascularDiseaseAssessmentRepository extends JpaRepository<TargetOrganCardiovascularDiseaseAssessment, String> {
    public List<TargetOrganCardiovascularDiseaseAssessment> findByUserId(String userId);
}
