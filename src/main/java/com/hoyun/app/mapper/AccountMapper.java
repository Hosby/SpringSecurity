package com.hoyun.app.mapper;

import com.hoyun.app.VO.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountMapper {
    @Select("SELECT * FROM user WHERE id=#{id}")
    Account readAccount(String id);

    @Select("SELECT authority_name FROM AUTHORITY WHERE username=#{id}")
    List readAuthorities(String id);

    @Insert("INSERT INTO USER VALUES(#{account.id},#{account.password},#{account.isAccountNonExpired},#{account.isAccountNonLocked},#{account.isCredentialsNonExpired},#{account.isEnabled})")
    void insertUser(@Param("account") Account account);

    @Insert("INSERT INTO AUTHORITY VALUES(#{id},#{autority})")
    void insertUserAuthority(@Param("id") String id, @Param("autority") String autority);

    @Select("SELECT* FROM USER")
    List readAllUsers();
}
