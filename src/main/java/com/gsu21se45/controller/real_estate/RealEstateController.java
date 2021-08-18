package com.gsu21se45.controller.real_estate;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.common.response.PaginationResponse;
import com.gsu21se45.core.real_estate.dto.*;
import com.gsu21se45.core.real_estate.service.RealEstateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api/v1/realEstate")
public class RealEstateController {

    @Autowired
    RestTemplate restTemplate;

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

    @GetMapping(value = "/getRealEstateBySeller/{sellerId}/{status}/{page}")
    @ApiOperation("Get all real estate have ? status of a seller")
    public PaginationResponse<GRealEstateBySellerOrStaffDto> getRealEstateBySeller(@PathVariable String sellerId, @PathVariable String status, @PathVariable Integer page){
        Integer size = 30;
        Page<GRealEstateBySellerOrStaffDto> data = rs.getRealEstatesBySeller(sellerId, status, page, size);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getRealEstateByDataentry/{dataentryId}/{status}/{page}")
    @ApiOperation("Get all real estate have ? status of a dataentry")
    public PaginationResponse<GRealEstateByDataentryDto> getRealEstateByDataentry(@PathVariable String dataentryId, @PathVariable String status, @PathVariable Integer page){
        Integer size = 30;
        Page<GRealEstateByDataentryDto> data = rs.getRealEstatesByDataentry(dataentryId, status, page, size);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getRealEstateActiveBySeller/{sellerId}/{page}")
    @ApiOperation("Get all real estate have active status of a seller")
    public PaginationResponse<GRealEstateBySellerOrStaffDto> getRealEstateActiveBySeller(@PathVariable String sellerId, @PathVariable Integer page){
        Integer size = 30;
        Page<GRealEstateBySellerOrStaffDto> data = rs.getRealEstatesActiveBySeller(sellerId, page, size);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getRealEstatesNotAssign/{page}")
    @ApiOperation("Get all real estate not assigned")
    public PaginationResponse<RealEstateDto> getRealEstatesNotAssign(@PathVariable Integer page){
        Integer size = 30;
        Page<RealEstateDto> data = rs.getRealEstatesNotAssign(page, size);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getRealEstatesAssigned/{page}")
    @ApiOperation("Get all real estate assigned")
    public PaginationResponse<RealEstateDto> getRealEstatesAssigned(@PathVariable Integer page){
        Integer size = 30;
        Page<RealEstateDto> data = rs.getRealEstatesAssigned(page, size);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getRealEstatesByStaff/{staffId}/{status}/{page}")
    @ApiOperation("Get all real estate have ? status of a staff")
    public PaginationResponse<GRealEstateBySellerOrStaffDto> getRealEstatesByStaff(@PathVariable String staffId, @PathVariable String status, @PathVariable Integer page){
        Integer size = 30;
        Page<GRealEstateBySellerOrStaffDto> data = rs.getRealEstatesByStaff(staffId, status, page, size);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getRealEstateAssignStaff/{staffId}/{page}")
    @ApiOperation("Get all real estate assigned of a staff")
    public PaginationResponse<GRealEstateBySellerOrStaffDto> getRealEstateAssignStaff(@PathVariable String staffId, @PathVariable Integer page){
        Integer size = 30;
        Page<GRealEstateBySellerOrStaffDto> data = rs.getRealEstateAssignStaff(staffId, page, size);
        return new PaginationResponse<>(data);
    }

    @GetMapping(value = "/getNumberOfRealEstateByStaff/{staffId}/{status}")
    @ApiOperation("Get number of real estate with ? status of a staff")
    public Integer getNumberOfRealEstateByStaff(@PathVariable String staffId, @PathVariable String status){
        return rs.getNumberOfRealEstateByStaff(staffId, status);
    }

    @GetMapping(value = "/getAllRealEstateType")
    @ApiOperation("Get all real estate type")
    public List<RealEstateTypeDto> getAllRealEstateType(){
        return  rs.getAllRealEstateType();
    }

    @GetMapping(value = "/getAllStaff/{phone}/{fullname}")
    @ApiOperation("Get all staffs")
    public List<StaffDto> getAllStaff(@PathVariable String phone, @PathVariable String fullname){
        return  rs.getAllStaff(phone,fullname);
    }

    @PostMapping(value = "/createRealEstate")
    @ApiOperation("Create a new real estate")
    public HttpStatus createRealEstate(@RequestBody CRealEstateDto cRealEstateDto){
        return rs.createRealEstate(cRealEstateDto) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @PutMapping(value = "/updateRealEstateByManagerAssign")
    @ApiOperation("Update staff_id into real estate when manager assigned")
    public HttpStatus updateRealEstateByManagerAssign(@RequestBody UpdateRealEstateByManagerAssignDto updateRealEstateByManagerAssignDto){
        return rs.updateRealEstateByManagerAssign(updateRealEstateByManagerAssignDto) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @PutMapping(value = "/updateRealEstateStatus")
    @ApiOperation("Update the status of real estate")
    public HttpStatus updateRealEstateStatus(@RequestBody UpdateStatusDto updateStatusDto){
        return rs.updateRealEstateStatus(updateStatusDto) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @PutMapping(value = "/updateBuyerId")
    @ApiOperation("Update the buyerId of real estate")
    public HttpStatus updateBuyerId(@RequestBody UpdateBuyerIdDto updateBuyerIdDto){
        return rs.updateBuyerId(updateBuyerIdDto) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @PutMapping(value = "/updateRealEstateRejected")
    @ApiOperation("Update the real estate when rejected with a reason")
    public HttpStatus updateRealEstateRejected(@RequestBody UpdateRejectedDto updateRejectedDto){
        return rs.updateRealEstateRejected(updateRejectedDto) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "autocomplete/{input}")
    public String callAutocompleteApi(@PathVariable String input) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String url = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input={input}&key={key}&language={language}";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("input", input);
        uriVariables.put("key", "AIzaSyDPzD4tPUGV3HGIiv7fVcWEFEQ0r1AAxwg");
        uriVariables.put("language", "vi");
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class, uriVariables).getBody();
    }

    @RequestMapping(value = "geocoding/{address}")
    public List<GeocodingDto> callGeocodingApi(@PathVariable String address) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address={address}&key={key}";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("address", address);
        uriVariables.put("key", "AIzaSyAk_HxKWrfBT1g9WkfL0gqRIa9HD0d7Q0I");
        WrapperGeometryDto response =  restTemplate.getForObject(url, WrapperGeometryDto.class, uriVariables);
        return response.getResults();
    }

    @RequestMapping(value = "nearbysearch/{location}/{type}")
    public List<NearBySearchDto> callNearBySearchApi(@PathVariable String location, @PathVariable String type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={location}&radius={radius}&type={type}&key={key}&language={language}";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("location", location);
        uriVariables.put("radius", "3000");
        uriVariables.put("type", type);
        uriVariables.put("key", "AIzaSyDPzD4tPUGV3HGIiv7fVcWEFEQ0r1AAxwg");
        uriVariables.put("language", "vi");
        WrapperNearBySearchDto response =  restTemplate.getForObject(url, WrapperNearBySearchDto.class, uriVariables);
        return response.getResults();
    }
}