package com.cad.im.repository;

import com.cad.im.entity.profile.TocdaHaveSymptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TocdaHaveSymptomRepository extends JpaRepository<TocdaHaveSymptom, Integer> {

    public List<TocdaHaveSymptom> findByUserId(String userId);

    @Transactional
    public void deleteByUserId(String userId);
}
