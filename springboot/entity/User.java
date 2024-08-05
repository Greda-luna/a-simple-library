package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private String username;
    private Integer age;
    private String address;
    private String phone;
    private String sex;
    @JsonFormat(pattern = "yyy-MM-dd",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyy-MM-dd",timezone = "GMT+8")
    private Date updatetime;
}
