package com.gsu21se45.repository;

import com.gsu21se45.entity.Schedule;
import com.gsu21se45.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findSchedulesBySeller(User seller);
}
