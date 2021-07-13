package com.gsu21se45.service.implement;

import com.gsu21se45.common.constant.AppConstant;
import com.gsu21se45.entity.User;
import com.gsu21se45.repository.UserRepository;
import com.gsu21se45.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public User getByUserId(String userId) {
        return repository.getUserById(userId);
    }

    @Override
    public void update(User user) {
        repository.saveAndFlush(user);
    }

    @Override
    public Page<User> getByRole(String role, Pageable pageable) {
        Page<User> users = null;
        if (role.equalsIgnoreCase(AppConstant.DEFAULT_ROLE)) {
            users = repository.findAllExceptAdmin(AppConstant.ADMIN_ROLE, pageable);
        } else {
            users = repository.findByRole(role, pageable);
        }
        return users;
    }
}
