package com.felixhjx.springboot.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.felixhjx.springboot.common.Constants;
import com.felixhjx.springboot.common.Result;
import com.felixhjx.springboot.controller.dto.UserDTO;
import com.felixhjx.springboot.entity.User;
import com.felixhjx.springboot.exception.ServiceException;
import com.felixhjx.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService extends ServiceImpl<UserMapper,User> {

    private static final Log LOG = Log.get();


    @Transactional
    //使用Mybatis-Plus提供的方法实现CRUD
    public boolean saveUser(User user){
        //传入user的id为空是一个新用户
        if(user.getId()==null){
            return save(user);
        }else {
            return updateById(user);
        }
        //saveOrUpdate(user);
    }

    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if(one!=null){
            BeanUtil.copyProperties(one,userDTO,true);
            return userDTO;
        } else{
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }

    }

    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if(one==null){
            one=new User();
            BeanUtil.copyProperties(userDTO,one,true);
            save(one);
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
        return null;
    }

    //function for get userInfo
    private User getUserInfo(UserDTO userDTO ){
        //数据库查询
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        User one;
        try{
            //业务异常
            one = getOne(queryWrapper);
            //UserDTO是User属性的一部分使用copy方法
        }catch(Exception e){
            //自定义异常
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统异常");
        }
        return one;
    }
}
