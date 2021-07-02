package com.gsu21se45.core.real_estate.service;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.core.real_estate.dto.*;
import com.gsu21se45.core.real_estate.respo.RealEstateRespo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface RealEstateService {
    Page<RealEstateDto> getRealEstates(RequestPrams r);
    Page<RealEstateDto> getRealEstatesBySellerId(String sellerId, Integer page, Integer size);
    Page<RealEstateDto> getRealEstatesInactive(Integer page, Integer size);
    Page<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(String staffId, Integer page, Integer size);
    List<RealEstateTypeDto> getAllRealEstateType();
    RealEstateDto getRealEstateById(int id);
    List<AddressDto> getAddress();
    boolean createRealEstate(CRealEstate cRealEstate);
    boolean updateRealEstateStatusByStaffAssign(UpdateStatus updateStatus);

    @Service
    @Transactional
    class RealEstateServiceImpl implements RealEstateService {

        @Autowired
        private RealEstateRespo rs;

        @Override
        public Page<RealEstateDto> getRealEstates(RequestPrams r) {
            Pageable pageable = PageRequest.of(r.getPage(), r.getSize());
            return rs.getRealEstates(r, pageable);
        }

        @Override
        public Page<RealEstateDto> getRealEstatesBySellerId(String sellerId, Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstatesBySellerId(sellerId, pageable);
        }

        @Override
        public Page<RealEstateDto> getRealEstatesInactive(Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstatesInactive(pageable);
        }

        @Override
        public Page<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(String staffId, Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstateAssignStaff(staffId, pageable);
        }

        @Override
        public List<RealEstateTypeDto> getAllRealEstateType() {
            return rs.getAllRealEstateType();
        }

        @Override
        public RealEstateDto getRealEstateById(int id) {
            return rs.getRealEstateById(id);
        }

        @Override
        public List<AddressDto> getAddress() {
            return rs.getAddress();
        }

        @Override
        public boolean createRealEstate(CRealEstate cRealEstate) {
            try {
                rs.createRealEstate(cRealEstate);
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateStatusByStaffAssign(UpdateStatus updateStatus) {
            try {
                rs.updateRealEstateStatusByStaffAssign(updateStatus);
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }
    }
}