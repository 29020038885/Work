package com.example.controller;


import com.example.common.Result;
import com.example.entity.SysAccount;
import com.example.exception.CustomException;
import com.example.service.SysAccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//对外提供接口
@RestController
public class WebController {

    @Resource
    private SysAccountService sysAccountService;

    @GetMapping("/hello")
    public Result hello() {
        return Result.success("hello");
    }

    @PostMapping("/login")
    public Result login(@RequestBody SysAccount sysAccount) {
        sysAccountService.login(sysAccount);
        return Result.success();
    }
}
