package com.gsu21se45.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gsu21se45.dto.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "real_estate")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RealEstate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private User staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dataentry_id")
    private User dataentry;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "view")
    private int view;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private String status;

    public RealEstate(int id) {
        this.id = id;
    }
}
