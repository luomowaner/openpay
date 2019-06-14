package com.openpay.paywebapi.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.openpay.payserviceapi.dto.LoginUserDto;
import com.openpay.paywebapi.vo.req.LoginUserReqVO;
import com.openpay.paywebapi.vo.resp.LoginUserRespVO;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Reference

    public LoginUserReqVO findUserById(String userId) {
        return null;
    }

    public LoginUserDto findUserLoginName(String loginName) {
        return null;
    }
}
