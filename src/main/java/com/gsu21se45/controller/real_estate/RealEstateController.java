package com.gsu21se45.controller.real_estate;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.common.response.PaginationResponse;
import com.gsu21se45.core.real_estate.dto.*;
import com.gsu21se45.core.real_estate.service.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/getRealEstateBySellerId/{sellerId}/{page}")
    public PaginationResponse<RealEstateDto> getRealEstateBySellerId(@PathVariable String sellerId, @PathVariable Integer page){
        Integer size = 30;
        Page<RealEstateDto> data = rs.getRealEstatesBySellerId(sellerId, page, size);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getRealEstatesNotAssign/{page}")
    public PaginationResponse<RealEstateDto> getRealEstatesNotAssign(@PathVariable Integer page){
        Integer size = 30;
        Page<RealEstateDto> data = rs.getRealEstatesNotAssign(page, size);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getRealEstateAssignStaff/{staffId}/{page}")
    public PaginationResponse<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(@PathVariable String staffId, @PathVariable Integer page){
        Integer size = 30;
        Page<GRealEstateAssignedStaffDto> data = rs.getRealEstateAssignStaff(staffId, page, size);
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

    @PostMapping(value = "/createRealEstate")
    public HttpStatus createRealEstate(@RequestBody CRealEstate cRealEstate){
        return rs.createRealEstate(cRealEstate) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @PutMapping(value = "/updateRealEstateStatusByStaffAccuracy")
    public HttpStatus updateRealEstateStatusByStaffAccuracy(@RequestBody UpdateStatusByStaffAccuracy updateStatusByStaffAccuracy){
        return rs.updateRealEstateStatusByStaffAccuracy(updateStatusByStaffAccuracy) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @PutMapping(value = "/updateRealEstateByStaffAssign")
    public HttpStatus updateRealEstateByStaffAssign(@RequestBody UpdateStatusByStaffAccuracy updateStatusByStaffAccuracy){
        return rs.updateRealEstateByStaffAssign(updateStatusByStaffAccuracy) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @PutMapping(value = "/updateRealEstateStatusBySellerCancel")
    public HttpStatus updateRealEstateStatusBySellerCancel(@RequestBody UpdateStatusBySellerCancel updateStatusBySellerCancel){
        return rs.updateRealEstateStatusBySellerCancel(updateStatusBySellerCancel) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }
}