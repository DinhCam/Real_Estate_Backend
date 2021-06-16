package com.gsu21se45.repository;

import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Integer> {

    List<Deal> findDealsByConversation(Conversation conversation);

    Deal findFirstByConversationOrderByCreateAtDesc(Conversation conversation);

    @Modifying
    @Transactional
    @Query(value = "update deal set status = :status where id = :dealId", nativeQuery = true)
    void update(@Param(value = "dealId") int dealId,
                @Param(value = "status") boolean status);
}
