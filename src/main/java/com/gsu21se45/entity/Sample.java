package com.gsu21se45.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sample")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sample implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "street_id")
    private int streetId;

    @Column(name = "ward_id")
    private int wardId;

    @Column(name = "district_id")
    private int districtId;

    @Column(name = "price")
    private double price;

    @Column(name = "area")
    private double area;

    @Column(name = "post_time")
    private Date postTime;

    @Column(name = "type")
    private int type;

}
