package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Admin {
    private Integer id;
    private String email;
    private String username;
    private String phone;
    private String password;
    @JsonFormat(pattern = "yyy-MM-dd",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyy-MM-dd",timezone = "GMT+8")
    private Date updatetime;
}
