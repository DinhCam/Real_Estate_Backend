package com.gsu21se45.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageModel implements Serializable {

    private int id;
    private int conversationId;
    private String senderId;
    private String text;
    private String file;
    private Date createAt;
    private String status;
}
