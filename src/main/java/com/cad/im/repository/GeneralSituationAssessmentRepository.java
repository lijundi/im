package com.cad.im.repository;

import com.cad.im.entity.profile.GeneralSituationAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralSituationAssessmentRepository extends JpaRepository<GeneralSituationAssessment, Integer> {
    public List<GeneralSituationAssessment> findByUserId(Integer userId);
}
