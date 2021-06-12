package com.gsu21se45.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "image_resource", schema = "real_estate", catalog = "")
public class ImageResource {
    private int id;
    private Integer realEstateDetailId;
    private String imgUrl;
    private RealEstateDetail realEstateDetailByRealEstateDetailId;

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
    @Column(name = "img_url", nullable = true, length = 255)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageResource that = (ImageResource) o;
        return id == that.id && Objects.equals(realEstateDetailId, that.realEstateDetailId) && Objects.equals(imgUrl, that.imgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, realEstateDetailId, imgUrl);
    }

    @ManyToOne
    @JoinColumn(name = "real_estate_detail_id", referencedColumnName = "id")
    public RealEstateDetail getRealEstateDetailByRealEstateDetailId() {
        return realEstateDetailByRealEstateDetailId;
    }

    public void setRealEstateDetailByRealEstateDetailId(RealEstateDetail realEstateDetailByRealEstateDetailId) {
        this.realEstateDetailByRealEstateDetailId = realEstateDetailByRealEstateDetailId;
    }
}
