package com.gsu21se45.controller;

import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.AppointmentModel;
import com.gsu21se45.dto.ConversationModel;
import com.gsu21se45.dto.UserModel;
import com.gsu21se45.entity.*;
import com.gsu21se45.mapper.ConversationMapper;
import com.gsu21se45.service.AppointmentService;
import com.gsu21se45.service.ConversationService;
import com.gsu21se45.service.DealService;
import com.gsu21se45.service.MessageService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + RestEntityConstant.URI_CONVERSATION)
public class ConversationController {

    private static final Logger LOGGER = LogManager.getLogger(ConversationController.class);

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ConversationMapper conversationMapper;

    @Autowired
    private DealService dealService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MessageService messageService;

    @GetMapping(value = RestEntityConstant.URI_MESSAGE)
    @ApiOperation(value = "Get all message of specify conversation")
    public @ResponseBody
    ConversationModel getAllMessageByConversation(
            @RequestParam(name = RestEntityConstant.BUYER_ID, required = true) String buyerId
            , @RequestParam(name = RestEntityConstant.SELLER_ID, required = true) String sellerId
            , @RequestParam(name = RestEntityConstant.REAL_ESTATE_ID, required = true) int realEstateId) {
        LOGGER.debug("Begin inside ConversationController.getAllMessageByConversation()");
        ConversationModel model = new ConversationModel();
        Conversation conversation = conversationService.getConversation(buyerId, sellerId, realEstateId);
        if (conversation != null) {
            List<Deal> deals = dealService.findByConversationId(conversation.getId());
            List<Appointment> appointments = appointmentService.findByConversationId(conversation.getId());
            List<Message> messages = messageService.findByConversationId(conversation.getId());
            model = conversationMapper.convertToDTO(conversation, deals, appointments, messages);
        } else {
            conversation = conversationService.save(new Conversation(-1, new User(buyerId), new User(sellerId), new RealEstate(realEstateId)));
            model = conversationMapper.convertToDTO(conversation, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        }
        LOGGER.debug("End inside ConversationController.getAllMessageByConversation()");
        return model;
    }

    @ApiOperation(value = "Get all conversation Ids of recipient")
    @GetMapping(RestEntityConstant.URI_IDS + "/{recipientId}")
    public @ResponseBody
    List<Integer> getAllIdsByRecipientId(@PathVariable String recipientId){
        return conversationService.getIdsByRecipientId(recipientId);
    }

    @ApiOperation(value = "Get buyerId of conversation following appointment status is close sale ")
    @GetMapping(RestEntityConstant.URI_CONVERSATION)
    public @ResponseBody
    ConversationModel getBuyerIdByCloseSaleAppointment(
            @RequestParam(name = RestEntityConstant.REAL_ESTATE_ID, required = true) int realEstateId
            , @RequestParam(name = RestEntityConstant.CLOSE_SALE_STATUS, required = true) String status){
        LOGGER.debug("Begin inside ConversationController.getBuyerIdByCloseSaleAppointment()");
        ConversationModel model = new ConversationModel();

        Conversation conversation = conversationService.getConversationByRealEstateIdAndStatusOfAppointment(realEstateId, status);
        model = conversationMapper.convertToDTO(conversation, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        LOGGER.debug("End inside ConversationController.getBuyerIdByCloseSaleAppointment()");
        return model;
    }

}
