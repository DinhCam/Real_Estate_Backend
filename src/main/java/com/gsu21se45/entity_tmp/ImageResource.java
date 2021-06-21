package com.gsu21se45.entity_tmp;

import javax.persistence.*;

@Entity
@Table(name = "image_resource", schema = "real_estate", catalog = "")
public class ImageResource {
    private int id;
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
    @Column(name = "img_url", nullable = true, length = 255)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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