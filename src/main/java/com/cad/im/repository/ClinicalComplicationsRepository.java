package com.cad.im.repository;

import com.cad.im.entity.risk.ClinicalComplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lijundi
 * @date 2020/11/13 17:32
 */
@Repository
public interface ClinicalComplicationsRepository extends JpaRepository<ClinicalComplications, String> {
}
