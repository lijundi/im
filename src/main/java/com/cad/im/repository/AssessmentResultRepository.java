package com.cad.im.repository;

import com.cad.im.entity.risk.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lijundi
 * @date 2020/11/18 22:55
 */
@Repository
public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, String> {
}
