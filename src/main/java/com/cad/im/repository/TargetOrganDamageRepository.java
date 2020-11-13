package com.cad.im.repository;

import com.cad.im.entity.risk.TargetOrganDamage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lijundi
 * @date 2020/11/13 17:33
 */
@Repository
public interface TargetOrganDamageRepository extends JpaRepository<TargetOrganDamage, String> {
}
