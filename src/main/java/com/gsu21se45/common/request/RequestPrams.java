package com.gsu21se45.common.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPrams {

    private int page;
    private int size;
    private Integer districtId;
    private Integer wardId;
    private Integer streetId;

    public RequestPrams(){
        page = 1;
        size = 20;
    }
}
