package com.jingyx.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SystemLog {
    private Long logId;

    private String operator;

    private String operAction;

    private String operResut;

    private String operInfo;

    private Date createTime;
}