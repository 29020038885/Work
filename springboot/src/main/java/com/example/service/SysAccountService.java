package com.example.service;


import com.example.entity.SysAccount;
import com.example.exception.CustomException;
import com.example.mapper.SysAccountMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAccountService {

    @Resource
    private SysAccountMapper sysAccountMapper;

    public void add(SysAccount sysAccount) {
        sysAccountMapper.insert(sysAccount);
    }

    public void deleteById(Integer id) {
        sysAccountMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public void update(SysAccount sysAccount) {
        sysAccountMapper.updateById(sysAccount);
    }

    public List<SysAccount> selectAll(SysAccount sysAccount) {
        return sysAccountMapper.selectAll( sysAccount);
    }

    public SysAccount selectById( Integer id ) {
        return sysAccountMapper.selectById(id);
    }


    public PageInfo<SysAccount> selectPage(SysAccount sysAccount, Integer pageNum, Integer pageSize ) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysAccount> list = sysAccountMapper.selectAll(sysAccount);
        return PageInfo.of(list);
    }


    public SysAccount login(SysAccount sysAccount) {
        String username = sysAccount.getUsername();
        SysAccount dbSysAccount = sysAccountMapper.selectByUsername(username);
        if (dbSysAccount == null) {
            throw new CustomException("500", "账号不存在");
        }
        String password = sysAccount.getPassword();
        if (!dbSysAccount.getPassword().equals(password)) {
            throw new CustomException("500", "账号或密码错误");
        }
        return dbSysAccount;
    }

}
