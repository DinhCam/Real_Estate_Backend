package com.gsu21se45.repository;

import com.gsu21se45.dto.MessageStatus;
import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.Message;
import com.gsu21se45.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findMessagesByConversation(Conversation conversation);

    Long countBySenderAndAndConversationAndStatus(User sender, Conversation conversation, String received);
}
