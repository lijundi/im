package com.cad.im.repository;

import com.cad.im.entity.profile.IncentiveHaveSymptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IncentiveHaveSymptomRepository extends JpaRepository<IncentiveHaveSymptom, Integer> {
    public List<IncentiveHaveSymptom> findByUserId(Integer userId);

    @Transactional
    public void deleteByUserId(Integer userId);
}
