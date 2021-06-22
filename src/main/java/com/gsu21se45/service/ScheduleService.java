package com.gsu21se45.service;

import com.gsu21se45.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    List<Schedule> findBySellerId(String sellerId);
}
