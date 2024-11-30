package com.example.mapper;

import com.example.entity.SysAccount;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysAccountMapper {
    List<SysAccount> selectAll(SysAccount sysAccount);

    @Select("select * from `sysaccount` where id = #{id}")
    SysAccount selectById(Integer id);

    void insert(SysAccount sysAccount);

    @Delete("delete from `sysaccount` where id = #{id}")
    void deleteById(Integer id);

    void updateById(SysAccount sysAccount);

    @Select("select * from `sysaccount` where username = #{username}")
    SysAccount selectByUsername(String username);
}
