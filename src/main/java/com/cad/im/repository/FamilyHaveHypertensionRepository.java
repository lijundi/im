package com.cad.im.repository;

import com.cad.im.entity.profile.FamilyHaveHypertension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FamilyHaveHypertensionRepository extends JpaRepository<FamilyHaveHypertension, Integer> {
    public List<FamilyHaveHypertension> findByUserId(Integer userId);

    @Transactional
    public void deleteByUserId(Integer userId);
}
