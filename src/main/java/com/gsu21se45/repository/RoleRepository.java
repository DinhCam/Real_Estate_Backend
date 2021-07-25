package com.gsu21se45.repository;

import com.gsu21se45.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "select id from role where name = :name",
            nativeQuery = true)
    int getIdByName(@Param(value = "name") String name);
}
