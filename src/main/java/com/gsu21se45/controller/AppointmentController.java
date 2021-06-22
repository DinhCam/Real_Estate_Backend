package com.gsu21se45.controller;

import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.AppointmentModel;
import com.gsu21se45.entity.Appointment;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.AppointmentService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + RestEntityConstant.URI_APPOINTMENT)
public class AppointmentController {

    private static final Logger LOGGER = LogManager.getLogger(AppointmentController.class);//???

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = RestEntityConstant.URI_ALL)
    @ApiOperation("Get all appointments without cancel")
    public @ResponseBody
    List<AppointmentModel> getAllBySellerId(@RequestParam(name = RestEntityConstant.SELLER_ID, required = true) String sellerId,
                                            @RequestParam(name = RestEntityConstant.STATUS, required = true) String status ) {
        LOGGER.debug("Begin inside AppointmentController.getAllBySellerId()");
        List<Appointment> appointments = appointmentService.findBySellerIdAndStatus(sellerId, status ); //!RestEntityConstant.CANCEL_STATUS
        LOGGER.debug("End inside AppointmentController.getAllBySellerId()");
        return appointments != null ? objectMapper.convertToListDTO(appointments, AppointmentModel.class) : null;
    }

    @PostMapping(value = RestEntityConstant.URI_CREATE)
    @ApiOperation("Create a new appointment")
    public @ResponseBody
    AppointmentModel create(@RequestBody AppointmentModel model) {
        LOGGER.debug("Begin inside AppointmentController.create()");
        if (model != null) {
            Appointment appointment = (Appointment) objectMapper.convertToEntity(model, Appointment.class);
            appointment = appointmentService.save(appointment);
            model.setId(appointment.getId());
        }
        LOGGER.debug("End inside AppointmentController.create()");
        return model;
    }

    @PutMapping(value = RestEntityConstant.URI_UPDATE)
    @ApiOperation("Update status")
    public void update(@RequestParam(name = RestEntityConstant.APPOINTMENT_ID, required = true) int appointmentId,
                       @RequestParam(name = RestEntityConstant.STATUS, required = true) String status) { //defaultValue = RestEntityConstant.CANCEL_STATUS + ""
        LOGGER.debug("Begin inside AppointmentController.update()");
        appointmentService.update(appointmentId, status);
        LOGGER.debug("End inside AppointmentController.update()");
    }
}
