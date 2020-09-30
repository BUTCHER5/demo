package com.jingyx.entity;

import lombok.Data;

@Data
public class Account {
    private Integer id;

    private String accountNum;

    private Byte accountType;

    private Float quota;

    private Byte costType;

    private String remark;
}