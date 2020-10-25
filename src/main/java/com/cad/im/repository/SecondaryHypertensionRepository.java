package com.cad.im.repository;

import com.cad.im.entity.profile.SecondaryHypertension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecondaryHypertensionRepository extends JpaRepository<SecondaryHypertension, Integer> {
    public List<SecondaryHypertension> findByUserId(Integer userId);
}
