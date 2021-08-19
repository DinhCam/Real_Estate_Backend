package com.gsu21se45.service;

import com.gsu21se45.entity.District;

import java.util.List;

public interface DistrictService {

    List<District> getAll();

    public String getDistrictNameByDistrictId(int id);
}
