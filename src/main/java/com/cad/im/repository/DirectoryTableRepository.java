package com.cad.im.repository;

import com.cad.im.entity.mysql.DirectoryTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DirectoryTableRepository extends JpaRepository<DirectoryTable, Integer>{

    @Query(value = "select * from directory_table where pid = ?1", nativeQuery = true)
    public List<DirectoryTable> findAllByPid(int pid);

}
