package com.hoyun.app.mapper;

import com.hoyun.app.VO.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountMapper {
    @Select("SELECT * FROM WHERE id=#{id}")
    Account readAccount(String id);

    @Select("SELECT authority_name FROM AUTHORITY WHERE username=#{id}")
    List readAuthorities(String id);

    //@Insert("INSERT INTO USER VALUES(#{account.id},#{account.password},#{account.isAccountNonExpired},#{account.isAccountNonLocked},#{account.isCredentialsNonExpired},#{account.isEnabled})")
}
