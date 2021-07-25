package com.gsu21se45.helper;

import com.gsu21se45.dto.AddressModel;
import com.gsu21se45.dto.AppointmentModel;
import com.gsu21se45.dto.RealEstateModel;
import com.gsu21se45.entity.Appointment;
import com.gsu21se45.entity.RealEstate;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.RealEstateDetailService;
import com.gsu21se45.service.RealEstateService;
import com.gsu21se45.service.StreetWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentHelper {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RealEstateService realEstateService;

    @Autowired
    private RealEstateDetailService realEstateDetailService;

    @Autowired
    private StreetWardService streetWardService;

    public List<AppointmentModel> getRealEstates(List<Appointment> appointments) {
        List<AppointmentModel> appointmentModels = appointments != null ? objectMapper.convertToListDTO(appointments, AppointmentModel.class) : new ArrayList<>();
        for (AppointmentModel model : appointmentModels) {
            RealEstate realEstate = realEstateService.getByConversationId(model.getConversationId());
            int streetWardId = realEstateDetailService.getStreetWardIdById(realEstate.getId());
            AddressModel address = streetWardService.getById(streetWardId);
            model.setRealEstateModel((RealEstateModel) objectMapper.convertToDTO(realEstate, RealEstateModel.class));
            model.getRealEstateModel().setAddress(address);
        }
        return appointmentModels;
    }


}
