package com.gsu21se45.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "image_resource")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageResource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "real_estate_detail_id")
    private RealEstateDetail realEstateDetail;

    @Column(name = "img_url", length = 2000)
    private String imgUrl;


}
