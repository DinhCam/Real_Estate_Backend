package com.gsu21se45.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversationModel implements Serializable {

    private int id;
    private int relEstateId;
    private UserModel buyer;
    private UserModel seller;
    private List<MessageModel> messages;
    private List<DealModel> deals;
    private List<AppointmentModel> appointments;

}
