package com.gsu21se45.controller;

import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.ConversationModel;
import com.gsu21se45.entity.*;
import com.gsu21se45.log.Logger;
import com.gsu21se45.mapper.ConversationMapper;
import com.gsu21se45.service.AppointmentService;
import com.gsu21se45.service.ConversationService;
import com.gsu21se45.service.DealService;
import com.gsu21se45.service.MessageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + RestEntityConstant.URI_CONVERSATION)
public class ConversationController extends Logger {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ConversationController.class);

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
            , @RequestParam(name = RestEntityConstant.STAFF_ID, required = true) String staffId
            , @RequestParam(name = RestEntityConstant.REAL_ESTATE_ID, required = true) int realEstateId) {
        LOGGER.debug("Begin inside ConversationController.getAllMessageByConversation()");
        ConversationModel model = new ConversationModel();
        Conversation conversation = conversationService.getConversation(buyerId, staffId, realEstateId);
        if (conversation != null) {
            List<Deal> deals = dealService.findByConversationId(conversation.getId());
            List<Appointment> appointments = appointmentService.findByConversationId(conversation.getId());
            List<Message> messages = messageService.findByConversationId(conversation.getId());
            model = conversationMapper.convertToDTO(conversation, deals, appointments, messages);
        } else {
            conversation = conversationService.save(new Conversation(-1, new User(buyerId), new User(staffId), new RealEstate(realEstateId)));
            model = conversationMapper.convertToDTO(conversation, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        }
        model.getStaff().setPassword("********************");
        model.getBuyer().setPassword("********************");
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
        Conversation conversation = conversationService.getConversationByRealEstateIdAndStatusOfAppointment(realEstateId, status);
        ConversationModel model = null;
        if(conversation != null){
            model = conversationMapper.convertToDTO(conversation, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            model.getBuyer().setPassword("*********************");
            model.getStaff().setPassword("*********************");
        }
//        UserModel model = user != null ? ((UserModel) objectMapper.convertToDTO(user, UserModel.class)) : null;
//        List<Appointment> appointments = appointmentService.findBySellerIdAndStatus(realEstateId, status);
//        List<AppointmentModel> appointmentModels = appointmentHelper.getRealEstates(appointments);

        LOGGER.debug("End inside ConversationController.getBuyerIdByCloseSaleAppointment()");
        return model;
    }

}
