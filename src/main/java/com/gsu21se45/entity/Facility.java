package com.gsu21se45.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "facility")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Facility implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private FacilityType facilityTypeId;

    @Column(name = "name", length = 255)
    private String facilityName;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "address", length = 255)
    private String address;
}
