package com.cad.im.repository;

import com.cad.im.entity.profile.AntihypertensiveDrugsEffect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AntihypertensiveDrugsEffectRepository extends JpaRepository<AntihypertensiveDrugsEffect, Integer> {

    public List<AntihypertensiveDrugsEffect> findByUserId(Integer userId);

    @Transactional
    public void deleteByUserId(Integer userId);
}
