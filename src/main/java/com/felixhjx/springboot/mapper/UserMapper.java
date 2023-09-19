package com.felixhjx.springboot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.felixhjx.springboot.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;


import java.util.List;

@Mapper
//User entity mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from sys_user")
    List<User> findAll();

    @Insert("insert into sys_user(username,password,nickname,email,phone,address)" +
            "VALUES " +
            "(#{username},#{password},#{nickname},#{email},#{phone},#{address})")
    int insertUser(User user);

//    @Select("select * from sys_user limit #{pageNum}, #{pageSize}")
//    List<User> selectPage(Integer pageNum, Integer pageSize);

    @Select("select count(*) from sys_user")
    Integer selectTotal();

    int update(User user);
}
