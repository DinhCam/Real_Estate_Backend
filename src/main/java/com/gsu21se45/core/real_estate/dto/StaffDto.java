package com.gsu21se45.core.real_estate.dto;

import com.gsu21se45.core.address.dto.DistrictDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {
    private String id;
    private String fullname;
    private String avatar;
    private String phone;
    private Set<DistrictDto> workingArea;
}
