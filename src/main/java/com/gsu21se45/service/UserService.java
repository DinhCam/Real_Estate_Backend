package com.gsu21se45.service;

import com.gsu21se45.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User create(User user);

    User getByUsername(String username);

    User getByUserId(String userId);

    void update(User user);

    Page<User> getByRole(String role, Pageable pageable);
}
