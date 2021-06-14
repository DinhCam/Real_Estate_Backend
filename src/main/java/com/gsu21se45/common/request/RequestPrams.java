package com.gsu21se45.common.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPrams {

    private Integer page;
    private Integer size;

    public RequestPrams(){
        page = 0;
        size = 20;
    }
}
