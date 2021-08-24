package com.hoyun.app.service;

import com.hoyun.app.VO.Account;
import com.hoyun.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        Account account = accountRepository.findById(id);
        account.setAuthorities(getAuthorities(id));

        UserDetails userDetails = new UserDetails() {

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public String getUsername() {
                return account.getUsername();
            }

            @Override
            public String getPassword() {
                return account.getPassword();
            }

            @Override
            public Collection getAuthorities() {
                return account.getAuthorities();
            }
        };
        return account;
    }

    public Account save(Account account, String role) {
        account.setUsername(account.getUsername());
        System.out.println("username : " + account.getUsername());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setAccountNonExpired(true);
        account.setAccountNonLocked(true);
        account.setCredentialsNonExpired(true);
        account.setEnabled(true);
        System.out.println("Service 진입");

        return accountRepository.save(account, role);
    }

    public Collection<GrantedAuthority> getAuthorities(String username) {
        List<String> string_authorities = accountRepository.findAuthoritiesById(username);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String authority : string_authorities) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;
    }

}
