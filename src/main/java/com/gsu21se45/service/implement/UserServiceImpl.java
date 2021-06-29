package com.gsu21se45.service.implement;

import com.gsu21se45.entity.User;
import com.gsu21se45.repository.UserRepository;
import com.gsu21se45.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return repository.getUserByUsername(username);
    }

    @Override
    public User getByUserId(String userId) {return repository.getUserById(userId); }

    @Override
    public void update(User user) {
        repository.saveAndFlush(user);
    }
}
