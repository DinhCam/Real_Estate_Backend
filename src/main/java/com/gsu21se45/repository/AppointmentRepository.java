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

    @Query(value = "select a.id, a.conversation_id, a.seller_id, a.schedule_date, a.create_at, a.status " +
            "from appointment a join conversation c " +
            "on a.conversation_id = c.id " +
            "and c.staff_id = :staffId " +
            "and a.status = :status " +
            "order by schedule_date desc", nativeQuery = true)
    List<Appointment> findByStaffIdAndStatus(@Param(value = "staffId") String staffId,
                                              @Param(value = "status") String status);

    @Modifying
    @Transactional
    @Query(value = "update appointment set status = :status where id = :appointmentId", nativeQuery = true)
    void update(@Param(value = "appointmentId") String appointmentId,
                @Param(value = "status") String status);

    @Query(value = "select a.id, a.conversation_id, a.seller_id, a.schedule_date, a.create_at, a.status " +
            "from appointment a join conversation c " +
            "on a.conversation_id = c.id " +
            "and c.buyer_id = :buyerId " +
            "and a.status = :status " +
            "order by schedule_date desc", nativeQuery = true)
    List<Appointment> findByBuyerIdAndStatus(@Param(value = "buyerId") String buyerId,
                                             @Param(value = "status") String status);

//    List<Appointment> findAppointmentsByStaffAndStatusOrderByScheduleDateDesc (User staff, String status);

    List<Appointment> findAppointmentsBySellerAndStatusOrderByScheduleDateDesc (User seller, String status);

    Appointment findByConversationAndCreateAt(Conversation conversation, Date createAt);
}
