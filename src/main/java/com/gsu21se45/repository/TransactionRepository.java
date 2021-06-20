package com.gsu21se45.repository;

import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.RealEstate;
import com.gsu21se45.entity.Transaction;
import com.gsu21se45.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Conversation, Integer> {
    @Query(value = "select c.buyer_id as buyerId\n" +
            "from conversation c\n" +
            "join real_estate r on c.real_estate_id = r.id and c.real_estate_id = :realEstateId", nativeQuery = true)
    List<Conversation> findBuyerIdByRealEstate(@Param(value = "realEstateId") int realEstateId);
}
