package com.openpay.paywebapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.openpay.payserviceapi.dto.LoginUserDto;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String getToken(LoginUserDto loginUserDto) {
        String token = "";
        token = JWT.create().withAudience(loginUserDto.getId())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(loginUserDto.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}
