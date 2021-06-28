package com.gsu21se45.controller.real_estate;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.common.response.PaginationResponse;
import com.gsu21se45.core.real_estate.dto.*;
import com.gsu21se45.core.real_estate.respo.RealEstateRespo;
import com.gsu21se45.core.real_estate.service.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/realEstate")
public class RealEstateController {

    @Autowired
    private RealEstateService rs;

    @Autowired
    private RealEstateRespo realEstateRespo;

    @PostMapping(value = "/getRealEstateDetail")
    public PaginationResponse<RealEstateDto> getRealEstates(@RequestBody RequestPrams r){
        Page<RealEstateDto> data = rs.getRealEstates(r);
        return new PaginationResponse<>(data);
    }

    @PostMapping(value = "/getRealEstateBySellerId")
    public PaginationResponse<RealEstateDto> getRealEstateBySellerId(@RequestBody RequestPrams r){
        Page<RealEstateDto> data = rs.getRealEstatesBySellerId(r);
        return new PaginationResponse<>(data);
    }

    @PostMapping(value = "/getRealEstateAssignStaff")
    public PaginationResponse<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(@RequestBody RequestPrams r){
        Page<GRealEstateAssignedStaffDto> data = rs.getRealEstateAssignStaff(r);
        return new PaginationResponse<>(data);
    }
    @GetMapping(value = "/{id}")
    public RealEstateDto getRealEstateById(@PathVariable Integer id){
        return rs.getRealEstateById(id);
    }

    @GetMapping(value = "/getAllRealEstateType")
    public List<RealEstateTypeDto> getAllRealEstateType(){
        return  rs.getAllRealEstateType();
    }

    @GetMapping(value = "/getAddress")
    public List<AddressDto> getAddress(){
        return  rs.getAddress();
    }

    @PostMapping(value = "/createRealEstate")
    public HttpStatus createRealEstate(@RequestBody CRealEstate cRealEstate){
        return rs.createRealEstate(cRealEstate) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }
}