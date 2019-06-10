package com.openpay.payserviceapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUserDto implements Serializable {
    private static final long serialVersionUID = -8586257154950889646L;

    private String Id;

    private String loginName;

    private String fullName;

    private String password;
}
