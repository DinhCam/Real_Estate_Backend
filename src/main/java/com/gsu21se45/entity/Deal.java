package com.gsu21se45.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "deal")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Deal implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @Column(name = "offered_price")
    private double offeredPrice;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "status")
    private boolean status;

}
