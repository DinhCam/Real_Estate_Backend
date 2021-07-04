package com.gsu21se45.core.address.service;

import com.gsu21se45.core.address.dto.DistrictDto;
import com.gsu21se45.core.address.dto.WardDto;
import com.gsu21se45.core.address.respo.AddressRespo;
import com.gsu21se45.core.address.dto.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface AddressService {
    boolean insertDistrictWard(List<DistrictDto> districtDtos);
    List<AddressDto> getAddress();

    @Service
    @Transactional
    class AddressServiceImpl implements AddressService{
        @Autowired
        private AddressRespo addressRespo;

        @Override
        public boolean insertDistrictWard(List<DistrictDto> districtDtos){
            try {
                for (DistrictDto d:districtDtos) {
                    addressRespo.insertDistrict(d);
                    for (WardDto w:d.getWards()) {
                        addressRespo.insertWard(w, d);
                    }
                }
            } catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public List<AddressDto> getAddress() {
            return addressRespo.getAddress();
        }
    }
}
