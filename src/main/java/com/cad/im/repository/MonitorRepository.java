package com.cad.im.repository;

import com.cad.im.entity.mysql.BpMonitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorRepository extends JpaRepository<BpMonitor, Long> {
    public List findByUserId(String userId);
}
