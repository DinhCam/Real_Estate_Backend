package com.gsu21se45.repository;

import com.gsu21se45.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    Page<User> findByRole(@Param(value = "role") String role, Pageable pageable);


    @Query(value = "select distinct u.* " +
            "from (`user` u join working_area w " +
            "on u.fullname " +
            "like %:name% " +
            "and u.id = w.staff_id " +
            "and u.role_id = :roleId " +
            "and w.district_id = :districtId) " +
            "left join (select count(t.staff_id) as c, t.staff_id " +
            "from `transaction` t " +
            "group by t.staff_id ) as ts " +
            "on ts.staff_id = u.id order by ts.c desc",
            nativeQuery = true)
    List<User> getByNameAndDistrictIdAndRoleId(@Param(value = "name") String name,
                                               @Param(value = "districtId") int districtId,
                                               @Param(value = "roleId") int roleId);

    @Query(value = "select distinct u.* " +
            "from (`user` u join working_area w " +
            "on u.fullname " +
            "like %:name% " +
            "and u.id = w.staff_id " +
            "and u.role_id = :roleId) " +
            "left join (select count(t.staff_id) as c, t.staff_id " +
            "from `transaction` t " +
            "group by t.staff_id ) as ts " +
            "on ts.staff_id = u.id order by ts.c desc",
            nativeQuery = true)
    List<User> getByNameAndRoleId(@Param(value = "name") String name,
                                  @Param(value = "roleId") int roleId);

    //getTransactionNumById
    @Query(value = "select count(t.staff_id) from `transaction` t where t.staff_id = :staffId",
            nativeQuery = true)
    int getTransactionNumById(@Param(value = "staffId") String staffId);


}
