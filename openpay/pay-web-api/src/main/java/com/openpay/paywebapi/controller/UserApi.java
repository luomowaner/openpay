package com.openpay.paywebapi.controller;


import com.openpay.payserviceapi.dto.LoginUserDto;
import com.openpay.paywebapi.annotation.PassToken;
import com.openpay.paywebapi.service.TokenService;
import com.openpay.paywebapi.service.UserService;
import com.openpay.paywebapi.vo.ApiResponseBody;
import com.openpay.paywebapi.vo.req.LoginUserReqVO;
import com.openpay.paywebapi.vo.resp.LoginUserRespVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api("登陆api")
@RestController
@RequestMapping("/openpay/user")
@Slf4j
public class UserApi {

    @Resource
    UserService userService;

    @Resource
    TokenService tokenService;

    @PostMapping("/login")
    @PassToken
    public ApiResponseBody<LoginUserRespVO> login(LoginUserReqVO loginUser) {
        ApiResponseBody<LoginUserRespVO> apiResponseBody = new ApiResponseBody(ApiResponseBody.ResponseCodeEnum.ERROR_500.getCode(), ApiResponseBody.ResponseCodeEnum.ERROR_500.getMsg(), null);
        try {
            LoginUserDto loginUserDto = userService.findUserLoginName(loginUser.getLoginName());
            if (null == loginUserDto) {
                apiResponseBody.setResponseCode(ApiResponseBody.ResponseCodeEnum.ERROR_402.getCode());
                apiResponseBody.setResponseMsg("用户不存在!");
            } else {
                //TODO 密码加密算法待定
                if (!loginUserDto.getPassword().equals(loginUser.getPassword())) {
                    apiResponseBody.setResponseCode(ApiResponseBody.ResponseCodeEnum.ERROR_402.getCode());
                    apiResponseBody.setResponseMsg("密码错误!");
                }else {
                    LoginUserRespVO loginUserRespVO = new LoginUserRespVO();
                    //TODO 用户其他信息
                    String token = tokenService.getToken(loginUserDto);
                    loginUserRespVO.setToken(token);
                    apiResponseBody.setResponseCode(ApiResponseBody.ResponseCodeEnum.OK.getCode());
                    apiResponseBody.setResponseMsg(ApiResponseBody.ResponseCodeEnum.OK.getMsg());
                    apiResponseBody.setData(loginUserRespVO);
                }
            }
        } catch (Exception e) {
            log.error("登陆失败:{}", e);
        }
        return apiResponseBody;
    }

}
