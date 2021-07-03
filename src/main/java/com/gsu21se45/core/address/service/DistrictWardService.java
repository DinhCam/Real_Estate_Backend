package com.gsu21se45.core.address.service;

import com.gsu21se45.core.address.dto.DistrictDto;
import com.gsu21se45.core.address.dto.WardDto;
import com.gsu21se45.core.address.respo.DistrictWardRespo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DistrictWardService {

    @Autowired
    private DistrictWardRespo districtWardRespo;

    public boolean insertDistrictWard(List<DistrictDto> districtDtos){
        try {
            for (DistrictDto d:districtDtos) {
                districtWardRespo.insertDistrict(d);
                for (WardDto w:d.getWards()) {
                    districtWardRespo.insertWard(w, d);
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
