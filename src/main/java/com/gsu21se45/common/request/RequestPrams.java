package com.gsu21se45.common.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPrams {

    private Integer page;
    private Integer size;
    private String search;
    private Double price;
    private Double fromArea;
    private Double toArea;
    private Integer type;

}
