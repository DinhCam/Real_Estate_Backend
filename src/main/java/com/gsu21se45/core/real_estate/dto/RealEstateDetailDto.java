package com.gsu21se45.core.real_estate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RealEstateDetailDto {

    private int id;
    private String title;
    private String description;
    private String typeName;
    private int view;
    private String sellerId;
    private String sellerName;
    private String avatar;
    private String staffId;
    private String staffName;
    private double area;
    private double price;
    private String direction;
    private String balconyDirection;
    private int numberOfBedroom;
    private int numberOfBathroom;
    private String project;
    private String investor;
    private Set<ImageDto> images;
    private Set<FacilityDto> facilities;
    private String streetName;
    private String wardName;
    private String disName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createAt;
}