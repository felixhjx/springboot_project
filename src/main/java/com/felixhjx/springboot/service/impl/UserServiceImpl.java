package com.felixhjx.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.felixhjx.springboot.controller.dto.UserDTO;
import com.felixhjx.springboot.entity.User;
import com.felixhjx.springboot.mapper.UserMapper;
import com.felixhjx.springboot.service.IUserService;

public class UserServiceImpl extends ServiceImpl<UserMapper, User>  {

    public boolean login(UserDTO userDTO) {
        //数据库查询
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        User one = getOne(queryWrapper);
        return one!=null;
    }

//    @Override
//    public boolean saveOrUpdate(User entity) {
//        return super.saveOrUpdate(entity);
//    }
}
