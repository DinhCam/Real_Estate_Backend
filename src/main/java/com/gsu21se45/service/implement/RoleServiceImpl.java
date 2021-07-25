package com.gsu21se45.service.implement;

import com.gsu21se45.repository.RoleRepository;
import com.gsu21se45.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public int getIdByName(String name) {
        return repository.getIdByName(name);
    }
}
