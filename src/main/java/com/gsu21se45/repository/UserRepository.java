package com.gsu21se45.repository;

import com.gsu21se45.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByUsername(String username);

    User getUserById(String userId);

    @Query(value = "Select u.id, u.username, u.role_id, u.password, u.avatar, u.phone, u.fullname, u.email, u.status " +
            "from user u join role r " +
            "on u.role_id = r.id and r.name != :role",
            countQuery = "select count(u.id) " +
                    "from user u join role r " +
                    "on u.role_id = r.id and r.name != :role",
            nativeQuery = true)
    Page<User> findAllExceptAdmin(@Param(value = "role") String role, Pageable pageable);

    @Query(value = "Select u.id, u.username, u.role_id, u.password, u.avatar, u.phone, u.fullname, u.email, u.status " +
            "from user u join role r " +
            "on u.role_id = r.id and r.name = :role",
            countQuery = "select count(u.id) " +
                    "from user u join role r " +
                    "on u.role_id = r.id and r.name = :role",
            nativeQuery = true)
    Page<User> findByRole(String role, Pageable pageable);
}
