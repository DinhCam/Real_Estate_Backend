package com.gsu21se45.controller;

import com.gsu21se45.common.constant.AppConstant;
import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.UserModel;
import com.gsu21se45.entity.User;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestEntityConstant.URI_ROOT + RestEntityConstant.URI_VERSION + RestEntityConstant.URI_ACCOUNT)
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation(value = "Create a new account")
    @PostMapping(value = RestEntityConstant.URI_CREATE)
    public @ResponseBody
    UserModel create(@RequestBody UserModel model) {
        LOGGER.debug("Begin inside UserController.create()");
        User user = (User) objectMapper.convertToEntity(model, User.class);
        user = userService.create(user);
        model.setId(user.getId());
        model.setPassword("**********");
        LOGGER.debug("End inside UserController.create()");
        return model;
    }

    @ApiOperation(value = "Get user profile")
    @GetMapping(value = RestEntityConstant.URI_PROFILE)
    public @ResponseBody
    UserModel get(@RequestParam(name = RestEntityConstant.USER_ID, required = true) String userId) {
        LOGGER.debug("Begin inside UserController.get()");
        User user = userService.getByUserId(userId);
        UserModel model = user != null ? ((UserModel) objectMapper.convertToDTO(user, UserModel.class)) : null;
        if (model != null) {
            model.setPassword("*************");
        }
        LOGGER.debug("End inside UserController.get()");
        return model;
    }

    @ApiOperation(value = "Update an account")
    @PutMapping(value = RestEntityConstant.URI_UPDATE)
    public @ResponseBody
    void update(@RequestBody UserModel model) {
        LOGGER.debug("Begin inside UserController.update()");
        User user = (User) objectMapper.convertToEntity(model, User.class);
        userService.update(user);
        LOGGER.debug("Begin inside UserController.update()");
    }

    @GetMapping(value = RestEntityConstant.URI_GET + RestEntityConstant.URI_ALL)
    @ApiOperation(value = "Get list account by role with pagination")
    public @ResponseBody
    Page<UserModel> getByRole(@RequestParam(name = "page", defaultValue = AppConstant.DEFAULT_PAGE + "", required = false) int page,
                              @RequestParam(name = "pageSize", defaultValue = AppConstant.DEFAULT_PAGE_SIZE + "", required = false) int pageSize,
                              @RequestParam(name = "role", defaultValue = AppConstant.DEFAULT_ROLE, required = false) String role) {
        LOGGER.debug("Begin inside UserController.getByRole()");
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("fullname"));
        Page<UserModel> users = userService.getByRole(role, pageable).map(entity ->(UserModel) objectMapper.convertToDTO(entity, UserModel.class));
        LOGGER.debug("Begin inside UserController.getByRole()");
        return users;
    }
}
