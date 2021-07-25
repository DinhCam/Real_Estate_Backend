package com.gsu21se45.service.implement;

import com.gsu21se45.dto.AddressModel;
import com.gsu21se45.repository.StreetWardRepository;
import com.gsu21se45.service.StreetWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreetWardServiceImpl implements StreetWardService {

    @Autowired
    private StreetWardRepository repository;

    @Override
    public AddressModel getById(int id) {
        String[] address = repository.getById(id).split(",");
        AddressModel model = new AddressModel(address[0], address[1], address[2]);
        return model;
    }
}
