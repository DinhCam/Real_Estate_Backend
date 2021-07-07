package com.gsu21se45.service.implement;

import com.gsu21se45.entity.Appointment;
import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.User;
import com.gsu21se45.repository.AppointmentRepository;
import com.gsu21se45.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<Appointment> findBySellerIdAndStatus(String sellerId, String status) {
        return appointmentRepository.findBySellerIdAndStatus(sellerId, status);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void update(int appointmentId, String status) {
        appointmentRepository.update(appointmentId, status);
    }

    @Override
    public List<Appointment> findByBuyerIdAndStatus(String buyerId, String status) {
        return appointmentRepository.findByBuyerIdAndStatus(buyerId, status);
    }

    @Override
    public List<Appointment> findByStaffIdAndStatus(String staffId, String status) {
        return appointmentRepository.findAppointmentsByStaffAndStatusOrderByScheduleDateDesc(new User(staffId), status);
    }

    @Override
    public Appointment findByConversationAndCreateAt(Conversation conversation, Date createAt) {
        return appointmentRepository.findByConversationAndCreateAt(conversation, createAt);
    }
}
