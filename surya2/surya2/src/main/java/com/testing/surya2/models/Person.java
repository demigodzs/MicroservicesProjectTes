package com.testing.surya2.models;

import lombok.Data;

import java.util.Date;

@Data
public class Person {
    private Long id;

    private String name;

    private String email;

    private Boolean isActive;

    private String createdBy;

    private Date createdDate;

    private String  modifyBy;

    private Date modifyDate;
}
