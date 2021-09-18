package com.gsu21se45.service.implement;

import com.gsu21se45.entity.User;
import com.gsu21se45.entity.WorkingArea;
import com.gsu21se45.repository.WorkingAreaRepository;
import com.gsu21se45.service.WorkingAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WorkingAreaServiceImpl implements WorkingAreaService {

    @Autowired
    private WorkingAreaRepository repository;

    @Override
    public void process(List<WorkingArea> list) {
        repository.saveAll(list);
    }

    @Override
    @Transactional
    public void delete(String staffId) {
        repository.deleteAllByStaff_Id(staffId);
    }

}
