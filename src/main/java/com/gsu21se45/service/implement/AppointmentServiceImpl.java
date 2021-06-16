package com.gsu21se45.service.implement;

import com.gsu21se45.entity.Appointment;
import com.gsu21se45.entity.Conversation;
import com.gsu21se45.repository.AppointmentRepository;
import com.gsu21se45.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> findByConversationId(int conversationId) {
        return appointmentRepository.findAppointmentsByConversation(new Conversation(conversationId));
    }

    @Override
    public List<Appointment> findBySellerIdAndStatus(int sellerId, boolean status) {
        return appointmentRepository.findBySellerIdAndStatus(sellerId, status);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void update(int appointmentId, boolean status) {
        appointmentRepository.update(appointmentId, status);
    }
}
