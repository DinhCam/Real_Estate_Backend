package com.gsu21se45.core.real_estate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GRealEstateAssignedStaffDto {
    private int realEstateId;
    private String sellerId;
    private String sellerName;
    private String staffName;
    private String title;
    private String description;
    private String streetName;
    private String wardName;
    private String disName;
    private Double area;
    private Double price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createAt;
    private List<BuyerDto> buyers = new ArrayList<>();
}