package com.gsu21se45.core.real_estate_search.dto;

import com.gsu21se45.util.TypeTransformImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FacilityDto {
    private int id;
    private String facilityType;
    private String facilityName;
    private double distance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacilityDto that = (FacilityDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}