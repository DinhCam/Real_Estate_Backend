package com.gsu21se45.service.implement;

import com.gsu21se45.common.constant.AppConstant;
import com.gsu21se45.entity.AveragePrice;
import com.gsu21se45.entity.District;
import com.gsu21se45.entity.Street;
import com.gsu21se45.entity.Ward;
import com.gsu21se45.repository.AveragePriceRepository;
import com.gsu21se45.service.AveragePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AveragePriceServiceImpl implements AveragePriceService {

    @Autowired
    private AveragePriceRepository repository;

    @Override
    public List<AveragePrice> getByStreet(int id, int month, int year, int reTypeId) {
        List<Integer> time = calculateYear(month, year);
        List<AveragePrice> list = null;
        Street street = new Street(id);
        if (month < AppConstant.MONTH_OF_STATISTIC) {
            list = repository.findAveragePricesByStreetAndMonthLessThanEqualAndMonthGreaterThanAndYearAndTypeOrderByMonth(street, 12, time.get(0), time.get(1), reTypeId);
            List<AveragePrice> additional = repository.findAveragePricesByStreetAndMonthLessThanEqualAndMonthGreaterThanAndYearAndTypeOrderByMonth(street, month, 0, year, reTypeId);
            for (AveragePrice averagePrice : additional) {
                list.add(averagePrice);
            }
        } else {
            list = repository.findAveragePricesByStreetAndMonthLessThanEqualAndMonthGreaterThanAndYearAndTypeOrderByMonth(street, month, time.get(0), year, reTypeId);
        }
        return list;
    }

    @Override
    public List<AveragePrice> getByWard(int id, int month, int year, int reTypeId) {
        List<Integer> time = calculateYear(month, year);
        List<AveragePrice> list = null;
        Ward ward = new Ward(id);
        if (month < AppConstant.MONTH_OF_STATISTIC) {
            list = repository.findAveragePricesByWardAndMonthLessThanEqualAndMonthGreaterThanAndYearAndTypeOrderByMonth(ward, 12, time.get(0), time.get(1), reTypeId);
            List<AveragePrice> additional = repository.findAveragePricesByWardAndMonthLessThanEqualAndMonthGreaterThanAndYearAndTypeOrderByMonth(ward, month, 0, year, reTypeId);
            for (AveragePrice averagePrice : additional) {
                list.add(averagePrice);
            }
        } else {
            list = repository.findAveragePricesByWardAndMonthLessThanEqualAndMonthGreaterThanAndYearAndTypeOrderByMonth(ward, month, time.get(0), year, reTypeId);
        }
        return list;
    }

    @Override
    public List<AveragePrice> getByDistrict(int id, int month, int year, int reTypeId) {
        List<Integer> time = calculateYear(month, year);
        List<AveragePrice> list = null;
        District district = new District(id);
        if (month < AppConstant.MONTH_OF_STATISTIC) {
            list = repository.findAveragePricesByDistrictAndMonthLessThanEqualAndMonthGreaterThanAndYearAndTypeOrderByMonth(district, 12, time.get(0), time.get(1), reTypeId);
            List<AveragePrice> additional = repository.findAveragePricesByDistrictAndMonthLessThanEqualAndMonthGreaterThanAndYearAndTypeOrderByMonth(district, month, 0, year, reTypeId);
            for (AveragePrice averagePrice : additional) {
                list.add(averagePrice);
            }
        } else {
            list = repository.findAveragePricesByDistrictAndMonthLessThanEqualAndMonthGreaterThanAndYearAndTypeOrderByMonth(district, month, time.get(0), year, reTypeId);
        }
        return list;
    }

    private List<Integer> calculateYear(int month, int year) {
        List<Integer> list = new ArrayList<>();
        int startMonth = month - AppConstant.MONTH_OF_STATISTIC;
        if (month < AppConstant.MONTH_OF_STATISTIC) {
            year -= 1;
            startMonth = month - AppConstant.MONTH_OF_STATISTIC + 12;
        }
        list.add(startMonth);
        list.add(year);
        return list;
    }
}
