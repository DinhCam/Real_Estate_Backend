package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "street_ward", schema = "real_estate", catalog = "")
public class StreetWard {
    private int id;
    private Double averagePrice;
    private Collection<RealEstateDetail> realEstateDetailsById;
    private Ward wardByWardId;
    private Street streetByStreetId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "average_price", nullable = true, precision = 0)
    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    @OneToMany(mappedBy = "streetWardByStreetWardId")
    public Collection<RealEstateDetail> getRealEstateDetailsById() {
        return realEstateDetailsById;
    }

    public void setRealEstateDetailsById(Collection<RealEstateDetail> realEstateDetailsById) {
        this.realEstateDetailsById = realEstateDetailsById;
    }

    @ManyToOne
    @JoinColumn(name = "ward_id", referencedColumnName = "id")
    public Ward getWardByWardId() {
        return wardByWardId;
    }

    public void setWardByWardId(Ward wardByWardId) {
        this.wardByWardId = wardByWardId;
    }

    @ManyToOne
    @JoinColumn(name = "street_id", referencedColumnName = "id")
    public Street getStreetByStreetId() {
        return streetByStreetId;
    }

    public void setStreetByStreetId(Street streetByStreetId) {
        this.streetByStreetId = streetByStreetId;
    }
}
