package com.daedong.tastemap.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetails;
    @Autowired
    private CustomOAuth2UserService customOauth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/error", "favicon.ico", "/resources/**", "/pic/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/user/login", "/user/join", "/user/auth", "/home", "/user/idChk","/board/list","/board/detail").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/home")
                .usernameParameter("email")
                .passwordParameter("pw")
                .failureUrl("/home") // 로그인 실패시 redirect
                .defaultSuccessUrl("/home");//로그인 성공하면 여기로감

        http.oauth2Login()
                .loginPage("/home")
                .defaultSuccessUrl("/home")
                .userInfoEndpoint() //OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당합니다.
                .userService(customOauth2UserService);

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }
}

