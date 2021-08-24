package com.hoyun.app.repository;

import com.hoyun.app.VO.Account;
import com.hoyun.app.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {
    @Autowired
    AccountMapper accountMapper;

    public Account save(Account account, String role) {
        accountMapper.insertUser(account);
        accountMapper.insertUserAuthority(account.getUsername(), role);
        return account;
    }

    public Account findById(String id) {
        return accountMapper.readAccount(id);
    }

    public List<String> findAuthoritiesById(String username) {
        return accountMapper.readAuthorities(username);
    }
}
