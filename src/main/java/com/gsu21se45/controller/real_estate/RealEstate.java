package com.gsu21se45.controller.real_estate;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.common.response.PaginationResponse;
import com.gsu21se45.core.real_estate.dto.RealEstateDto;
import com.gsu21se45.core.real_estate.service.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/rs")
public class RealEstate {

    @Autowired
    private RealEstateService rs;

    @PostMapping
    public PaginationResponse<RealEstateDto> getRealEstates(@RequestBody RequestPrams r){
        Page<RealEstateDto> data = rs.getRealEstates(r);
        return new PaginationResponse<>(data);
    }
}