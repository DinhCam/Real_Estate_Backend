package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "real_estate_detail", schema = "real_estate", catalog = "")
public class RealEstateDetail {
    private int id;
    private Integer realEstateId;
    private Integer facilityId;
    private Integer streetWardId;
    private Integer typeId;
    private String description;
    private Double area;
    private Double price;
    private String direction;
    private Integer numberOfBedroom;
    private Integer numberOfBathroom;
    private Integer lot;
    private Collection<ImageResource> imageResourcesById;
    private RealEstate realEstateByRealEstateId;
    private StreetWard streetWardByStreetWardId;
    private RealEstateType realEstateTypeByTypeId;
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
    @Column(name = "real_estate_id", nullable = true)
    public Integer getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(Integer realEstateId) {
        this.realEstateId = realEstateId;
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
    @Column(name = "street_ward_id", nullable = true)
    public Integer getStreetWardId() {
        return streetWardId;
    }

    public void setStreetWardId(Integer streetWardId) {
        this.streetWardId = streetWardId;
    }

    @Basic
    @Column(name = "type_id", nullable = true)
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "area", nullable = true, precision = 0)
    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "direction", nullable = true, length = 255)
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Basic
    @Column(name = "number_of_bedroom", nullable = true)
    public Integer getNumberOfBedroom() {
        return numberOfBedroom;
    }

    public void setNumberOfBedroom(Integer numberOfBedroom) {
        this.numberOfBedroom = numberOfBedroom;
    }

    @Basic
    @Column(name = "number_of_bathroom", nullable = true)
    public Integer getNumberOfBathroom() {
        return numberOfBathroom;
    }

    public void setNumberOfBathroom(Integer numberOfBathroom) {
        this.numberOfBathroom = numberOfBathroom;
    }

    @Basic
    @Column(name = "lot", nullable = true)
    public Integer getLot() {
        return lot;
    }

    public void setLot(Integer lot) {
        this.lot = lot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealEstateDetail that = (RealEstateDetail) o;
        return id == that.id && Objects.equals(realEstateId, that.realEstateId) && Objects.equals(facilityId, that.facilityId) && Objects.equals(streetWardId, that.streetWardId) && Objects.equals(typeId, that.typeId) && Objects.equals(description, that.description) && Objects.equals(area, that.area) && Objects.equals(price, that.price) && Objects.equals(direction, that.direction) && Objects.equals(numberOfBedroom, that.numberOfBedroom) && Objects.equals(numberOfBathroom, that.numberOfBathroom) && Objects.equals(lot, that.lot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, realEstateId, facilityId, streetWardId, typeId, description, area, price, direction, numberOfBedroom, numberOfBathroom, lot);
    }

    @OneToMany(mappedBy = "realEstateDetailByRealEstateDetailId")
    public Collection<ImageResource> getImageResourcesById() {
        return imageResourcesById;
    }

    public void setImageResourcesById(Collection<ImageResource> imageResourcesById) {
        this.imageResourcesById = imageResourcesById;
    }

    @ManyToOne
    @JoinColumn(name = "real_estate_id", referencedColumnName = "id")
    public RealEstate getRealEstateByRealEstateId() {
        return realEstateByRealEstateId;
    }

    public void setRealEstateByRealEstateId(RealEstate realEstateByRealEstateId) {
        this.realEstateByRealEstateId = realEstateByRealEstateId;
    }

    @ManyToOne
    @JoinColumn(name = "street_ward_id", referencedColumnName = "id")
    public StreetWard getStreetWardByStreetWardId() {
        return streetWardByStreetWardId;
    }

    public void setStreetWardByStreetWardId(StreetWard streetWardByStreetWardId) {
        this.streetWardByStreetWardId = streetWardByStreetWardId;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    public RealEstateType getRealEstateTypeByTypeId() {
        return realEstateTypeByTypeId;
    }

    public void setRealEstateTypeByTypeId(RealEstateType realEstateTypeByTypeId) {
        this.realEstateTypeByTypeId = realEstateTypeByTypeId;
    }

    @OneToMany(mappedBy = "realEstateDetailByRealEstateDetailId")
    public Collection<RealEstateFacility> getRealEstateFacilitiesById() {
        return realEstateFacilitiesById;
    }

    public void setRealEstateFacilitiesById(Collection<RealEstateFacility> realEstateFacilitiesById) {
        this.realEstateFacilitiesById = realEstateFacilitiesById;
    }
}
