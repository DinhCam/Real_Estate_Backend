package com.gsu21se45.repository;

import com.gsu21se45.entity.Appointment;
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

    @Query(value = "select c.id, c.buyer_id, c.seller_id, c.real_estate_id " +
            "from conversation c join appointment a " +
            "on c.id = a.conversation_id " +
            "and c.real_estate_id = :realEstateId " +
            "and a.status = :status ", nativeQuery = true)
    Conversation findByRealEstateIdAndStatus(@Param(value = "realEstateId") int realEstateId,
                                              @Param(value = "status") String status);
}
