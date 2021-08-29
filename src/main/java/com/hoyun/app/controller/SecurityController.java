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

    // 메인페이지/로그인페이지
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("message", "Security Hello");
        System.out.println("Controller 로그인 페이지 진입");
        return "index";
    }

    // 회원가입 페이지
    @RequestMapping(value = "/joinForm", method = RequestMethod.GET)
    public String joinPage(Model model) {
        model.addAttribute("message", "회원가입 페이지입니다.");
        System.out.println("Controller 회원가입 페이지 진입");
        return "joinForm";
    }

    // 회원가입 처리
    @RequestMapping(value = "/joinSuccess", method = RequestMethod.GET)
    public String joinSuccess(Account account) {
        accountService.save(account, "ROLE_ADMIN");
        System.out.println("joinSuccess 진입");
        return "redirect:/index";
    }

    // 로그인 결과 페이지
    @RequestMapping(value = "/login/process", method = RequestMethod.POST)
    public String loginSuccess(Model model, Account account) {
        model.addAttribute("message", "Spring Security Home에 오신 것을 환영합니다");
        model.addAttribute("userName", account.getUsername());
        System.out.println("Controller /login 진입");
        return "success";
    }

    // 로그아웃 결과 페이지
    @RequestMapping(value = "/logout/success", method = RequestMethod.GET)
    public String logoutSuccess() {
        System.out.println("Controller 로그아웃 결과 페이지 진입");
        return "logout";
    }

    // 내 정보 페이지
    @RequestMapping(value = "/user/myInfo", method = RequestMethod.GET)
    public String myInfo(Model model, Account account) {

        model.addAttribute("userName", account.getUsername());
        model.addAttribute("password", account.getPassword());

        return "myInfo";
    }

    // 에러페이지
    @RequestMapping(value = "/login?error=true", method = RequestMethod.GET)
    public String failure(Model model, Account account) {
        return "failure";
    }


}
