package com.example.controller;

import com.example.common.Result;
import com.example.entity.SysAccount;
import com.example.service.SysAccountService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysAccount")
public class SysAccountController {

    @Resource
    private SysAccountService sysAccountService;

    /**
     * 新增数据
     */
    @PostMapping("/add")
    public Result insert(@RequestBody SysAccount sysAccount) {
        sysAccountService.add(sysAccount);
        return Result.success();
    }

    /**
     * 删除单个数据
     */
    @DeleteMapping("/deleteById/{id}")
    public Result delete(@PathVariable Integer id) {
        sysAccountService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除数据
     */
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        sysAccountService.deleteBatch(ids);
        return Result.success();
    }
    /**
     * 更新数据
     */
    @PutMapping("/update")
    public Result update(@RequestBody SysAccount sysAccount) {
        sysAccountService.update(sysAccount);
        return Result.success();
    }


    /**
     * 查询单个数据
     */
    @GetMapping("/selectById/{id}")
    public Result selectById (@PathVariable Integer id) {
        SysAccount sysAccount = sysAccountService.selectById(id);
        return Result.success(sysAccount);
    }


    /**
     * 查询所有数据
     */
    @GetMapping("/selectAll")
    public Result selectALL(SysAccount sysAccount) {
        List<SysAccount> list = sysAccountService.selectAll(sysAccount);
        return Result.success(list);
    }


    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage (SysAccount sysAccount,
                              @RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize) {

        PageInfo<SysAccount> pageInfo = sysAccountService.selectPage(sysAccount, pageNum, pageSize);
        return Result.success(pageInfo);

    }


}
