package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Ward {
    private int id;
    private String name;
    private Collection<StreetWard> streetWardsById;
    private District districtByDistrictId;

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


    @OneToMany(mappedBy = "wardByWardId")
    public Collection<StreetWard> getStreetWardsById() {
        return streetWardsById;
    }

    public void setStreetWardsById(Collection<StreetWard> streetWardsById) {
        this.streetWardsById = streetWardsById;
    }

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    public District getDistrictByDistrictId() {
        return districtByDistrictId;
    }

    public void setDistrictByDistrictId(District districtByDistrictId) {
        this.districtByDistrictId = districtByDistrictId;
    }
}
