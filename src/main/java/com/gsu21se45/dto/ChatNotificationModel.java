package com.gsu21se45.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotificationModel {
    private int id;
    private int conversationId;
    private String senderId;
    private String senderName;
}