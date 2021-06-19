package com.gsu21se45.entity_tmp;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "facility_type", schema = "real_estate", catalog = "")
public class FacilityType {
    private int id;
    private String name;
    private Collection<Facility> facilitiesById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacilityType that = (FacilityType) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "facilityTypeByTypeId")
    public Collection<Facility> getFacilitiesById() {
        return facilitiesById;
    }

    public void setFacilitiesById(Collection<Facility> facilitiesById) {
        this.facilitiesById = facilitiesById;
    }
}
