package com.gsu21se45.service.implement;

import com.gsu21se45.entity.Schedule;
import com.gsu21se45.entity.User;
import com.gsu21se45.repository.ScheduleRepository;
import com.gsu21se45.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> findBySellerId(String sellerId) {
        return scheduleRepository.findSchedulesBySeller(new User(sellerId));
    }

    @Transactional
    @Override
    public void save(List<Schedule> entities) {
        for (Schedule entity : entities) {
            scheduleRepository.save(entity);
        }
    }
}
