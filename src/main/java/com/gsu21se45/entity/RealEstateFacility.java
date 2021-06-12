package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "real_estate_facility", schema = "real_estate", catalog = "")
public class RealEstateFacility {
    private int id;
    private Integer realEstateDetailId;
    private Integer facilityId;
    private Double distance;
    private RealEstateDetail realEstateDetailByRealEstateDetailId;
    private Facility facilityByFacilityId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "real_estate_detail_id", nullable = true)
    public Integer getRealEstateDetailId() {
        return realEstateDetailId;
    }

    public void setRealEstateDetailId(Integer realEstateDetailId) {
        this.realEstateDetailId = realEstateDetailId;
    }

    @Basic
    @Column(name = "facility_id", nullable = true)
    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    @Basic
    @Column(name = "distance", nullable = true, precision = 0)
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealEstateFacility that = (RealEstateFacility) o;
        return id == that.id && Objects.equals(realEstateDetailId, that.realEstateDetailId) && Objects.equals(facilityId, that.facilityId) && Objects.equals(distance, that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, realEstateDetailId, facilityId, distance);
    }

    @ManyToOne
    @JoinColumn(name = "real_estate_detail_id", referencedColumnName = "id")
    public RealEstateDetail getRealEstateDetailByRealEstateDetailId() {
        return realEstateDetailByRealEstateDetailId;
    }

    public void setRealEstateDetailByRealEstateDetailId(RealEstateDetail realEstateDetailByRealEstateDetailId) {
        this.realEstateDetailByRealEstateDetailId = realEstateDetailByRealEstateDetailId;
    }

    @ManyToOne
    @JoinColumn(name = "facility_id", referencedColumnName = "id")
    public Facility getFacilityByFacilityId() {
        return facilityByFacilityId;
    }

    public void setFacilityByFacilityId(Facility facilityByFacilityId) {
        this.facilityByFacilityId = facilityByFacilityId;
    }
}
