package com.gsu21se45.core.real_estate.dto;

import com.gsu21se45.entity.ImageResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRealEstateDto {
    private String sellerId;
    private String dataentryId;
    private String title;
    private Timestamp createAt;
    private String status;
    private int id;
    private int districtId;
    private int wardId;
    private String streetName;
    private String realEstateNo;
    private String address;
    private int typeId;
    private String description;
    private double length;
    private double width;
    private Double area;
    private String floor;
    private Double price;
    private String direction;
    private String balconyDirection;
    private String project;
    private String investor;
    private String juridical;
    private String furniture;
    private int numberOfBedroom;
    private int numberOfBathroom;
    private List<ImageResource> images;
    private List<FacilityDto> facilities;
    private Double distance;
}
