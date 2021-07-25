package com.gsu21se45.controller;

import com.gsu21se45.common.constant.AppConstant;
import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.WorkingAreaModel;
import com.gsu21se45.entity.WorkingArea;
import com.gsu21se45.log.Logger;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.WorkingAreaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + RestEntityConstant.URI_WORKING_AREA)
public class WorkingAreaController extends Logger {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(WorkingAreaController.class);

    @Autowired
    private WorkingAreaService workingAreaService;

    @Autowired
    private ObjectMapper objectMapper;

    @PutMapping(value = RestEntityConstant.URI_UPDATE)
    @ApiOperation(value = "Update/Create a list working areas of staff")
    @ResponseBody
    public ResponseEntity<?> process(@RequestBody List<WorkingAreaModel> models) {
        LOGGER.debug("Begin inside WorkingAreaController.process()");
        try {
            if (models != null) {
                List<WorkingArea> entities = objectMapper.convertToListEntity(models, WorkingArea.class);
                workingAreaService.process(entities);
            }
        } catch (Exception ex) {
            LOGGER.error("Error inside WorkingAreaController.process()", ex);
            return new ResponseEntity(AppConstant.HTTP_BAD_REQUEST_STATUS_CODE, HttpStatus.BAD_REQUEST);
        }
        LOGGER.debug("End inside WorkingAreaController.process()");
        return new ResponseEntity(AppConstant.HTTP_OK_REQUEST_STATUS_CODE, HttpStatus.OK);
    }
}
