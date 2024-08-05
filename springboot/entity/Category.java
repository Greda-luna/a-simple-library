package com.example.springboot.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Category {
    private Integer id;
    private String name;
    private String remark;
    private Integer pid;
    @JsonFormat(pattern = "yyy-MM-dd",timezone = "GMT+8")
    private LocalDate createtime;
    @JsonFormat(pattern = "yyy-MM-dd",timezone = "GMT+8")
    private LocalDate updatetime;
}
