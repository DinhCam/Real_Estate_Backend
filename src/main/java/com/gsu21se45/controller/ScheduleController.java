package com.gsu21se45.controller;

import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.ScheduleModel;
import com.gsu21se45.entity.Schedule;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + RestEntityConstant.URI_SCHEDULE)
public class ScheduleController {

    private static final Logger LOGGER = LogManager.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = RestEntityConstant.URI_ALL)
    @ApiOperation("Get all schedules by seller")
    public @ResponseBody
    List<ScheduleModel> getAllBySellerId(@RequestParam(name = RestEntityConstant.SELLER_ID, required = true) String sellerId) {
        LOGGER.debug("Begin inside ScheduleController.getAllBySellerId()");
        List<Schedule> schedules = scheduleService.findBySellerId(sellerId);
        LOGGER.debug("End inside ScheduleController.getAllBySellerId()");
        return schedules != null ? objectMapper.convertToListDTO(schedules, ScheduleModel.class) : null;
    }
}
