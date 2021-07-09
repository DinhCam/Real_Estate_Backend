package com.gsu21se45.core.real_estate.dto;

import com.gsu21se45.entity.Facility;
import com.gsu21se45.entity.ImageResource;
import com.gsu21se45.entity.RealEstateFacility;
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
    private Double latitude;
    private Double longitude;
    private String juridical;
    private String furniture;
    private int numberOfBedroom;
    private int numberOfBathroom;
    private List<ImageResource> images;
    private List<FacilityDto> facilities;
    private Double distance;
}
