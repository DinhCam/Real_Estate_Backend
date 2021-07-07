package com.gsu21se45.helper;

import com.gsu21se45.dto.AppointmentModel;
import com.gsu21se45.dto.RealEstateModel;
import com.gsu21se45.entity.Appointment;
import com.gsu21se45.entity.RealEstate;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.RealEstateService;
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

    public List<AppointmentModel> getRealEstates(List<Appointment> appointments){
        List<AppointmentModel> appointmentModels = appointments != null ? objectMapper.convertToListDTO(appointments, AppointmentModel.class) : new ArrayList<>();
        for(AppointmentModel model: appointmentModels){
            RealEstate realEstate = realEstateService.getByConversationId(model.getConversationId());
            model.setRealEstateModel((RealEstateModel) objectMapper.convertToDTO(realEstate, RealEstateModel.class));
        }
        return appointmentModels;
    }
}
