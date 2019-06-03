package com.openpay.payserviceimpl.service;

import com.openpay.payserviceapi.ITestService;

public class TestServiceImpl implements ITestService {
    @Override
    public String getTest() {
        return "hello";
    }
}
