package com.gsu21se45.core.real_estate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NearBySearchDto {
    private GeometryDto geometry;
    private String name;
    private Integer facilityTypeId = 2;
}
