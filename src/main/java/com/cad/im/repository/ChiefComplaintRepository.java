package com.cad.im.repository;

import com.cad.im.entity.profile.ChiefComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiefComplaintRepository extends JpaRepository<ChiefComplaint, Integer> {

    public List<ChiefComplaint> findByUserId(String userId);
}
