package com.gsu21se45.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageModel implements Serializable {

    private int id;
    private int conversationId;
    private String senderId;
    private String recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private String file;
    private Date timestamp;
    private String status;
    private AppointmentModel appointment;
    private DealModel deal;
}
