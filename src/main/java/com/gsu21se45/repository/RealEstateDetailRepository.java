package com.gsu21se45.repository;

import com.gsu21se45.entity.RealEstateDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RealEstateDetailRepository extends JpaRepository<RealEstateDetail, Integer> {

    @Query(value = "select street_ward_id from real_estate_detail where id = :id",
            nativeQuery = true)
    int getStreetWardIdById(@Param(value = "id") int id);
}
