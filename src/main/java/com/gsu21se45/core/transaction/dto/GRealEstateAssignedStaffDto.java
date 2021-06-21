package com.gsu21se45.core.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GRealEstateAssignedStaffDto {
    private int realEstateId;
    private String sellerName;
    private String staffName;
    private String title;
    private String streetName;
    private String wardName;
    private String disName;
    private Double area;
    private Double price;
    private List<BuyerDto> buyers = new ArrayList<>();
}