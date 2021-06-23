package com.gsu21se45.repository;

import com.gsu21se45.entity.Appointment;
import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findAppointmentsByConversation(Conversation conversation);

    @Query(value = "select a.id, a.conversation_id, a.staff_id, a.schedule_date, a.create_at, a.status " +
            "from appointment a join conversation c " +
            "on a.conversation_id = c.id " +
            "and c.seller_id = :sellerId " +
            "and a.status = :status " +
            "and a.schedule_date >= :time", nativeQuery = true)
    List<Appointment> findBySellerIdAndStatus(@Param(value = "sellerId") String sellerId,
                                              @Param(value = "status") String status,
                                              @Param(value = "time") Date time);

    @Modifying
    @Transactional
    @Query(value = "update appointment set status = :status where id = :appointmentId", nativeQuery = true)
    void update(@Param(value = "appointmentId") int appointmentId,
                @Param(value = "status") String status);

    @Query(value = "select a.id, a.conversation_id, a.staff_id, a.schedule_date, a.create_at, a.status " +
            "from appointment a join conversation c " +
            "on a.conversation_id = c.id " +
            "and c.buyer_id = :buyerId " +
            "and a.status = :status " +
            "and a.schedule_date >= :time", nativeQuery = true)
    List<Appointment> findByBuyerIdAndStatus(@Param(value = "buyerId") String buyerId,
                                             @Param(value = "status") String status,
                                             @Param(value = "time") Date time);

    List<Appointment> findAppointmentsByStaffAndStatusAndScheduleDateIsGreaterThanEqual (User staff, String status, Date time);
}
