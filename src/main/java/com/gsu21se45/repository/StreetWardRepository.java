package com.gsu21se45.repository;

import com.gsu21se45.entity.StreetWard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StreetWardRepository extends JpaRepository<StreetWard, Integer> {

    @Query(value = "select s.name as streetName, w.name as wardName, d.name as districtName " +
            "from (street_ward sw join street s on sw.street_id = s.id and sw.id = :id) " +
            "join (ward w join district d on w.district_id = d.id) on sw.ward_id = w.id",
            nativeQuery = true)
    String getById(@Param(value = "id") int id);
}
