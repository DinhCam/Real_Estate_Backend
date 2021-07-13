package com.gsu21se45.repository;

import com.gsu21se45.entity.RealEstateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RealEstateTypeRepository extends JpaRepository<RealEstateType, Integer> {

    @Query(value = "Select r.id from real_estate_type r where r.name = :name",
            nativeQuery = true)
    int getIdByName(@Param(value = "name") String name);
}
