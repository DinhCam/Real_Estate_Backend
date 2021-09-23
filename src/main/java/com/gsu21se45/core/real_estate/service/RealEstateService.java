package com.gsu21se45.core.real_estate.service;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.core.real_estate.dto.*;
import com.gsu21se45.core.real_estate.repository.RealEstateRepository;
import com.gsu21se45.util.filterHelper.OrderFilterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

public interface RealEstateService {
    Page<RealEstateDto> getRealEstate(RequestPrams r);
    Page<RealEstateDto> getRealEstateByTitleAddress(String search, Integer page, Integer size);
    Page<GRealEstateBySellerOrStaffDto> getRealEstatesBySeller(String sellerId, String status, Integer page, Integer size);
    Page<GRealEstateByDataentryDto> getRealEstatesByDataentry(String dataentryId, String status, Integer page, Integer size);
    Page<GRealEstateByDataentryDto> getRealEstatesNotVerifyByDataentry(Integer page, Integer size);
    Page<GRealEstateByDataentryDto> getRealEstatesVerifyByDataentry(String dataentryId, Integer page, Integer size);
    Page<GRealEstateBySellerOrStaffDto> getRealEstatesActiveBySeller(String sellerId, Integer page, Integer size);
    Page<RealEstateDto> getRealEstatesNotAssign(Integer page, Integer size);
    Page<RealEstateDto> getRealEstatesAssigned(Integer page, Integer size);
    Page<GRealEstateBySellerOrStaffDto> getRealEstatesByManager(Integer page, Integer size);
    Page<GRealEstateBySellerOrStaffDto> getRealEstatesByStaff(String staffId, String status, Integer page, Integer size);
    Page<GRealEstateBySellerOrStaffDto> getRealEstateAssignStaff(String staffId, Integer page, Integer size);
    Integer getNumberOfRealEstateByStaff(String staffId, String status);
    List<RealEstateTypeDto> getAllRealEstateType();
    List<StaffDto> getAllStaff(String phone, String fullname);
    RealEstateDetailDto getRealEstateDetailById(int id);
    boolean createRealEstate(CRealEstateDto cRealEstateDto);
    boolean updateRealEstate(UpdateRealEstateDto updateRealEstateDto);
    boolean updateRealEstateByManagerAssign(UpdateRealEstateByManagerAssignDto updateRealEstateByManagerAssignDto);
    boolean updateRealEstateStatus(UpdateStatusDto updateStatusDto);
    boolean updateRealEstateDetailLatLng(UpdateLatLngDto updateLatLngDto);
    boolean updateBuyerId(UpdateBuyerIdDto updateBuyerIdDto);
    boolean updateRealEstateRejected(UpdateRejectedDto updateRejectedDto);

    @Service
    @Transactional
    class RealEstateServiceImpl implements RealEstateService {

        @Autowired
        private RealEstateRepository rs;

        @Override
        public Page<RealEstateDto> getRealEstate(RequestPrams r) {
            List<String> columnsAllow = Arrays.asList(
                    "r.view",
                    "rd.price",
                    "rd.area",
                    "r.create_at"
            );
            OrderFilterHelper orderFilterHelperImpl = new OrderFilterHelper(r.getSort(), columnsAllow);
            Pageable pageable = PageRequest.of(r.getPage(), r.getSize(), orderFilterHelperImpl.getSort());
            return rs.getRealEstate(r, pageable);
        }

        @Override
        public Page<RealEstateDto> getRealEstateByTitleAddress(String search, Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page,size);
            return rs.getRealEstateByTitleAddress(search, pageable);
        }

        @Override
        public Page<GRealEstateBySellerOrStaffDto> getRealEstatesBySeller(String sellerId, String status, Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstatesBySeller(sellerId, status, pageable);
        }

        @Override
        public Page<GRealEstateByDataentryDto> getRealEstatesByDataentry(String dataentryId, String status, Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstatesByDataentry(dataentryId, status, pageable);
        }

        @Override
        public Page<GRealEstateByDataentryDto> getRealEstatesNotVerifyByDataentry(Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstatesNotVerifyByDataentry(pageable);
        }

        @Override
        public Page<GRealEstateByDataentryDto> getRealEstatesVerifyByDataentry(String dataentryId, Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstatesVerifyByDataentry(dataentryId, pageable);
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
        public Page<GRealEstateBySellerOrStaffDto> getRealEstatesByManager(Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
            return rs.getRealEstatesByManager(pageable);
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

        @Override
        public List<RealEstateTypeDto> getAllRealEstateType() {
            return rs.getAllRealEstateType();
        }

        @Override
        public List<StaffDto> getAllStaff(String phone, String fullname) {
            return rs.getAllStaff(phone,fullname);
        }

        @Override
        public RealEstateDetailDto getRealEstateDetailById(int id) {
            RealEstateDetailDto result = rs.getRealEstateDetailById(id);
            rs.updateView(id);
            return result;
        }

        @Override
        public boolean createRealEstate(CRealEstateDto cRealEstateDto) {
            try {
                rs.createRealEstate(cRealEstateDto);
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstate(UpdateRealEstateDto updateRealEstateDto) {
            try {
                rs.updateRealEstate(updateRealEstateDto);
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateByManagerAssign(UpdateRealEstateByManagerAssignDto updateRealEstateByManagerAssignDto) {
            try {
                rs.updateRealEstateByManagerAssign(updateRealEstateByManagerAssignDto);
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateStatus(UpdateStatusDto updateStatusDto) {
            try {
                rs.updateRealEstateStatus(updateStatusDto);
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateDetailLatLng(UpdateLatLngDto updateLatLngDto) {
            try {
                rs.updateRealEstateDetailLatLng(updateLatLngDto);
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateBuyerId(UpdateBuyerIdDto updateBuyerIdDto) {
            try {
                rs.updateBuyerId(updateBuyerIdDto);
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public boolean updateRealEstateRejected(UpdateRejectedDto updateRejectedDto) {
            try {
                rs.updateRealEstateRejected(updateRejectedDto);
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }
    }
}