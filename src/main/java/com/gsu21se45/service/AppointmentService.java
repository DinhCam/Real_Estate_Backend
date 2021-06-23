package com.gsu21se45.service;

import com.gsu21se45.entity.Appointment;

import java.util.Date;
import java.util.List;

public interface AppointmentService {

    List<Appointment> findByConversationId(int conversationId);

    List<Appointment> findBySellerIdAndStatus(String sellerId, String status, Date time);

    Appointment save(Appointment appointment);

    void update(int appointmentId, String status);

    List<Appointment> findByBuyerIdAndStatus(String buyerId, String status, Date time);

    List<Appointment> findByStaffIdAndStatus(String staffId, String status, Date time);
}
