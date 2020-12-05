package com.cad.im.repository;

import com.cad.im.entity.profile.MetabolicSyndromeCardiovascularDisease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetabolicSyndromeCardiovascularDiseaseRepository extends JpaRepository<MetabolicSyndromeCardiovascularDisease, String> {
    public List<MetabolicSyndromeCardiovascularDisease> findByUserId(String userId);
}
