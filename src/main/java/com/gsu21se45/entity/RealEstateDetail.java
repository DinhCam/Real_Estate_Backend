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
    @Column(name = "id")
    private int id;

    @Column(name = "real_estate_no", length = 255)
    private String realEstateNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "street_ward_id")
    private StreetWard streetWard;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private RealEstateType realEstateType;

    @Column(name = "description", length = 5000)
    private String description;

    @Column(name = "length")
    private double length;

    @Column(name = "width")
    private double width;

    @Column(name = "area")
    private double area;

    @Column(name = "floor")
    private String floor;

    @Column(name = "price")
    private double price;

    @Column(name = "direction", length = 255)
    private String direction;

    @Column(name = "balcony_direction", length = 255)
    private String balconyDirection;

    @Column(name = "project", length = 255)
    private String project;

    @Column(name = "investor", length = 255)
    private String investor;

    @Column(name = "juridical", length = 255)
    private String juridical;

    @Column(name = "furniture", length = 255)
    private String furniture;

    @Column(name = "number_of_bedroom")
    private int numOfBedroom;

    @Column(name = "number_of_bathroom")
    private int numOfBathroom;
}
