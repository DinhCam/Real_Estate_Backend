package com.gsu21se45.repository;

import com.gsu21se45.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    @Query(value = "select name from district where id = :id",
            nativeQuery = true)
    String getDistrictName(@Param(value = "id") int id);

}
