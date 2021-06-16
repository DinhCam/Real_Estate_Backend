package com.gsu21se45.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_profile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "avatar", length = 2000)
    private String avatar;

    @Column(name = "phone", length = 255)
    private String phone;

    @Column(name = "fullname", length = 255)
    private String fullname;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "status")
    private boolean status;



}
