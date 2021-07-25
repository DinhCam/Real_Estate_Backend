package com.gsu21se45.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RealEstateModel implements Serializable {

    private int id;
    private String sellerId;
    private String staffId;
    private String title;
    private int view;
    private Date create_at;
    private String status;
    private AddressModel address;
}
