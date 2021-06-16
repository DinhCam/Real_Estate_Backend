package com.gsu21se45.repository;

import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findMessagesByConversation(Conversation conversation);
}
