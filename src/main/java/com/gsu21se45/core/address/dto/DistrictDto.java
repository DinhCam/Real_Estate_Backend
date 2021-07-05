package com.gsu21se45.core.address.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDto {
    private Integer id;
    private String name;
    List<WardDto> wards;
}
