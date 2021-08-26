package com.gsu21se45.common.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPrams {
    private Integer page;
    private Integer size = 30;
    private String search;
    private Double minPrice;
    private Double maxPrice;
    private Double minArea;
    private Double maxArea;
    private Integer type;
    private String staffId;
    private String sellerId;
    private String userId;
    private Integer disId;
    private Integer wardId;
    private String direction;
    private Integer numberOfBedroom;
}