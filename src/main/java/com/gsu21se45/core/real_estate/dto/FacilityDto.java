package com.gsu21se45.core.real_estate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FacilityDto {
    private Integer facilityId;
    private Integer facilityTypeId;
    private String facilityTypeName;
    private String facilityName;
    private Double latitudeFacility;
    private Double longitudeFacility;
    private String addressFacility;
    private Double distance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacilityDto that = (FacilityDto) o;
        return Objects.equals(facilityId, that.facilityId) && Objects.equals(facilityTypeId, that.facilityTypeId) && Objects.equals(facilityTypeName, that.facilityTypeName) && Objects.equals(facilityName, that.facilityName) && Objects.equals(latitudeFacility, that.latitudeFacility) && Objects.equals(longitudeFacility, that.longitudeFacility) && Objects.equals(addressFacility, that.addressFacility) && Objects.equals(distance, that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facilityId, facilityTypeId, facilityTypeName, facilityName, latitudeFacility, longitudeFacility, addressFacility, distance);
    }
}