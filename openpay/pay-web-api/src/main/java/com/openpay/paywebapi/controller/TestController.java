package com.openpay.paywebapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("测试类")
@RestController
@RequestMapping("/test/controller")
public class TestController {

    /**
     * test
     * @return
     */
    @ApiOperation("测试方法")
    @GetMapping("/test")
    public String test(){
        return "hello";
    }
}
