package com.gsu21se45.core.real_estate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CRealEstate {
    private String sellerId;
    private String title;
    private int view;
    private Timestamp createAt;
    private String status;
    private int id;
    private int districtId;
    private int wardId;
    private String streetName;
    private String realEstateNo;
    private int typeId;
    private String description;
    private Double area;
    private Double price;
    private String direction;
    private String balconyDirection;
    private String project;
    private String investor;
    private int numberOfBedroom;
    private int numberOfBathroom;
    private int lot;
}
