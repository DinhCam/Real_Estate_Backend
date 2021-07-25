package com.gsu21se45.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {

    @Id
    @Column(name = "id", length = 255)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "username", length = 255)
    private String username;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "avatar", length = 3000)
    private String avatar;

    @Column(name = "phone", length = 255)
    private String phone;

    @Column(name = "fullname", length = 255)
    private String fullname;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "status", length = 255)
    private String status;

    @OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name="staff_id", updatable = false)
    private List<WorkingArea> workingAreas;

    public User(String id) {
        this.id = id;
    }
}
