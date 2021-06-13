package com.gsu21se45.controller;

import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.entity.Conversation;
import com.gsu21se45.service.ConversationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestEntityConstant.URI_CONVERSATION)
public class ConversationController {

    @Autowired
    ConversationService conversationService;

    @GetMapping(value = RestEntityConstant.URI_GET_ALL_MESSAGE)
    @ApiOperation(value = "Get all message of specify conversation")
    public @ResponseBody Conversation getAllMessageByConversation(
            @RequestParam(name = RestEntityConstant.BUYER_ID, required = true) int buyerId
            , @RequestParam(name = RestEntityConstant.SELLER_ID, required = true) int sellerId
            , @RequestParam (name = RestEntityConstant.REAL_ESTATE_ID, required = true) int  realEstateId){
            Conversation conversation = conversationService.getConversation(buyerId, sellerId, realEstateId);
            return conversation;
    }

}
