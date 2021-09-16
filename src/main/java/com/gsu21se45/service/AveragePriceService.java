package com.gsu21se45.service;

import com.gsu21se45.entity.AveragePrice;

import java.util.List;

public interface AveragePriceService {

//    List<AveragePrice> getByStreet(int id, int month, int year, int reTypeId);

    List<AveragePrice> getByWard(int id, int month, int year, int reTypeId);

    List<AveragePrice> getByDistrict(int id, int month, int year, int reTypeId);
}
