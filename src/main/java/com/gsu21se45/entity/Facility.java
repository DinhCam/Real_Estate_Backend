package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Facility {
    private int id;
    private String name;
    private FacilityType facilityTypeByTypeId;
    private Collection<RealEstateFacility> realEstateFacilitiesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    public FacilityType getFacilityTypeByTypeId() {
        return facilityTypeByTypeId;
    }

    public void setFacilityTypeByTypeId(FacilityType facilityTypeByTypeId) {
        this.facilityTypeByTypeId = facilityTypeByTypeId;
    }

    @OneToMany(mappedBy = "facilityByFacilityId")
    public Collection<RealEstateFacility> getRealEstateFacilitiesById() {
        return realEstateFacilitiesById;
    }

    public void setRealEstateFacilitiesById(Collection<RealEstateFacility> realEstateFacilitiesById) {
        this.realEstateFacilitiesById = realEstateFacilitiesById;
    }
}
