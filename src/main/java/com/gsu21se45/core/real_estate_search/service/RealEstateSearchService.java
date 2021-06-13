package com.gsu21se45.core.real_estate_search.service;

import com.gsu21se45.common.request.RequestPrams;
import com.gsu21se45.core.real_estate_search.dto.RealEstateDto;
import com.gsu21se45.core.real_estate_search.respo.RealEstateSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface RealEstateSearchService {
   Page<RealEstateDto> getRealEstates(RequestPrams r);

    @Service
    @Transactional
    class RealEstateSearchServiceImpl implements RealEstateSearchService{
        @Autowired
        private RealEstateSearch rs;

        @Override
        public Page<RealEstateDto> getRealEstates(RequestPrams r) {
            Pageable pageable = PageRequest.of(r.getPage(), r.getSize());
            return rs.getRealEstates(r, pageable);
        }
    }
}
