package com.felixhjx.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.felixhjx.springboot.controller.dto.UserDTO;
import com.felixhjx.springboot.entity.User;

public interface IUserService extends IService<User> {
    boolean login(UserDTO userDTO);

//    public boolean saveOrUpdate(User user);
}
