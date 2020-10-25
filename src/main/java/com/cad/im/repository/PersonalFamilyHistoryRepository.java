package com.cad.im.repository;

import com.cad.im.entity.profile.PersonalFamilyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalFamilyHistoryRepository extends JpaRepository<PersonalFamilyHistory, Integer> {
    public List<PersonalFamilyHistory> findByUserId(Integer userId);
}
