package com.gsu21se45.controller;

import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.*;
import com.gsu21se45.entity.Appointment;
import com.gsu21se45.entity.Deal;
import com.gsu21se45.entity.Message;
import com.gsu21se45.log.Logger;
import com.gsu21se45.mapper.MessageMapper;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.AppointmentService;
import com.gsu21se45.service.DealService;
import com.gsu21se45.service.MessageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ChatController extends Logger {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ChatController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DealService dealService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageModel chatMessage) {
        Message message = messageService.save(messageMapper.convertToEntity(chatMessage));
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/queue/messages",
                new ChatNotificationModel(message.getId(), chatMessage.getConversationId(), message.getSender().getId(), message.getSender().getFullname()));
        if (chatMessage.getAppointment() != null) {
            Appointment appointment = (Appointment) objectMapper.convertToEntity(chatMessage.getAppointment(), Appointment.class);
            appointment.setCreateAt(chatMessage.getTimestamp());
            appointmentService.save(appointment);
        }
        if (chatMessage.getDeal() != null) {
            Deal deal = (Deal) objectMapper.convertToEntity(chatMessage.getDeal(), Deal.class);
            deal.setCreateAt(chatMessage.getTimestamp());
            dealService.save(deal);
        }
    }

    @ApiOperation(value = "Counter new messages")
    @GetMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + "/messages/{senderId}/{conversationId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable int conversationId) {
        return ResponseEntity.ok(messageService.countNewMessages(senderId, conversationId));
    }

    @ApiOperation(value = "Find all messages of conversation")
    @GetMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + "/messages/{conversationId}/all")
    public ResponseEntity<?> findChatMessages(@PathVariable int conversationId) {
        LOGGER.debug("Begin inside ChatController.findChatMessages()");
        List<Message> messages = messageService.findByConversationId(conversationId);
        List<ChatMessageModel> messageModels = new ArrayList<>();
        for (Message message : messages) {
            DealModel dealModel = null;
            AppointmentModel appointmentModel = null;
            if (message.getText().isEmpty()) {
                Deal deal = dealService.findByConversationAndCreateAt(message.getConversation(), message.getCreateAt());
                dealModel = deal != null ? (DealModel) objectMapper.convertToDTO(deal, DealModel.class) : null;
                Appointment appointment = appointmentService.findByConversationAndCreateAt(message.getConversation(), message.getCreateAt());
                appointmentModel = appointment != null ? (AppointmentModel) objectMapper.convertToDTO(appointment, AppointmentModel.class) : null;
            }
            messageModels.add(messageMapper.convertToDTO(message, appointmentModel, dealModel));
        }
        LOGGER.debug("End inside ChatController.findChatMessages()");
        return ResponseEntity.ok(messageModels);
    }

    @ApiOperation(value = "Find message by id")
    @GetMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + "/messages/{id}")
    public ResponseEntity<?> findMessage(@PathVariable int id) {
        LOGGER.debug("Begin inside ChatController.findMessage()");
        Optional<Message> message = messageService.findById(id);
        DealModel dealModel = null;
        AppointmentModel appointmentModel = null;
        if (message.isPresent() && message.get().getText().isEmpty()) {
            Deal deal = dealService.findByConversationAndCreateAt(message.get().getConversation(), message.get().getCreateAt());
            dealModel = deal != null ? (DealModel) objectMapper.convertToDTO(deal, DealModel.class) : null;
            Appointment appointment = appointmentService.findByConversationAndCreateAt(message.get().getConversation(), message.get().getCreateAt());
            appointmentModel = appointment != null ? (AppointmentModel) objectMapper.convertToDTO(appointment, AppointmentModel.class) : null;
        }
        LOGGER.debug("End inside ChatController.findMessage()");
        return ResponseEntity.ok(message.isPresent() ? messageMapper.convertToDTO(message.get(), appointmentModel, dealModel) : null);
    }
}
