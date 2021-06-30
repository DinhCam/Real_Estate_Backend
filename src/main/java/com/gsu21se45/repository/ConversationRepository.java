package com.gsu21se45.repository;

import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.RealEstate;
import com.gsu21se45.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    Conversation getConversationByBuyerAndAndSellerAndRealEstate(User buyer, User seller, RealEstate realEstate);

    @Query(value = "select distinct id from conversation where buyer_id = :recipientId or seller_id = :recipientId",
            nativeQuery = true)
    List<Integer> getIdsByRecipientId(@Param(value = "recipientId") String recipientId);
}
