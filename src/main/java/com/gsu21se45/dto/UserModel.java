package com.gsu21se45.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements Serializable {

    private String id;
    private int roleId;
//    private UserProfileModel userProfile;
    private String username;
    private String password;
    private String avatar;
    private String phone;
    private String fullname;
    private String email;
    private String status;

}
