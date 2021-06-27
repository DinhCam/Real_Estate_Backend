package com.gsu21se45.common.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPrams {
    private Integer page;
    private Integer size;
    private String address;
    private Double fromPrice;
    private Double toPrice;
    private Double fromArea;
    private Double toArea;
    private Integer type;
    private String staffId;
    private String sellerId;
    private String userId;
    private String title;
    private String project;
}