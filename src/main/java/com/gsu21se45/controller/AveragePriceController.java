package com.gsu21se45.controller;

import com.gsu21se45.common.constant.AppConstant;
import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.AveragePriceModel;
import com.gsu21se45.entity.AveragePrice;
import com.gsu21se45.log.Logger;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.AveragePriceService;
import com.gsu21se45.service.RealEstateTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + RestEntityConstant.URI_AVERAGE_PRICE)
public class AveragePriceController extends Logger {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(AveragePriceController.class);

    @Autowired
    private RealEstateTypeService realEstateTypeService;

    @Autowired
    private AveragePriceService averagePriceService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = RestEntityConstant.URI_GET)
    @ApiOperation(value = "Get average price")
    public List<AveragePriceModel> get(@RequestParam(name = "reType", required = true) String reType,
                                       @RequestParam(name = "id", required = true) int id,
                                       @RequestParam(name = "addressType", required = true) String addressType,
                                       @RequestParam(name = "month", required = true) int month,
                                       @RequestParam(name = "year", required = true) int year) {
        LOGGER.debug("Begin inside AveragePriceController.get()");
        int reTypeId = realEstateTypeService.getIdByName(reType);
        List<AveragePrice> entities = null;
        if (addressType.equalsIgnoreCase(AppConstant.STREET_TYPE)) {
            entities = averagePriceService.getByStreet(id, month, year, reTypeId);
        } else if (addressType.equalsIgnoreCase(AppConstant.WARD_TYPE)) {
            entities = averagePriceService.getByWard(id, month, year, reTypeId);
        } else {
            entities = averagePriceService.getByDistrict(id, month, year, reTypeId);
        }
        LOGGER.debug("End inside AveragePriceController.get()");
        return entities != null ? objectMapper.convertToListDTO(entities, AveragePriceModel.class) : new ArrayList<>();
    }
}
