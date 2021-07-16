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
public class GRealEstateByCensorDto {
    private int id;
    private String title;
    private String description;
    private int view;
    private String status;
    private String sellerId;
    private String sellerName;
    private String sellerAvatar;
    private Double area;
    private Double price;
    private int numberOfBedroom;
    private int numberOfBathroom;
    private String project;
    private String streetName;
    private String wardName;
    private String disName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createAt;
    private Set<ImageDto> images;
}
