package com.gsu21se45.controller.address;

import com.gsu21se45.core.address.dto.DistrictDto;
import com.gsu21se45.core.address.service.AddressService;
import com.gsu21se45.core.address.dto.AddressDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService rs;

    @PostMapping(value = "/insertAddressData")
    public HttpStatus insertAddress(@RequestBody List<DistrictDto> districtDtos){
        return rs.insertDistrictWard(districtDtos) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @GetMapping(value = "/getAddress")
    @ApiOperation("Get real estate address")
    public List<AddressDto> getAddress(){
        return  rs.getAddress();
    }
}
