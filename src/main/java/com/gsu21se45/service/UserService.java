package com.gsu21se45.service;

import com.gsu21se45.entity.User;

public interface UserService {

    User create(User user);

    User getByUsername(String username);

    User getByUserId(String userId);

    void update(User user);
}
