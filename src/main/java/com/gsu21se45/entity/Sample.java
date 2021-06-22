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

    @Column(name = "street", length = 255)
    private String street;

    @Column(name = "ward", length = 255)
    private String ward;

    @Column(name = "district", length = 255)
    private String district;

    @Column(name = "price")
    private double price;

    @Column(name = "area")
    private double area;

    @Column(name = "post_time", length = 255)
    private Date postTime;

    @Column(name = "type", length = 255)
    private String type;

}
