package com.cad.im.repository;

import com.cad.im.entity.profile.IncentiveOfHypertension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncentiveOfHypertensionRepository extends JpaRepository<IncentiveOfHypertension, String> {
    public List<IncentiveOfHypertension> findByUserId(String userId);
}
