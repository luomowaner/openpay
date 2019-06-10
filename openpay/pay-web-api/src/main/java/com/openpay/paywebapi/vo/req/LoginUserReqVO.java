package com.openpay.paywebapi.vo.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUserReqVO implements Serializable {
    private static final long serialVersionUID = -4906715914294033269L;

    private String Id;

    private String loginName;

    private String password;
}
