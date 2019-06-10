package com.openpay.paywebapi.vo.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUserRespVO implements Serializable {
    private static final long serialVersionUID = -4906715914294033269L;

    private String Id;

    private String loginName;

    private String fullName;

    private String token;
}
