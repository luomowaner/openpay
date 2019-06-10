package com.openpay.paywebapi.interceptor;

import com.openpay.paywebapi.vo.ApiResponseBody;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jinbin
 * @date 2018-07-08 22:37
 */
@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResponseBody<String> handleException(Exception e) {
        ApiResponseBody<String> apiResponseBody = new ApiResponseBody(ApiResponseBody.ResponseCodeEnum.ERROR_500.getCode(), ApiResponseBody.ResponseCodeEnum.ERROR_500.getMsg(), null);
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
            apiResponseBody.setResponseMsg(msg);
        }
        return apiResponseBody;
    }
}
