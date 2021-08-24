package com.hoyun.app.controller;

import com.hoyun.app.VO.Account;
import com.hoyun.app.mapper.AccountMapper;
import com.hoyun.app.service.AccountService;
import org.apache.ibatis.annotations.Param;
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
    /*
    * / 로그인페이지
    * /login 로그인 완료 기능
    * /login/success 로그인 성공 페이지
    * /joinForm 회원가입 페이지
    * /join 회원가입 완료 기능
    * */

    // 메인페이지/로그인페이지
    @RequestMapping("/")
    public String index(Model model, Account account) {
        model.addAttribute("message", "Security Hello");
        System.out.println("Controller 로그인 페이지 진입");
        return "/index";
    }

    // 회원가입 페이지
    @RequestMapping(value = "/joinForm", method = RequestMethod.GET)
    public String joinPage() {
        System.out.println("Controller 회원가입 페이지 진입");
        return "joinForm";
    }

    // 회원가입 처리
    @RequestMapping(value = "/joinSuccess", method = RequestMethod.POST)
    public String joinSuccess(Account account) {
        accountService.joinUser(account, "ROLE_ADMIN");
        return "/index";
    }

    // 로그인 결과 페이지
    @RequestMapping(value = "/login/success", method = RequestMethod.GET)
    public String loginSuccess(Model model, Account account) {
        model.addAttribute("message", "Spring Security Home에 오신 것을 환영합니다");
        model.addAttribute("userId", account.getUsername());
        System.out.println("Controller /login 진입");
        return "success";
    }

    // 로그아웃 결과 페이지
    @RequestMapping(value = "/logout/success", method = RequestMethod.GET)
    public String logoutSuccess(Model model, Account account) {
        return "/logout";
    }

}
