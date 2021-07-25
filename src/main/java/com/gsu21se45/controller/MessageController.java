package com.gsu21se45.controller;

import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.MessageModel;
import com.gsu21se45.entity.Message;
import com.gsu21se45.log.Logger;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.MessageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + RestEntityConstant.URI_MESSAGE)
public class MessageController extends Logger {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MessageController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "Create message")
    @PostMapping(value = RestEntityConstant.URI_CREATE)
    public @ResponseBody
    MessageModel createMessage(@RequestBody MessageModel messageModel) {
        LOGGER.debug("Begin inside MessageController.createMessage()");
        if (messageModel != null) {
            Message message = (Message) objectMapper.convertToEntity(messageModel, Message.class);
            messageService.save(message);
        }
        LOGGER.debug("End inside MessageController.createMessage()");
        return messageModel;
    }

}
