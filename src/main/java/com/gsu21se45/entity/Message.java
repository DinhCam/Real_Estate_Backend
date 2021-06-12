package com.gsu21se45.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Message {
    private int id;
    private Integer conversationId;
    private Integer senderId;
    private String text;
    private String file;
    private Timestamp time;
    private Conversation conversationByConversationId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "conversation_id", nullable = true)
    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    @Basic
    @Column(name = "sender_id", nullable = true)
    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    @Basic
    @Column(name = "text", nullable = true, length = 255)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "file", nullable = true, length = 255)
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && Objects.equals(conversationId, message.conversationId) && Objects.equals(senderId, message.senderId) && Objects.equals(text, message.text) && Objects.equals(file, message.file) && Objects.equals(time, message.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conversationId, senderId, text, file, time);
    }

    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    public Conversation getConversationByConversationId() {
        return conversationByConversationId;
    }

    public void setConversationByConversationId(Conversation conversationByConversationId) {
        this.conversationByConversationId = conversationByConversationId;
    }
}
