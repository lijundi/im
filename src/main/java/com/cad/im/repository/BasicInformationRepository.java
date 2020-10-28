package com.cad.im.repository;

import com.cad.im.entity.profile.BasicInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicInformationRepository extends JpaRepository<BasicInformation, Integer> {

    public List<BasicInformation> findByUserId(String userId);
}
