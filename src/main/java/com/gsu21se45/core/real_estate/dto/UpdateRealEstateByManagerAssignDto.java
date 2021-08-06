package com.gsu21se45.core.real_estate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRealEstateByManagerAssignDto {
    private int id;
    private String staffId;
}
