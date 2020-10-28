package com.cad.im.repository;

import com.cad.im.entity.profile.LifeHabits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LifeHabitsRepository extends JpaRepository<LifeHabits, Integer> {
    public List<LifeHabits> findByUserId(String userId);
}
