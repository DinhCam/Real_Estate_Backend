package com.gsu21se45.core.real_estate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RealEstateDetailDto {

    private int id;
    private String title;
    private String description;
    private Double latitude;
    private Double longitude;
    private int typeId;
    private String typeName;
    private int view;
    private String sellerId;
    private String sellerPhone;
    private String sellerName;
    private String sellerAvatar;
    private String staffId;
    private String staffPhone;
    private String staffName;
    private String staffAvatar;
    private String dataentryId;
    private String dataentryPhone;
    private String dataentryName;
    private String dataentryAvatar;
    private double length;
    private double width;
    private double area;
    private String floor;
    private double price;
    private String direction;
    private String balconyDirection;
    private int numberOfBedroom;
    private int numberOfBathroom;
    private String project;
    private String investor;
    private String juridical;
    private String furniture;
    private Set<ImageDto> images;
    private Map<String, Set<FacilityDto>> facilities = new LinkedHashMap<>();
    private String realEstateNo;
    private String streetName;
    private int wardId;
    private String wardName;
    private int disId;
    private String disName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createAt;
}