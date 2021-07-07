package com.gsu21se45.repository;

import com.gsu21se45.entity.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RealEstateRepository extends JpaRepository<RealEstate, Integer> {

    @Query(value = "select r.staff_id from real_estate r join conversation c " +
            "on c.id = :conversationId and c.real_estate_id = r.id",
            nativeQuery = true)
    String getStaffId(@Param(value = "conversationId") int conversationId);

    @Query(value = "select r.* from real_estate r join conversation c " +
            "on c.id = :conversationId and c.real_estate_id = r.id",
            nativeQuery = true)
    RealEstate getByConversationId(@Param(value = "conversationId") int conversationId);
}
