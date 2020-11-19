package com.cad.im.repository;

import com.cad.im.entity.risk.AssessmentCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lijundi
 * @date 2020/11/18 22:55
 */
@Repository
public interface AssessmentConditionRepository extends JpaRepository<AssessmentCondition, String> {
}
