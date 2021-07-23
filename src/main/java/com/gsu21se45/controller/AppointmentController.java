package com.gsu21se45.controller;

import com.gsu21se45.common.constant.AppConstant;
import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.AppointmentModel;
import com.gsu21se45.dto.RealEstateModel;
import com.gsu21se45.entity.Appointment;
import com.gsu21se45.entity.RealEstate;
import com.gsu21se45.helper.AppointmentHelper;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.AppointmentService;
import com.gsu21se45.service.RealEstateService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + RestEntityConstant.URI_APPOINTMENT)
public class AppointmentController {

    private static final Logger LOGGER = LogManager.getLogger(AppointmentController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private RealEstateService realEstateService;

    @Autowired
    private AppointmentHelper appointmentHelper;

    @GetMapping(value = RestEntityConstant.URI_ALL + RestEntityConstant.URI_SELLER)
    @ApiOperation("Get all appointments by seller without cancel")
    public @ResponseBody
    List<AppointmentModel> getAllBySellerId(@RequestParam(name = RestEntityConstant.SELLER_ID, required = true) String sellerId,
                                            @RequestParam(name = RestEntityConstant.STATUS, defaultValue = RestEntityConstant.PASS_STATUS, required = false) String status) {
        LOGGER.debug("Begin inside AppointmentController.getAllBySellerId()");
//        Date time = new Date(System.currentTimeMillis() - AppConstant.MILISECONDS_IN_TIME_FRAME);
        List<Appointment> appointments = appointmentService.findBySellerIdAndStatus(sellerId, status);
        List<AppointmentModel> appointmentModels = appointmentHelper.getRealEstates(appointments);
        LOGGER.debug("End inside AppointmentController.getAllBySellerId()");
        return appointmentModels;
    }

    @GetMapping(value = RestEntityConstant.URI_ALL + RestEntityConstant.URI_BUYER)
    @ApiOperation("Get all appointments by buyer without cancel")
    public @ResponseBody
    List<AppointmentModel> getAllByBuyerId(@RequestParam(name = RestEntityConstant.BUYER_ID, required = true) String buyerId,
                                            @RequestParam(name = RestEntityConstant.STATUS, defaultValue = RestEntityConstant.PASS_STATUS, required = false) String status) {
        LOGGER.debug("Begin inside AppointmentController.getAllByBuyerId()");
//        Date time = new Date(System.currentTimeMillis() - AppConstant.MILISECONDS_IN_TIME_FRAME);
        List<Appointment> appointments = appointmentService.findByBuyerIdAndStatus(buyerId, status);
        List<AppointmentModel> appointmentModels = appointmentHelper.getRealEstates(appointments);
        LOGGER.debug("End inside AppointmentController.getAllByBuyerId()");
        return appointmentModels;
    }

    @GetMapping(value = RestEntityConstant.URI_ALL + RestEntityConstant.URI_STAFF)
    @ApiOperation("Get all appointments by staff without cancel")
    public @ResponseBody
    List<AppointmentModel> getAllByStaffId(@RequestParam(name = RestEntityConstant.STAFF_ID, required = true) String staffId,
                                           @RequestParam(name = RestEntityConstant.STATUS, defaultValue = RestEntityConstant.PASS_STATUS, required = false) String status) {
        LOGGER.debug("Begin inside AppointmentController.getAllByStaffId()");
//        Date time = new Date(System.currentTimeMillis() - AppConstant.MILISECONDS_IN_TIME_FRAME);
        List<Appointment> appointments = appointmentService.findByStaffIdAndStatus(staffId, status);
        List<AppointmentModel> appointmentModels = appointmentHelper.getRealEstates(appointments);
        LOGGER.debug("End inside AppointmentController.getAllByStaffId()");
        return appointmentModels;
    }

    @PostMapping(value = RestEntityConstant.URI_CREATE)
    @ApiOperation("Create a new appointment")
    public @ResponseBody
    AppointmentModel create(@RequestBody AppointmentModel model) {
        LOGGER.debug("Begin inside AppointmentController.create()");
        if (model != null) {
            String staffId = realEstateService.getStaffId(model.getConversationId());
            model.setStaffId(staffId);
            Appointment appointment = (Appointment) objectMapper.convertToEntity(model, Appointment.class);
            appointment = appointmentService.save(appointment);
            model.setId(appointment.getId());
            RealEstate realEstate = realEstateService.getByConversationId(model.getConversationId());
            model.setRealEstateModel((RealEstateModel) objectMapper.convertToDTO(realEstate, RealEstateModel.class));
        }
        LOGGER.debug("End inside AppointmentController.create()");
        return model;
    }

    @PutMapping(value = RestEntityConstant.URI_UPDATE)
    @ApiOperation("Update status")
    public void update(@RequestParam(name = RestEntityConstant.APPOINTMENT_ID, required = true) String appointmentId,
                       @RequestParam(name = RestEntityConstant.STATUS, required = true) String status) { //defaultValue = RestEntityConstant.CANCEL_STATUS + ""
        LOGGER.debug("Begin inside AppointmentController.update()");
        appointmentService.update(appointmentId, status);
        LOGGER.debug("End inside AppointmentController.update()");
    }
}
