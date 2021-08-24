package com.hoyun.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*@Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // 요청에 의한 보안검사 시작
                .anyRequest().authenticated() //어떤 요청에도 보안검사를 한다.
        .and()
                .formLogin() // form 로그인 기능이 작동함
                .loginPage("/login") // 사용자의 정의 로그인 페이지
                .defaultSuccessUrl("/login/success") // 로그인 성공 후 이동 페이지
                .failureUrl("/login") // 로그인 실패 후 이동 페이지
                .usernameParameter("userName") // 아이디 parameter 설정
                .passwordParameter("password") // 비밀번호  parameter 설정
                .loginProcessingUrl("/login") // 로그인 Form Action URL
                *//*.successHandler(loginSuccessHandler()) // 로그인 성공 후 핸들러 (해당 핸들러를 생성하여 핸들링 해준다)
                .failureUrl(loginFailureHandler()) // 로그인 실패 후 핸들러 (해당 핸들러를 생성하여 핸들링 해준다)*//*
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                                        HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        System.out.println("authentication:: "+ authentication.getName());
                        response.sendRedirect("/");
                    }
                })//로그인 성공 후 핸들러
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                                        HttpServletResponse response,
                                                        AuthenticationException e) throws IOException, ServletException {
                        System.out.println("exception:: "+e.getMessage());
                        response.sendRedirect("/login");
                    }
                })//로그인 실패 후 핸들러
                .permitAll() // 사용자 정의 로그인 페이지 접근 권한 승인
        .and() // 로그아웃 처리
                .logout()
                .logoutUrl("/logout") // 로그아웃 URL
                .logoutSuccessUrl("/login") // 로그아웃 성공 후 이동페이지
                .deleteCookies("JSESSIONID", "remember-me") // 로그아웃 후 쿠키 삭제
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        HttpSession session = request.getSession();
                        session.invalidate();
                    }
                }) // 로그아웃 핸들러
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.sendRedirect("/login");
                    }
                }) // 로그아웃 성공 후 핸들러
                .deleteCookies("remember-me") // 쿠키삭제
        .and() // remember-me 기능 작동
                .rememberMe() // remember-me 기능 작동
                .rememberMeParameter("remember-me") // 기본 파라미터명은 remember-me
                .tokenValiditySeconds(3600) // default는 14일
                .alwaysRemember(true) // remember me 기능이 활성화되지 않아도 항상 실행. default false
                .userDetailsService(userDetailsService()) // Remember me에서 시스템에 있는 사용자 계정을 조회할 때 사용할 클래스
        .and() // 동시 세션 제어
                .sessionManagement() //세션 관리 기능이 작동함
                .invalidSessionUrl("/invalid")//세션이 유효하지 않을 때 이동 할 페이지
                .maximumSessions(1)//최대 허용 가능 세션 수, (-1: 무제한)
                .maxSessionsPreventsLogin(true)//동시 로그인 차단함, false: 기존 세션 만료(default)
                .expiredUrl("/expired");//세션이 만료된 경우 이동할 페이지
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests() //페이지 권한 설정
                .antMatchers("/index", "resources/**", "/joinForm", "/joinSuccess", "/login", "logout").permitAll()
                //.antMatchers("/admin").hasRole("ADMIN")
                //.antMatchers("/user/myInfo").hasRole("USER")
                .anyRequest().authenticated()
                .and() // 로그인 설정
                .formLogin()
                .loginPage("/index")
                .loginProcessingUrl("login/process")
                .usernameParameter("username")
                .passwordParameter("password")
                //.defaultSuccessUrl("/login/success")
                .failureUrl("/login?error=true")
                .permitAll()
                .and() // 로그아웃 설정
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/success")
                .and() // 403 예외처리 핸들링
                .exceptionHandling()
                .accessDeniedPage("/accessDenied");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
