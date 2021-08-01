package com.gsu21se45.core.real_estate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RealEstateActiveByStaffDto {
    private int id;
    private String title;
    private String description;
    private int view;
    private String status;
    private String sellerId;
    private String sellerName;
    private String sellerAvatar;
    private String buyerId;
    private String buyerName;
    private String buyerAvatar;
    private Double offeredPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createAtDeal;
    private String staffId;
    private String staffName;
    private String staffAvatar;
    private Double area;
    private Double price;
    private String reason;
    private int numberOfBedroom;
    private int numberOfBathroom;
    private String project;
    private String realEstateNo;
    private String streetName;
    private String wardName;
    private String disName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createAt;
    private Set<ImageDto> images;
}
