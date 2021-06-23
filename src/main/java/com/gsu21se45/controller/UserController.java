package com.gsu21se45.controller;

import com.gsu21se45.common.constant.RestEntityConstant;
import com.gsu21se45.dto.UserModel;
import com.gsu21se45.entity.User;
import com.gsu21se45.mapper.ObjectMapper;
import com.gsu21se45.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    UserModel get(@RequestParam(name = RestEntityConstant.USERNAME, required = true) String username) {
        LOGGER.debug("Begin inside UserController.get()");
        User user = userService.getByUsername(username);
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
}
