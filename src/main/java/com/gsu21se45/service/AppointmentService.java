package com.gsu21se45.service;

import com.gsu21se45.entity.Appointment;
import com.gsu21se45.entity.Conversation;

import java.util.Date;
import java.util.List;

public interface AppointmentService {

    List<Appointment> findByConversationId(int conversationId);

    Appointment save(Appointment appointment);

    void update(String appointmentId, String status);

    List<Appointment> findBySellerIdAndStatus(String sellerId, String status);

    List<Appointment> findByBuyerIdAndStatus(String buyerId, String status);

    List<Appointment> findByStaffIdAndStatus(String staffId, String status);

    Appointment findByConversationAndCreateAt(Conversation conversation, Date createAt);
}
