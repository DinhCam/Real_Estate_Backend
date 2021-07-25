package com.gsu21se45.controller;

import com.gsu21se45.common.constant.AppConstant;
import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.ScheduleModel;
import com.gsu21se45.entity.Schedule;
import com.gsu21se45.log.Logger;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + RestEntityConstant.URI_SCHEDULE)
public class ScheduleController extends Logger {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ScheduleController.class);

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

    @PostMapping(value = RestEntityConstant.URI_CREATE + RestEntityConstant.URI_ALL)
    @ApiOperation("Create a list schedules")
    public ResponseEntity processAll(@RequestBody List<ScheduleModel> models) {
        LOGGER.debug("Begin inside ScheduleController.processAll()");
        try {
            List<Schedule> entities = models != null ? objectMapper.convertToListEntity(models, Schedule.class) : null;
            if (entities != null) {
                scheduleService.delete(entities.get(0).getSeller());
                scheduleService.save(entities);
            }
        } catch (Exception ex) {
            LOGGER.error("An error inside ScheduleController.processAll()", ex);
            return new ResponseEntity(AppConstant.HTTP_BAD_REQUEST_STATUS_CODE, HttpStatus.BAD_REQUEST);
        }
        LOGGER.debug("End inside ScheduleController.processAll()");
        return new ResponseEntity(AppConstant.HTTP_OK_REQUEST_STATUS_CODE, HttpStatus.OK);
    }
}
