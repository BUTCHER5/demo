package com.jingyx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Account {
    private Integer id;

    private String accountNum;

    private Byte accountType;

    private Float quota;

    private Byte costType;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}