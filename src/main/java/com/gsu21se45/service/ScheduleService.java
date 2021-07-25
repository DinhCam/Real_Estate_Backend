package com.gsu21se45.service;

import com.gsu21se45.entity.Schedule;
import com.gsu21se45.entity.User;

import java.util.List;

public interface ScheduleService {

    List<Schedule> findBySellerId(String sellerId);

    void save(List<Schedule> entities);

    void delete(User seller);
}
