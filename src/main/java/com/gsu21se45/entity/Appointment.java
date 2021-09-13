package com.gsu21se45.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "appointment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment implements Serializable {

    @Id
    @Column(name = "id", length = 255)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    @Column(name = "schedule_date")
    private Date scheduleDate;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "status")
    private String status;

    public Appointment(String id) {
        this.id = id;
    }

}
