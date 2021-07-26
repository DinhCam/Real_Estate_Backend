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
    Page<RealEstateDto> getAllRealEstates(RequestPrams r);
    Page<GRealEstateBySellerOrStaffDto> getRealEstatesBySeller(String sellerId, String status, Integer page, Integer size);
    Page<GRealEstateBySellerOrStaffDto> getRealEstatesActiveBySeller(String sellerId, Integer page, Integer size);
    Page<RealEstateDto> getRealEstatesNotAssign(Integer page, Integer size);
    Page<RealEstateDto> getRealEstatesAssigned(Integer page, Integer size);
    Page<GRealEstateBySellerOrStaffDto> getRealEstatesByStaff(String staffId, String status, Integer page, Integer size);
    Page<GRealEstateBySellerOrStaffDto> getRealEstateAssignStaff(String staffId, Integer page, Integer size);
//    Page<GRealEstateByCensorDto> getRealEstateByCensor(Integer page, Integer size);
    Integer getNumberOfRealEstateByStaff(String staffId, String status);
    List<RealEstateTypeDto> getAllRealEstateType();
    List<StaffDto> getAllStaff();
    RealEstateDetailDto getRealEstateDetailById(int id);
    boolean createRealEstate(CRealEstate cRealEstate);
    boolean updateRealEstateByManagerAssign(UpdateRealEstateByManagerAssign updateRealEstateByManagerAssign);
    boolean updateRealEstateStatus(UpdateStatus updateStatus);
    boolean updateRealEstateRejected(UpdateRejected updateRejected);

    @Service
    @Transactional
    class RealEstateServiceImpl implements RealEstateService {

        @Autowired
        private RealEstateRespo rs;

        @Override
        public Page<RealEstateDto> getAllRealEstates(RequestPrams r) {
            Pageable pageable = PageRequest.of(r.getPage(), r.getSize());
            return rs.getAllRealEstates(r, pageable);
        }

        @Override
        public Page<GRealEstateBySellerOrStaffDto> getRealEstatesBySeller(String sellerId, String status, Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstatesBySeller(sellerId, status, pageable);
        }

        @Override
        public Page<GRealEstateBySellerOrStaffDto> getRealEstatesActiveBySeller(String sellerId, Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstatesActiveBySeller(sellerId, pageable);
        }

        @Override
        public Page<RealEstateDto> getRealEstatesNotAssign(Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstatesNotAssign(pageable);
        }

        @Override
        public Page<RealEstateDto> getRealEstatesAssigned(Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstatesAssigned(pageable);
        }

        @Override
        public Page<GRealEstateBySellerOrStaffDto> getRealEstatesByStaff(String staffId, String status, Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstatesByStaff(staffId, status, pageable);
        }

        @Override
        public Page<GRealEstateBySellerOrStaffDto> getRealEstateAssignStaff(String staffId, Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstateAssignStaff(staffId, pageable);
        }

        @Override
        public Integer getNumberOfRealEstateByStaff(String staffId, String status) {
            return rs.getNumberOfRealEstateByStaff(staffId, status);
        }

//        @Override
//        public Page<GRealEstateByCensorDto> getRealEstateByCensor(Integer page, Integer size) {
//            Pageable pageable = PageRequest.of(page, size);
//            return rs.getRealEstateByCensor(pageable);
//        }

        @Override
        public List<RealEstateTypeDto> getAllRealEstateType() {
            return rs.getAllRealEstateType();
        }

        @Override
        public List<StaffDto> getAllStaff() {
            return rs.getAllStaff();
        }

        @Override
        public RealEstateDetailDto getRealEstateDetailById(int id) {
            RealEstateDetailDto result = rs.getRealEstateDetailById(id);
            rs.updateView(id);
            return result;
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
        public boolean updateRealEstateByManagerAssign(UpdateRealEstateByManagerAssign updateRealEstateByManagerAssign) {
            try {
                rs.updateRealEstateByManagerAssign(updateRealEstateByManagerAssign);
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateStatus(UpdateStatus updateStatus) {
            try {
                rs.updateRealEstateStatus(updateStatus);
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateRejected(UpdateRejected updateRejected) {
            try {
                rs.updateRealEstateRejected(updateRejected);
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }
    }
}