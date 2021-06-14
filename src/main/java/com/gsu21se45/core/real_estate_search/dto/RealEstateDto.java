package com.gsu21se45.core.real_estate_search.dto;

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
    private String view;
    private String sellerName;
    private String staffName;
    private double area;
    private double price;
    private Set<ImageDto> images;
    private Set<FacilityDto> facilities;
    private String streetName;
    private String wardName;
    private String disName;
    private Timestamp createAt;

}
