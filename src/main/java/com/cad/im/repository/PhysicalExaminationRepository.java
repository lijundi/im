package com.cad.im.repository;

import com.cad.im.entity.profile.PhysicalExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalExaminationRepository  extends JpaRepository<PhysicalExamination, Integer> {
    public List<PhysicalExamination> findByUserId(Integer userId);
}
