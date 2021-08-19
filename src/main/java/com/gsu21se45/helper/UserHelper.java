package com.gsu21se45.helper;

import com.gsu21se45.dto.StaffModel;
import com.gsu21se45.dto.UserModel;
import com.gsu21se45.dto.WorkingAreaModel;
import com.gsu21se45.entity.User;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.DistrictService;
import com.gsu21se45.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserHelper {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    public UserModel getUser(UserModel model) {
        for (WorkingAreaModel models : model.getWorkingAreas()) {
            int districtId = models.getDistrictId();
            String districtName = districtService.getDistrictNameByDistrictId(districtId);
            models.setDistrictName(districtName);
        }
        return model;
    }

    public List<UserModel> getUsers(List<User> users) {
        List<UserModel> userModels = users != null ? objectMapper.convertToListDTO(users, UserModel.class) : new ArrayList<>();

        for ( UserModel userModel : userModels) {

            for (WorkingAreaModel userls : userModel.getWorkingAreas()) {
                int districtId = userls.getDistrictId();
                String districtName = districtService.getDistrictNameByDistrictId(districtId);
                userls.setDistrictName(districtName);
            }

        }

        return userModels;
    }

    public List<StaffModel> getStaffs(List<User> users) {
        List<StaffModel> staffModels = users != null ? objectMapper.convertToListDTO(users, StaffModel.class) : new ArrayList<>();

            for (StaffModel staffModel : staffModels) {

                String staffId = staffModel.getId();
                int transactionNum = userService.getTransactionNum(staffId);
                staffModel.setTransactionNum(transactionNum);

            }



        return staffModels;
    }

}
