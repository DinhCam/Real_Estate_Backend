package com.gsu21se45.core.real_estate.service;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.core.real_estate.dto.GRealEstateAssignedStaffDto;
import com.gsu21se45.core.real_estate.dto.RealEstateDto;
import com.gsu21se45.core.real_estate.respo.RealEstateRespo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface RealEstateService {
    Page<RealEstateDto> getRealEstates(RequestPrams r);
    Page<RealEstateDto> getRealEstatesBySellerId(RequestPrams r);
    Page<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(RequestPrams r);

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
        public Page<RealEstateDto> getRealEstatesBySellerId(RequestPrams r) {
            Pageable pageable = PageRequest.of(r.getPage(), r.getSize());
            return rs.getRealEstatesBySellerId(r, pageable);
        }

        @Override
        public Page<GRealEstateAssignedStaffDto> getRealEstateAssignStaff(RequestPrams r) {
            Pageable pageable = PageRequest.of(r.getPage(), r.getSize());
            return rs.getRealEstateAssignStaff(r, pageable);
        }
    }
}