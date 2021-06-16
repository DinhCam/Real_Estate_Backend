package com.gsu21se45.repository;

import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.RealEstate;
import com.gsu21se45.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    Conversation getConversationByBuyerAndAndSellerAndRealEstate(User buyer, User seller, RealEstate realEstate);

}
