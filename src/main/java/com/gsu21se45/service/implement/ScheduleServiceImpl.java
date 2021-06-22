package com.gsu21se45.service.implement;

import com.gsu21se45.entity.Schedule;
import com.gsu21se45.entity.User;
import com.gsu21se45.repository.ScheduleRepository;
import com.gsu21se45.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> findBySellerId(String sellerId) {
        return scheduleRepository.findSchedulesBySeller(new User(sellerId));
    }
}
