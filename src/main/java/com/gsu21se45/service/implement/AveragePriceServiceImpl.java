package com.gsu21se45.service.implement;

import com.gsu21se45.entity.AveragePrice;
import com.gsu21se45.entity.District;
import com.gsu21se45.entity.Street;
import com.gsu21se45.entity.Ward;
import com.gsu21se45.repository.AveragePriceRepository;
import com.gsu21se45.service.AveragePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AveragePriceServiceImpl implements AveragePriceService {

    @Autowired
    private AveragePriceRepository repository;

    @Override
    public List<AveragePrice> getByStreet(int id, int month, int year, int reTypeId) {
        return repository.findAveragePricesByStreetAndMonthLessThanEqualAndMonthGreaterThanEqualAndYearAndType(new Street(id), month, month - 5, year, reTypeId);
    }

    @Override
    public List<AveragePrice> getByWard(int id, int month, int year, int reTypeId) {
        return repository.findAveragePricesByWardAndMonthLessThanEqualAndMonthGreaterThanEqualAndYearAndType(new Ward(id), month, month - 5, year, reTypeId);
    }

    @Override
    public List<AveragePrice> getByDistrict(int id, int month, int year, int reTypeId) {
        return repository.findAveragePricesByDistrictAndMonthLessThanEqualAndMonthGreaterThanEqualAndYearAndType(new District(id), month, month - 5, year, reTypeId);
    }

    private int calculatorYear(int month, int year){
        if (month < 6){
            return year - 1;
        }
        return year;
    }

    private int calculatorMonth(int month){
        if(month < 6){
            month += 7;
        }
        return month;
    }
}
