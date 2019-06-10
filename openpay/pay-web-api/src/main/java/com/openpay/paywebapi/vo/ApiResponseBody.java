package com.openpay.paywebapi.vo;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Rest API 统一返回结构
 *
 * @param <T>
 */
@NoArgsConstructor
@Data
public class ApiResponseBody<T> {
    public enum ResponseCodeEnum {
        /**
         * response
         */
        OK("200", "成功"),
        ERROR_400("400", "数据错误"),
        ERROR_402("402", "登录失败"),
        ERROR_403("403", "用户未登录"),
        ERROR_500("500", "调用失败");
        private String code;
        private String msg;

        ResponseCodeEnum(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    private String responseCode;

    private String responseMsg;

    private T data;

    public ApiResponseBody(T data) {
        this(String.valueOf(HttpStatus.OK.value()), "成功", data);
    }

    public ApiResponseBody(String responseCode, String responseMsg) {
        this(responseCode, responseMsg, null);
    }

    public ApiResponseBody(String responseCode, String responseMsg, T data) {
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
        this.data = data;
    }
}
