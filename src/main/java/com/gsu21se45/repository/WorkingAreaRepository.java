package com.gsu21se45.repository;

import com.gsu21se45.entity.User;
import com.gsu21se45.entity.WorkingArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingAreaRepository extends JpaRepository<WorkingArea, Integer> {

    void deleteAllByStaff_Id(String staffId);

}
