package com.gsu21se45.controller;

import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.DistrictModel;
import com.gsu21se45.entity.District;
import com.gsu21se45.log.Logger;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.DistrictService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + RestEntityConstant.URI_DISTRICT)
public class DistrictController extends Logger {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DistrictController.class);

    @Autowired
    private DistrictService districtService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = RestEntityConstant.URI_GET + RestEntityConstant.URI_ALL)
    @ApiOperation(value = "Get all districts")
    public @ResponseBody
    List<DistrictModel> get(){
        LOGGER.debug("Begin inside DistrictController.get()");
        List<District> districts = districtService.getAll();
        LOGGER.debug("End inside DistrictController.get()");
        return districts != null ? objectMapper.convertToListDTO(districts, DistrictModel.class) : new ArrayList<>();
    }
}
