package com.gsu21se45.repository;

import com.gsu21se45.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    @Query(value = "select * from conversation" +
            " where buyer_id = :buyerId and seller_id = :sellerId and real_estate_id = :realEstateId", nativeQuery = true)
   Conversation getConversation(@Param(value = "buyerId") int buyerId,
                                @Param(value = "sellerId") int sellerId,
                                @Param(value = "realEstateId") int realEstateId);
}
