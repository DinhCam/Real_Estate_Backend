package com.gsu21se45.controller.real_estate;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.common.response.PaginationResponse;
import com.gsu21se45.core.real_estate.dto.*;
import com.gsu21se45.core.real_estate.service.RealEstateService;
import io.swagger.annotations.ApiOperation;
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

    @PostMapping(value = "/getAllRealEstate")
    @ApiOperation("Get all real estate")
    public PaginationResponse<RealEstateDto> getAllRealEstates(@RequestBody RequestPrams r){
        Page<RealEstateDto> data = rs.getAllRealEstates(r);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getRealEstateDetail/{id}")
    @ApiOperation("Get a real estate detail by id")
    public RealEstateDetailDto getRealEstateDetailById(@PathVariable Integer id){
        return rs.getRealEstateDetailById(id);
    }

    @GetMapping(value = "/getRealEstateBySellerId/{sellerId}/{page}")
    @ApiOperation("Get all real estate of a seller")
    public PaginationResponse<GRealEstateBySellerOrStaffDto> getRealEstateBySellerId(@PathVariable String sellerId, @PathVariable Integer page){
        Integer size = 30;
        Page<GRealEstateBySellerOrStaffDto> data = rs.getRealEstatesBySellerId(sellerId, page, size);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getRealEstatesNotAssign/{page}")
    @ApiOperation("Get all real estate not assigned")
    public PaginationResponse<RealEstateDto> getRealEstatesNotAssign(@PathVariable Integer page){
        Integer size = 30;
        Page<RealEstateDto> data = rs.getRealEstatesNotAssign(page, size);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getRealEstatesInactive/{page}")
    @ApiOperation("Get all real estate have inactive status")
    public PaginationResponse<RealEstateDto> getRealEstatesInactive(@PathVariable Integer page){
        Integer size = 30;
        Page<RealEstateDto> data = rs.getRealEstatesInactive(page, size);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getRealEstateAssignStaff/{staffId}/{page}")
    @ApiOperation("Get all real estate assigned of a staff")
    public PaginationResponse<GRealEstateBySellerOrStaffDto> getRealEstateAssignStaff(@PathVariable String staffId, @PathVariable Integer page){
        Integer size = 30;
        Page<GRealEstateBySellerOrStaffDto> data = rs.getRealEstateAssignStaff(staffId, page, size);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getAllRealEstateType")
    @ApiOperation("Get all real estate type")
    public List<RealEstateTypeDto> getAllRealEstateType(){
        return  rs.getAllRealEstateType();
    }

    @PostMapping(value = "/createRealEstate")
    @ApiOperation("Create a new real estate")
    public HttpStatus createRealEstate(@RequestBody CRealEstate cRealEstate){
        return rs.createRealEstate(cRealEstate) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @PutMapping(value = "/updateRealEstateStatusByStaffAccuracy")
    @ApiOperation("Update the status of real estate when it has been accuracy staffed")
    public HttpStatus updateRealEstateStatusByStaffAccuracy(@RequestBody UpdateStatusByStaffAccuracy updateStatusByStaffAccuracy){
        return rs.updateRealEstateStatusByStaffAccuracy(updateStatusByStaffAccuracy) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @PutMapping(value = "/updateRealEstateByStaffAssign")
    @ApiOperation("Update real estate when staff assigned")
    public HttpStatus updateRealEstateByStaffAssign(@RequestBody UpdateStatusByStaffAccuracy updateStatusByStaffAccuracy){
        return rs.updateRealEstateByStaffAssign(updateStatusByStaffAccuracy) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @PutMapping(value = "/updateRealEstateStatusBySellerCancel")
    @ApiOperation("Update the status of real estate when seller wants to cancel post")
    public HttpStatus updateRealEstateStatusBySellerCancel(@RequestBody UpdateStatusBySellerCancel updateStatusBySellerCancel){
        return rs.updateRealEstateStatusBySellerCancel(updateStatusBySellerCancel) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }
}