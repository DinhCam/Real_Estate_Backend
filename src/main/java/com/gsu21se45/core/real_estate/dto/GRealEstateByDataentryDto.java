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
public class GRealEstateByDataentryDto {
    private int id;
    private String title;
    private String description;
    private int view;
    private String status;
    private String sellerId;
    private String sellerName;
    private String sellerAvatar;
    private String staffId;
    private String staffName;
    private String staffAvatar;
    private String dataentryId;
    private String dataentryName;
    private String dataentryAvatar;
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
    private Set<BuyerDto> buyers = new HashSet<>();
    private Set<ImageDto> images;
}
