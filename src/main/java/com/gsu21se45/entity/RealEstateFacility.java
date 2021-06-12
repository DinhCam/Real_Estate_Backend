package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "real_estate_facility", schema = "real_estate", catalog = "")
public class RealEstateFacility {
    private int id;
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
    @Column(name = "distance", nullable = true, precision = 0)
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
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
