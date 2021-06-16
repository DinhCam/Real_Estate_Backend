package com.gsu21se45.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "real_estate_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RealEstateDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "real_estate_id")
    private RealEstate realEstate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "street_ward_id")
    private StreetWard streetWard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private RealEstateType realEstateType;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "area")
    private double area;

    @Column(name = "price")
    private double price;

    @Column(name = "direction", length = 255)
    private String direction;

    @Column(name = "numOfBedroom")
    private int numOfBedroom;

    @Column(name = "numOfBathroom")
    private int numOfBathroom;

    @Column(name = "lot")
    private int lot;















}
