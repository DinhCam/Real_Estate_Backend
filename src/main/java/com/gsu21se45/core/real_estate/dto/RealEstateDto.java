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
public class RealEstateDto {
    private int id;
    private String title;
    private String description;
    private String typeName;
    private int view;
    private String sellerId;
    private String sellerName;
    private String sellerAvatar;
    private String staffId;
    private String staffName;
    private String staffAvatar;
    private String staffPhone;
    private double area;
    private double price;
    private int numberOfBedroom;
    private int numberOfBathroom;
    private String project;
    private Set<ImageDto> images;
    private String realEstateNo;
    private String streetName;
    private String wardName;
    private String disName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createAt;
}
