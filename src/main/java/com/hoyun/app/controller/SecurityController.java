package com.hoyun.app.controller;

import com.hoyun.app.VO.Account;
import com.hoyun.app.mapper.AccountMapper;
import com.hoyun.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountMapper accountMapper;

    //ADMIN 계정 부여
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public Account create() {
        Account account = new Account();
        account.setId("admin");
        account.setPassword("1234");
        accountService.save(account, "ROLE_ADMIN");
        return account;
    }

    // 추후 로그인 회원가입 완료하기
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("message", "Security Hello");
        System.out.println("Controller / 진입");
        return "index";
    }

    @RequestMapping(value = "/login/success", method = RequestMethod.POST)
    public String loginSuccess(Model model) {
        model.addAttribute("message", "Spring Security Home에 오신 것을 환영합니다");
        System.out.println("Controller /login 진입");
        return "success";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String joinPage(Model model) {

        System.out.println("Controller /join 진입");
        return "join";
    }

}
