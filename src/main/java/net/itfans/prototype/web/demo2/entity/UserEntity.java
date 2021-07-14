package net.itfans.prototype.web.demo2.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserEntity {

    private String userId;

    private String password;

    private Timestamp insertDate;

    private Timestamp updateDate;
}
