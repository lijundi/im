package com.cad.im.repository;

import com.cad.im.entity.mysql.MonitorTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorTaskRepository extends JpaRepository<MonitorTask, String> {
}
