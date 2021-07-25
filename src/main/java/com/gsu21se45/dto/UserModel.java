package com.gsu21se45.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel implements Serializable {

    private String id;
    private int roleId;
    private String username;
    private String password;
    private String avatar;
    private String phone;
    private String fullname;
    private String email;
    private String status;
    private List<WorkingAreaModel> workingAreas;

}
