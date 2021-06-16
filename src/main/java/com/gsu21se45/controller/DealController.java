package com.gsu21se45.controller;

import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.DealModel;
import com.gsu21se45.entity.Deal;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.DealService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_ROOT + RestEntityConstant.URI_DEAL)
public class DealController {

    private static final Logger LOGGER = LogManager.getLogger(DealController.class);

    @Autowired
    private DealService dealService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = RestEntityConstant.URI_ALL)
    @ApiOperation(value = "Get last deal of specify conversation")
    public @ResponseBody
    DealModel getByConversationId(@RequestParam(name = RestEntityConstant.CONVERSATION_ID, required = true) int conversationId) {
        LOGGER.debug("Begin inside DealController.getAllByConversationId()");
        Deal deal = dealService.findLastByConversationId(conversationId);
        LOGGER.debug("End inside DealController.getAllByConversationId()");
        return deal != null ? (DealModel) objectMapper.convertToDTO(deal, DealModel.class) : null;
    }

    @PostMapping(value = RestEntityConstant.URI_CREATE)
    @ApiOperation("Create a deal")
    public @ResponseBody
    DealModel create(@RequestBody DealModel dealModel) {
        LOGGER.debug("Begin inside DealController.create()");
        if (dealModel != null) {
            Deal deal = (Deal) objectMapper.convertToEntity(dealModel, Deal.class);
            deal = dealService.save(deal);
            dealModel.setId(deal.getId());
        }
        LOGGER.debug("End inside DealController.create()");
        return dealModel;
    }

    @PutMapping(value = RestEntityConstant.URI_UPDATE)
    @ApiOperation("Update status")
    public void update(@RequestParam(name = RestEntityConstant.DEAL_ID, required = true) int dealId,
                       @RequestParam(name = RestEntityConstant.STATUS, defaultValue = RestEntityConstant.CANCEL_STATUS + "") boolean status) {
        LOGGER.debug("Begin inside DealController.update()");
        dealService.update(dealId, status);
        LOGGER.debug("End inside DealController.update()");
    }
}
