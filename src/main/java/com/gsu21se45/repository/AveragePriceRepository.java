package com.gsu21se45.repository;

import com.gsu21se45.entity.AveragePrice;
import com.gsu21se45.entity.District;
import com.gsu21se45.entity.Street;
import com.gsu21se45.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AveragePriceRepository extends JpaRepository<AveragePrice, Integer> {

    List<AveragePrice> findAveragePricesByStreetAndMonthLessThanEqualAndMonthGreaterThanEqualAndYearAndType(Street street, int endMonth, int startMonth, int year, int type);

    List<AveragePrice> findAveragePricesByDistrictAndMonthLessThanEqualAndMonthGreaterThanEqualAndYearAndType(District district, int endMonth, int startMonth, int year, int type);

    List<AveragePrice> findAveragePricesByWardAndMonthLessThanEqualAndMonthGreaterThanEqualAndYearAndType(Ward ward, int endMonth, int startMonth, int year, int type);

}
