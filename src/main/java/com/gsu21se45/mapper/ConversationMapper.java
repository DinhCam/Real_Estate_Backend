package com.gsu21se45.mapper;

import com.gsu21se45.dto.*;
import com.gsu21se45.entity.Appointment;
import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.Deal;
import com.gsu21se45.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConversationMapper {

    @Autowired
    ObjectMapper objectMapper;

    public ConversationModel convertToDTO(Conversation conversation, List<Deal> deals, List<Appointment> appointments, List<Message> messages){
        ConversationModel model = new ConversationModel();
        model.setId(conversation.getId());
        model.setBuyer((UserModel) objectMapper.convertToDTO(conversation.getBuyer(), UserModel.class));
        model.setSeller((UserModel) objectMapper.convertToDTO(conversation.getSeller(), UserModel.class));
        model.setRelEstateId(conversation.getRealEstate().getId());
        model.setDeals(deals != null ? objectMapper.convertToListDTO(deals, DealModel.class) : null);
        model.setAppointments(appointments != null ? objectMapper.convertToListDTO(appointments, AppointmentModel.class) : null);
        model.setMessages(messages != null ? objectMapper.convertToListDTO(messages, MessageModel.class) : null);
        return model;
    }

}
