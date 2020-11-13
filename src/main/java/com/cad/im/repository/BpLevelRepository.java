package com.cad.im.repository;

import com.cad.im.entity.risk.BpLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lijundi
 * @date 2020/11/13 17:32
 */
@Repository
public interface BpLevelRepository extends JpaRepository<BpLevel, String> {
}
