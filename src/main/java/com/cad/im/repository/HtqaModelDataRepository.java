package com.cad.im.repository;

import com.cad.im.entity.mysql.HtqaModelData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HtqaModelDataRepository extends JpaRepository<HtqaModelData, String> {

    @Query(value = "select * from htqa_model_data where directory_id = ?1", nativeQuery = true)
    public List<HtqaModelData> findAllByDirectory_id(int directory_id);
}
