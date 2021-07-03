package com.gsu21se45.controller.address;

import com.gsu21se45.core.address.dto.DistrictDto;
import com.gsu21se45.core.address.service.DistrictWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class DistrictWardController {

    @Autowired
    private DistrictWardService rs;

    @PostMapping
    public boolean insertAddress(@RequestBody List<DistrictDto> districtDtos){
        return rs.insertDistrictWard(districtDtos);
    }
}
