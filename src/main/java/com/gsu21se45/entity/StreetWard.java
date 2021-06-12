package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "street_ward", schema = "real_estate", catalog = "")
public class StreetWard {
    private int id;
    private Integer wardId;
    private Integer streetId;
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
    @Column(name = "ward_id", nullable = true)
    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }

    @Basic
    @Column(name = "street_id", nullable = true)
    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    @Basic
    @Column(name = "average_price", nullable = true, precision = 0)
    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreetWard that = (StreetWard) o;
        return id == that.id && Objects.equals(wardId, that.wardId) && Objects.equals(streetId, that.streetId) && Objects.equals(averagePrice, that.averagePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wardId, streetId, averagePrice);
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
