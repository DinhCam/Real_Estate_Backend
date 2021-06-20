package com.gsu21se45.controller;

import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.ConversationModel;
import com.gsu21se45.dto.ScheduleModel;
import com.gsu21se45.dto.TransactionModel;
import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.Schedule;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "getBuyerId")
    public @ResponseBody
    List<TransactionModel> getBuyerId(@RequestParam(name = RestEntityConstant.REAL_ESTATE_ID, required = true) int realEstateId) {
        List<Conversation> conversations = transactionService.findBuyerIdByRealEstate(realEstateId);
        return objectMapper.convertToListDTO(conversations, TransactionModel.class);
    }
}
