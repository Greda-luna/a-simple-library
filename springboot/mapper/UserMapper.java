package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {


    List<User> list();

    List<User> listByCondition(BaseRequest baseRequest);

    void save(User user);

    User getById(Integer id);

    void updateById(User obj);

    void deleteById(Integer id);
}
