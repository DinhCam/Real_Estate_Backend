package com.gsu21se45.controller.real_estate;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.common.response.PaginationResponse;
import com.gsu21se45.core.real_estate.dto.GRealEstateAssignedStaffDto;
import com.gsu21se45.core.real_estate.dto.RealEstateDto;
import com.gsu21se45.core.real_estate.service.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/realEstate")
public class RealEstateController {

    @Autowired
    private RealEstateService rs;

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
}