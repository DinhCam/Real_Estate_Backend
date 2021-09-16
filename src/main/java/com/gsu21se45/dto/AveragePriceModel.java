package com.gsu21se45.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AveragePriceModel implements Serializable {

    private int id;

//    private int streetId;

    private int wardId;

    private int districtId;

    private double price;

    private int month;

    private int year;

    private int type;
}
