package com.gsu21se45.mapper;

import com.gsu21se45.dto.AppointmentModel;
import com.gsu21se45.dto.ChatMessageModel;
import com.gsu21se45.dto.DealModel;
import com.gsu21se45.dto.MessageModel;
import com.gsu21se45.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public Message convertToEntity(ChatMessageModel chatMessageModel) {
        MessageModel model = setModel(chatMessageModel);
        Message entity = (Message) objectMapper.convertToEntity(model, Message.class);
        return entity;
    }

    public MessageModel setModel(ChatMessageModel chatMessageModel) {
        MessageModel model = new MessageModel();
        model.setConversationId(chatMessageModel.getConversationId());
        model.setCreateAt(chatMessageModel.getTimestamp());
        model.setFile(chatMessageModel.getFile());
        model.setId(-1);
        model.setSenderId(chatMessageModel.getSenderId());
        model.setText(chatMessageModel.getContent());
        return model;
    }

    public ChatMessageModel convertToDTO(Message message, AppointmentModel appointment, DealModel deal) {
        ChatMessageModel model = new ChatMessageModel();
        model.setAppointment(appointment);
        model.setDeal(deal);
        if (message.getConversation().getBuyer().getId().equals(message.getSender().getId())) {
            model.setRecipientId(message.getConversation().getSeller().getId());
            model.setRecipientName(message.getConversation().getSeller().getFullname());
        } else {
            model.setRecipientId(message.getConversation().getBuyer().getId());
            model.setRecipientName(message.getConversation().getBuyer().getFullname());
        }
        model.setId(message.getId());
        model.setFile(message.getFile());
        model.setConversationId(message.getConversation().getId());
        model.setContent(message.getText());
        model.setSenderId(message.getSender().getId());
        model.setSenderName(message.getSender().getFullname());
        model.setStatus(message.getStatus());
        model.setTimestamp(message.getCreateAt());
        return model;
    }


}
